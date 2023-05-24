package com.example.user.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.example.user.annotation.UserLoginToken;
import com.example.user.common.Result;
import com.example.user.model.Banks;
import com.example.user.model.bo.*;
import com.example.user.model.vo.UserVo;
import com.example.user.service.BanksService;
import com.example.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "银行信息")
@RestController
@RequestMapping("/banks")
public class BanksController {

    @Autowired
    private BanksService banksService;



    @ApiOperation("添加银行信息")
    @PostMapping("/add")
    @UserLoginToken
    public Result<String> add(@RequestBody Banks banks) {
        return banksService.add(banks);
    }

    @ApiOperation("修改银行信息")
    @PostMapping("/update")
    @UserLoginToken
    public Result<String> update(@RequestBody Banks banks) {
        return banksService.updateBankById(banks);
    }

    @ApiOperation("所有的银行信息")
    @GetMapping("/getAllBanks")
    @UserLoginToken
    public Result<List<Banks>> getAllBanks( ) {
        List bankList=banksService.getAllBanks();
        return Result.ok(bankList);
    }

}



