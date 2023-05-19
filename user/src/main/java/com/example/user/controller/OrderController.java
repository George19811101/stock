package com.example.user.controller;

import com.example.user.common.Result;
import com.example.user.model.Order;
import com.example.user.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "股票信息")
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @ApiOperation("添加银行信息")
    @PostMapping("/add")
    public Result<String> add(@RequestBody Order order) {
        return orderService.add(order);
    }

    @ApiOperation("修改银行信息")
    @PostMapping("/update")
    public Result<String> update(@RequestBody Order order) {
        return orderService.updateOrderById(order);
    }

    @ApiOperation("所有的银行信息")
    @PostMapping("/getAllBanks")
    public Result<List<Order>> getAllBanks(@RequestBody Map map ) {
        List orderList=orderService.getAllOrders(map);
        return Result.ok(orderList);
    }


}
