package com.example.user.controller;

import com.example.user.common.Result;
import com.example.user.model.bo.SendCaptchaBo;
import com.example.user.model.bo.StockBo;
import com.example.user.service.StockService;
import com.example.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@Api(tags = "股票信息")
@RestController
@RequestMapping("stock")
public class StockController {
    @Autowired
    private StockService stockService;

    @ApiOperation("接收股票信息")
    @GetMapping("getStock")
    public Result<String> getStockIncrease( ) {
        return stockService.getStockIncrease();
    }

}
