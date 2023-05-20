package com.example.user.service.impl;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Validator;
import com.example.user.common.ExceptionEnum;
import com.example.user.common.MD5Util;
import com.example.user.common.Result;
import com.example.user.common.SmsUtils;
import com.example.user.mapper.UserMapper;
import com.example.user.model.bo.*;
import com.example.user.model.po.User;
import com.example.user.model.vo.UserVo;
import com.example.user.service.UserService;

import com.example.user.util.SnowflakeUtils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SmsUtils smsUtils;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 发送验证码
     *
     * @param captchaBo
     * @return
     */
    @Override
    public Result<String> sendCaptcha(SendCaptchaBo captchaBo) {

        String to = captchaBo.getTo();

        if (BooleanUtils.negate(Validator.isMobile(to)) && BooleanUtils.negate(Validator.isEmail(to))) {
            return Result.exception(ExceptionEnum.PHONE_NO_OR_MAIL_WRONG_FORMAT);
        }

        String code = RandomStringUtils.randomNumeric(6);;


        // 手机验证码5分钟过期
        redisTemplate.opsForValue().set(StringUtils.join("captcha_", to) , code, 5, TimeUnit.MINUTES);

        log.info("code is {}",code);
        return Result.ok();

    }


    @Override
    @Transactional
    public Result<String> register(RegisterBo registerBo) {

        DateTime date = DateUtil.date();

        String password = registerBo.getPassword();


        String phoneNo = registerBo.getPhoneNo();

        String invitationCode = registerBo.getInvitationCode();

        String areaCode = registerBo.getAreaCode();
        String tradePassword=registerBo.getTradePassword();

        String key = areaCode.equals("86") ? StringUtils.join("captcha_", phoneNo) : StringUtils.join("captcha_", areaCode + phoneNo);


        Object captchaByRedis = redisTemplate.opsForValue().get(key);

        if (Objects.isNull(captchaByRedis)) {
            return Result.exception(ExceptionEnum.PLEASE_SEND_CAPTCHA);
        }
        // 校验验证码
        if (BooleanUtils.negate(Objects.equals(captchaByRedis.toString(), registerBo.getCaptcha()))) {
            return Result.exception(ExceptionEnum.CAPTCHA_ERROR);
        }
        // 清除redis中的验证码
        redisTemplate.delete(key);

        // 两次密码不一致
        if (BooleanUtils.negate(StringUtils.equals(password, registerBo.getConfirmPassword()))) {
            return Result.exception(ExceptionEnum.THE_TWO_PASSWORDS_DO_NOT_MATCH);
        }

        User user = new User();
//        SnowflakeUtils snowflakeUtils=new SnowflakeUtils(5L,5L);
//        long id = snowflakeUtils.nextId();

        user.setCreateTime(date)
                .setAreaCode(areaCode)
                .setUserName(phoneNo)
                .setPhoneNo(phoneNo)
                .setStatus(1)
                .setLoginPassword(MD5Util.string2MD5(password));
        if (StringUtils.isNotBlank(tradePassword)){
            user.setCompleteLevel(1);
            user.setTradePassword(MD5Util.string2MD5(tradePassword));
        }else{
            user.setCompleteLevel(0);
        }

            int primarykeyid=0;

        // --判断推广码是否为空
       if (StringUtils.isNotBlank(invitationCode)) {
            // 查询推广码是否存在 && 根据推广码查询userID
            User invitationUser = userMapper.getUserByInvitationCode(invitationCode);
            if (ObjectUtils.isEmpty(invitationUser)) {
                return Result.exception(ExceptionEnum.NO_USER);
            }

            if (Objects.isNull(invitationUser.getId())) {
                return Result.exception(ExceptionEnum.INVITATION_NULL);
            }

            // 添加推广码用户“id”为上级
            user.setParentId(invitationUser.getId());

           primarykeyid=userMapper.saveUserByEntity(user);
//            // 更新代理商
//            userMapper.updateByPrimaryKey(invitationUser) ;

        } else {

           primarykeyid=userMapper.saveUserByEntity(user);

        }


//     user.setInvitationCode(user.getId());
//
//      userMapper.updateById(user);


        return Result.ok();

    }


    /**
     * 登录
     */
    @Override
    public Result<UserVo> login(UserBo userBo) {

        String phoneNo = userBo.getPhoneNo();

        UserVo userVo = null;

        String areaCode = userBo.getAreaCode();

        if (!StringUtils.isEmpty(userBo.getPhoneNo())) {

            userVo = userMapper.loginByPhoneNo(phoneNo, areaCode);

            if (Objects.isNull(userVo)) {

                return Result.exception(ExceptionEnum.PHONE_NO_NOT_EXIST);

            }

        }

        String str = MD5Util.string2MD5(userBo.getLoginPassword());
        if(!userVo.getLoginPassword().equals(MD5Util.string2MD5(userBo.getLoginPassword()))){
            return Result.exception(ExceptionEnum.PASSWORD_ERROR);
        }
//        userVo.setToken( RandomStringUtils.randomAlphanumeric(24));
//        redisTemplate.opsForValue().set(userVo.getToken() , userVo, 20, TimeUnit.MINUTES);
        StpUtil.login(userVo.getId());
        userVo.setToken(StpUtil.getTokenValue());

        return Result.ok(userVo);

    }

    @Override
    public Result<String> updateLoginPassword(LoginPassword loginPassword) {
        long id=loginPassword.getUserId();
        User userVo= userMapper.selectById(id);
        String oldPassword=loginPassword.getOldPassword();
        String newPassword=loginPassword.getNewPassword();
        String reNewPassword=loginPassword.getRenewPassword();
        if(StringUtils.isEmpty(oldPassword) ){
            //原始密码为空
            return Result.exception(ExceptionEnum.PASSWORD_ISNULL);
        }
        if(StringUtils.isEmpty(newPassword) ){
            //新密码为空
            return Result.exception(ExceptionEnum.NEWPASSWORD_ISNULL);
        }
        if(!newPassword.equals(reNewPassword)){
            //新密码两次输入不一致
            return Result.exception(ExceptionEnum.NEWPASSWORD_ISNOTSAME);
        }
        if(!MD5Util.string2MD5(oldPassword).equals(userVo.getLoginPassword()) ){
            //原始密码加密后与存储的密码不一致
            return Result.exception(ExceptionEnum.PASSWORD_ERROR);
        }
        userVo.setLoginPassword(MD5Util.string2MD5(newPassword));

        userMapper.updateByUserId(userVo);

        return Result.ok();
    }

    @Override
    public Result<String> addTradePassword(TradePassword tradePassword) {
        long id=tradePassword.getUserId();
        User userVo= userMapper.selectById(id);
        String newPassword=tradePassword.getNewTradePassword();
        String reNewPassword=tradePassword.getRenewTradePassword();

        if(StringUtils.isEmpty(newPassword) ){
            //新密码为空
            return Result.exception(ExceptionEnum.NEWPASSWORD_ISNULL);
        }
        if(!newPassword.equals(reNewPassword)){
            //新密码两次输入不一致
            return Result.exception(ExceptionEnum.NEWPASSWORD_ISNOTSAME);
        }
        userVo.setTradePassword(MD5Util.string2MD5(newPassword));
        if(userVo.getCompleteLevel()==0){
            userVo.setCompleteLevel(1);
        }
        userMapper.updateByUserId(userVo);
        return Result.ok();
    }

    @Override
    public Result<String> updateTradePassword(updateTradePassword updateTradePassword) {
        long id=updateTradePassword.getUserId();
        User userVo= userMapper.selectById(id);
        String oldPassword=updateTradePassword.getOldTradePassword();
        String newPassword=updateTradePassword.getNewTradePassword();
        String reNewPassword=updateTradePassword.getRenewTradePassword();
        if(StringUtils.isEmpty(oldPassword) ){
            //原始密码为空
            return Result.exception(ExceptionEnum.PASSWORD_ISNULL);
        }
        if(StringUtils.isEmpty(newPassword) ){
            //新密码为空
            return Result.exception(ExceptionEnum.NEWPASSWORD_ISNULL);
        }
        if(!newPassword.equals(reNewPassword)){
            //新密码两次输入不一致
            return Result.exception(ExceptionEnum.NEWPASSWORD_ISNOTSAME);
        }
        if(!MD5Util.string2MD5(oldPassword).equals(userVo.getTradePassword()) ){
            //原始密码加密后与存储的密码不一致
            return Result.exception(ExceptionEnum.PASSWORD_ERROR);
        }
        userVo.setTradePassword(MD5Util.string2MD5(newPassword));

        userMapper.updateByUserId(userVo);
        return Result.ok();
    }

    @Override
    public Result<String> sendCode( ) {
        String result= smsUtils.sendSms("13886918560","死家伙，我不找你你就不找我了，滚蛋，属王八的");

        return Result.ok();
    }

}
