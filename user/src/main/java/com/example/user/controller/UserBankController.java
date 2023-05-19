package com.example.user.controller;


import com.example.user.common.Result;
import com.example.user.model.Banks;
import com.example.user.model.UserBank;
import com.example.user.model.bo.*;
import com.example.user.model.vo.UserVo;
import com.example.user.service.BanksService;
import com.example.user.service.UserBankService;
import com.example.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "用户中心")
@RestController
@RequestMapping("user")
public class UserBankController {

    @Autowired
    private UserBankService userBankService;


    @Autowired
    private BanksService banksService;



    @ApiOperation("添加银行信息")
    @PostMapping("/add")
    public Result<String> add(@RequestBody UserBank userBank) {
        return userBankService.add(userBank);
    }

    @ApiOperation("修改银行信息")
    @PostMapping("/update")
    public Result<String> update(@RequestBody UserBank userBank) {
        return userBankService.updateBankById(userBank);
    }

    @ApiOperation("所有的银行信息")
    @PostMapping("/getAllBanks")
    public Result<List<Banks>> getAllBanks( ) {
        List bankList=userBankService.getAllBanks();
        return Result.ok(bankList);
    }


}



