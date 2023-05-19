package com.example.user.controller;

import com.example.user.common.Result;
import com.example.user.model.bo.DaySetting;
import com.example.user.service.DaySettingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "按天配资")
@RestController
@RequestMapping("daySetting")
public class DaySettingController {

    @Autowired
    private DaySettingService daySettingService;

    @ApiOperation("保真日配置信息")
    @PostMapping("saveDaysetting")
    public Result<String> save(@RequestBody DaySetting daySetting) {
        return daySettingService.save(daySetting);
    }
    @ApiOperation("撤销日配置信息")
    @PostMapping("revokeDaysetting")
    public Result<String> revoke(@RequestBody long id) {
        return daySettingService.revoke(id);
    }

}
