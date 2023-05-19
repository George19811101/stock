//package com.example.user.annotation.notice;
//
//import cn.hutool.core.bean.BeanUtil;
//import cn.hutool.core.date.DateUtil;
//import cn.hutool.core.lang.Validator;
//import cn.hutool.core.map.MapUtil;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.exchange.user.common.Constant;
//import com.exchange.user.entity.bo.EmailBo;
//import com.exchange.user.entity.po.LoginOn;
//import com.exchange.user.entity.po.User;
//import com.exchange.user.enums.JgpushEnum;
//import com.exchange.user.enums.MailEnum;
//import com.exchange.user.enums.NoticeMessageEnum;
//import com.exchange.user.enums.SmsEnum;
//import com.exchange.user.mapper.UserMapper;
//import com.exchange.user.utils.JGPushUtils;
//import com.exchange.user.utils.MailUtils;
//import com.exchange.user.utils.SmsUtils;
//import lombok.SneakyThrows;
//import org.apache.commons.lang3.BooleanUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.DefaultParameterNameDiscoverer;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.expression.EvaluationContext;
//import org.springframework.expression.spel.standard.SpelExpressionParser;
//import org.springframework.expression.spel.support.StandardEvaluationContext;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//import java.util.Objects;
//import java.util.concurrent.TimeUnit;
//
///**
// * desc: 解析 自定义 通知注解解析
// *
// * @author: xwx
// * @mail: 10086@126.com
// * @create 2020-09-23 16:50
// */
//@Aspect
//@Component
//public class NoticeAspect {
//
//    @Autowired
//    private SmsUtils smsUtils;
//
//    @Autowired
//    private MailUtils mailUtils;
//
//    @Autowired
//    private UserMapper userMapper;
//
//    @Autowired
//    private JGPushUtils jgPushUtils;
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    /**
//     * 用于SpEL表达式解析.
//     */
//    private static final SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
//
//    /**
//     * 用于获取方法参数定义名字.
//     */
//    private static final DefaultParameterNameDiscoverer defaultParameterNameDiscoverer = new DefaultParameterNameDiscoverer();
//
//    @SneakyThrows
//    @Around("@annotation(com.exchange.user.annotation.notice.Notice)")
//    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
//
//        // 获取注解实体信息
//        Notice noticeEntity = (((MethodSignature) proceedingJoinPoint.getSignature()).getMethod())
//                .getAnnotation(Notice.class);
//
//        Object resultObject = proceedingJoinPoint.proceed();
//
//        JSONObject result = (JSONObject) JSON.toJSON(resultObject);
//
//        Integer code = result.getInteger("code");
//
//        if (Objects.equals(Constant.TWO_HUNDRED, code)) {
//
//            // 获取调用类型
//            NoticeMessageEnum noticeMessageEnum = noticeEntity.noticeMessage();
//
//            // 获取参数
//            String userName = noticeEntity.param();
//
//            SmsEnum smsEnum = noticeMessageEnum.getSmsEnum();
//
//            MailEnum mailEnum = noticeMessageEnum.getMailEnum();
//
//            JgpushEnum jgpushEnum = noticeMessageEnum.getJgpushEnum();
//
//            String desc = noticeMessageEnum.getDesc();
//
//            Map paramMap = BeanUtil.beanToMap(getKeyBySpeL(userName, proceedingJoinPoint));
//
//            paramMap.put("title", desc);
//
//            paramMap.put("time", DateUtil.date());
//
//            String type = null;
//
//            // 注册成功通知
//            if (StringUtils.equals(noticeMessageEnum.getDesc(), NoticeMessageEnum.REGISTERED_SUCCESSFULLY.getDesc())) {
//
//                String phoneNoOrMail = MapUtil.getStr(paramMap, "phoneNoOrMail");
//
//                type = MapUtil.getStr(paramMap, "type");
//
//                paramMap.put("userName", phoneNoOrMail);
//
//                if (StringUtils.equals(type, Constant.ZERO.toString())) {
//
//                    // 短信
//                    if (Objects.nonNull(smsEnum)) {
//
//                        // 获取：模板+参数
//                        String descNameByValue = SmsEnum.getDescNameByValue(smsEnum.getValue(), paramMap);
//
//                        if (BooleanUtils.negate(Validator.isMobile(phoneNoOrMail))) {
//                            smsUtils.sendSmss(String.valueOf(phoneNoOrMail), descNameByValue);
//                        }else {
//                            smsUtils.sendSms(String.valueOf(phoneNoOrMail), descNameByValue);
//                        }
//
//                    }
//
//                } else {
//
//                    // 邮箱
//                    if (Objects.nonNull(mailEnum)) {
//
//                        // 获取：模板
//                        String descNameByValue = mailEnum.getBeanNameByValue(mailEnum.getValue());
//                        EmailBo emailBo = new EmailBo()
//                                .setTemplateType(descNameByValue)
//                                .setTo(phoneNoOrMail)
//                                .setSubject("注册成功通知");
//
//                        emailBo.setContent(paramMap);
//
//                        mailUtils.sendEmail(emailBo);
//
//                    }
//
//                }
//
//                return resultObject;
//
//            }
//
//        }
//
//        return resultObject;
//
//    }
//
//    /**
//     * 【异地登录通知】（账户，ip地址，登录地，登录设备，时间）
//     */
//    @SneakyThrows
//    public void errorLogin(LoginOn login) {
//
//        // 获取异常地登录模板
//        NoticeMessageEnum noticeMessageEnum = NoticeMessageEnum.ERROR_LOGIN;
//
//        SmsEnum smsEnum = noticeMessageEnum.getSmsEnum();
//
//        MailEnum mailEnum = noticeMessageEnum.getMailEnum();
//
//        String desc = noticeMessageEnum.getDesc();
//
//        String loginName = login.getLoginName();
//
//        Map paramMap = BeanUtil.beanToMap(login);
//
//        paramMap.put("title", desc);
//
//        paramMap.put("time", DateUtil.date());
//
//        String deviceId = login.getDeviceId();
//
//        String device = login.getDevice();
//        String ipAddress = login.getIpAddress();
//        String ipAddressCountry = login.getIpAddressCountry();
//
//        paramMap.put("device", device);
//
//        paramMap.put("userName", loginName);
//
//        paramMap.put("deviceId", deviceId);
//
//        paramMap.put("ipAddress", ipAddress);
//
//        paramMap.put("ipAddressCountry", ipAddressCountry);
//
//        User user = userMapper.selectByPrimaryKey(login.getUserId());
//
//        if (StringUtils.isNotBlank(user.getMail())){
//            // 短信
//            // 获取：模板+参数
//            String smsTemplate = SmsEnum.getDescNameByValue(smsEnum.getValue(), paramMap);
//
//            //发送短信
//            if (BooleanUtils.negate(Validator.isMobile(user.getMail()))) {
//                smsUtils.sendSmss(String.valueOf(user.getMail()), smsTemplate);
//            }else {
//                smsUtils.sendSms(String.valueOf(user.getMail()), smsTemplate);
//            }
//
//        }else if (StringUtils.isNotBlank(user.getPhoneNo())){
//            // 邮箱
//            // 获取：模板
//            String mailTemplate = mailEnum.getBeanNameByValue(mailEnum.getValue());
//
//            EmailBo emailBo = new EmailBo()
//                    .setTemplateType(mailTemplate)
////                .setTo("2215312874@qq.com")
//                    .setTo(user.getPhoneNo())
//                    .setSubject(desc);
//
//            emailBo.setContent(paramMap);
//
//            mailUtils.sendEmail(emailBo);
//        }
//
//    }
//
//    /**
//     * desc：发送验证码
//     * 1、【注册验证码：手机||邮箱】type:0||1（账户，ip地址，设备）
//     * 2、【提币邮件验证码：手机&&邮箱--（验证码，账户，地点，IP地址，提币金额，币种，收款地址）】type:2 获取个人绑定方式，及手机，邮箱
//     * 3、修改资金密码
//     * 验证码发送
//     */
//    public void sendCaptcha(Map<String, Object> map) {
//
//        // 获取发送模板（验证码只发送：手机||邮箱）
//        NoticeMessageEnum noticeMessage = NoticeMessageEnum.valueOf((String) map.get("noticeMessageEnum"));
//
//        SmsEnum smsEnum = noticeMessage.getSmsEnum();       // 取出短信模板
//        MailEnum mailEnum = noticeMessage.getMailEnum();    // 取出邮箱模板
//        String desc = noticeMessage.getDesc();              // 标题
//
//        String userName = null;
//
//        // 发送类型
//        Integer type = (Integer) map.get("type");
//
//        // 验证码
//        String code = (String) map.get("code");
//
//        Map<String, Object> paramMap = BeanUtil.beanToMap(map);
//
//        paramMap.put("title", desc);
//
//        paramMap.put("code", code);
//
//        paramMap.put("time", DateUtil.date());
//
//        // 根据用户已绑定手机号||邮箱去发送.(0：手机，1：邮箱，2手机&&邮箱)
//        if (Objects.equals(type, Constant.TWO)) {
//
//            if (Objects.equals(noticeMessage, NoticeMessageEnum.OPERATION_MONEY_CODE)) {
//                // service 带过来
//                userName = (String) map.get("userName");
//            }
//
////            paramMap.put("userName", userName);
//
//            // 短信获取：模板+参数
//            String smsTemplate = SmsEnum.getDescNameByValue(smsEnum.getValue(), paramMap);
//
//            // 邮箱获取：模板
//            String mailTemplate = mailEnum.getBeanNameByValue(mailEnum.getValue());
//
//            EmailBo emailBo = new EmailBo()
//                    .setTemplateType(mailTemplate)
////                    .setTo("2215312874@qq.com")// map.get("mail")
//                    .setTo(String.valueOf(map.get("mail")))
//                    .setContent(paramMap)
//                    .setSubject(desc);
//
//            // 短信发送
//
//            if (BooleanUtils.negate(Validator.isMobile((CharSequence) map.get("phone")))) {
//                smsUtils.sendSmss(String.valueOf(map.get("phone")), smsTemplate);
//            }else {
//                smsUtils.sendSms(String.valueOf(map.get("phone")), smsTemplate);
//            }
//
//            // 邮箱发送
//            mailUtils.sendEmail(emailBo);
//
//            // 手机验证码5分钟过期
////            redisTemplate.opsForValue().set(StringUtils.join(Constant.CAPTCHA, map.get("phone")), code, Constant.FIVE,
////                    TimeUnit.MINUTES);
//            redisTemplate.opsForValue().set(Constant.CAPTCHA + map.get("phone"), code, Constant.FIVE,
//                    TimeUnit.MINUTES);
//
//            // 邮箱验证码5分钟过期
////            redisTemplate.opsForValue().set(StringUtils.join(Constant.MAI_SYMBOL, map.get("mail")), code, Constant.FIVE,
////                    TimeUnit.MINUTES);
//            redisTemplate.opsForValue().set(Constant.MAI_SYMBOL+ map.get("mail"), code, Constant.FIVE,
//                    TimeUnit.MINUTES);
//
//        } else {
//
//            if (Objects.equals(noticeMessage, NoticeMessageEnum.REGISTRATION_CODE)) {
//                // 用户名
//                userName = (String) (Objects.equals(type, Constant.ZERO) ? map.get("phone") : map.get("mail"));
//                paramMap.put("userName", userName);
//            }
//
//            // 手机发送
//            if (Objects.equals(type, Constant.ZERO)) {
//
//                paramMap.put("userName", map.get("phone"));
//
//                // 短信获取：模板+参数
//                String smsTemplate = SmsEnum.getDescNameByValue(smsEnum.getValue(), paramMap);
//
//                if (BooleanUtils.negate(Validator.isMobile((CharSequence) map.get("phone")))) {
//                    smsUtils.sendSmss(String.valueOf(map.get("phone")), smsTemplate);
//                }else {
//                    smsUtils.sendSms(String.valueOf(map.get("phone")), smsTemplate);
//                }
//                redisTemplate.opsForValue().set(Constant.CAPTCHA + map.get("phone"), code, Constant.FIVE,
//                        TimeUnit.MINUTES);
////                redisTemplate.opsForValue().set(StringUtils.join(Constant.CAPTCHA, map.get("phone")),code, Constant.FIVE,
////                        TimeUnit.MINUTES);
//
//            } else if (Objects.equals(type, Constant.ONE)) {
//
//                // 邮箱获取：模板
//                String mailTemplate = mailEnum.getBeanNameByValue(mailEnum.getValue());
//
//                EmailBo emailBo = new EmailBo()
//                        .setTemplateType(mailTemplate)
////                        .setTo("2215312874@qq.com")
//                        .setTo(String.valueOf(map.get("mail")))
//                        .setContent(paramMap)
//                        .setSubject(desc);
//
//                mailUtils.sendEmail(emailBo);
//                redisTemplate.opsForValue().set(Constant.MAI_SYMBOL+map.get("mail"), code, Constant.FIVE,
//                        TimeUnit.MINUTES);
////                redisTemplate.opsForValue().set(StringUtils.join(Constant.MAI_SYMBOL, map.get("mail")), code, Constant.FIVE,
////                        TimeUnit.MINUTES);
//
//            }
//
//        }
//
//    }
//
//    /**
//     * 获取缓存的key
//     * key 定义在注解上，支持SPEL表达式
//     *
//     * @return
//     */
//    public Object getKeyBySpeL(String spel, ProceedingJoinPoint proceedingJoinPoint) {
//
//        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
//
//        String[] paramNames = defaultParameterNameDiscoverer.getParameterNames(methodSignature.getMethod());
//
//        EvaluationContext context = new StandardEvaluationContext();
//
//        Object[] args = proceedingJoinPoint.getArgs();
//
//        for (int i = 0; i < args.length; i++) {
//            context.setVariable(paramNames[i], args[i]);
//        }
//
//        return spelExpressionParser.parseExpression(spel).getValue(context);
//
//    }
//
//}
