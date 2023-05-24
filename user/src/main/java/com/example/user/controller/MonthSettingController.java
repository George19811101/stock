package com.example.user.controller;

import com.example.user.annotation.UserLoginToken;
import com.example.user.common.Result;
import com.example.user.model.bo.DaySetting;
import com.example.user.model.bo.MonthSetting;
import com.example.user.service.DaySettingService;
import com.example.user.service.MonthSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "按月配资")
@RestController
@RequestMapping("monthSetting")
public class MonthSettingController {

    @Autowired
    private MonthSettingService monthSettingService;

    @ApiOperation("接收股票信息")
    @PostMapping("saveMonthsetting")
    @UserLoginToken
    public Result<String> save(@RequestBody MonthSetting monthSetting) {
        return monthSettingService.save(monthSetting);
    }

    @ApiOperation("撤销月配置信息")
    @PostMapping("revokeMonthsetting")
    @UserLoginToken
    public Result<String> revoke(@RequestBody long id) {
        return monthSettingService.revoke(id);
    }
}
