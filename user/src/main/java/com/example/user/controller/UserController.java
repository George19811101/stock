package com.example.user.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.example.user.annotation.UserLoginToken;
import com.example.user.common.Result;
import com.example.user.model.bo.*;
import com.example.user.model.vo.UserVo;
import com.example.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户中心")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private RedisTemplate redisTemplate;


    @ApiOperation("发送验证码")
    @PostMapping("sendCaptcha")
    @UserLoginToken
    public Result<String> sendCaptcha(@RequestBody SendCaptchaBo captchaBo) {
        return userService.sendCaptcha(captchaBo);
    }

    @ApiOperation("注册")
    @PostMapping("register")
    public Result<String> register(@RequestBody RegisterBo registerBo) {
        return userService.register(registerBo);
    }

    @ApiOperation("登录")
    @PostMapping("login")
    public Result<UserVo> login(@RequestBody UserBo userBo) throws Exception {
        return userService.login(userBo);
    }

    @ApiOperation("修改登录密码")
    @PostMapping("updateLoginPassword")
    @UserLoginToken
    public Result<String> updateLoginPassword(@RequestBody LoginPassword loginPassword) {
        return userService.updateLoginPassword(loginPassword);
    }
    @ApiOperation("添加提现密码")
    @PostMapping("addTradePassword")
    @UserLoginToken
    public Result<String> addTradePassword(@RequestBody TradePassword tradePassword) {
        return userService.addTradePassword(tradePassword);
    }

    @ApiOperation("修改提现密码")
    @PostMapping("updateTradePassword")
    @UserLoginToken
    public Result<String> updateTradePassword(@RequestBody updateTradePassword updateTradePassword) {
        return userService.updateTradePassword(updateTradePassword);
    }


    @ApiOperation("发送短信")
    @PostMapping("sendCode")
    @UserLoginToken
    public Result<String> sendCode( ) {
        return userService.sendCode( );
    }
}



