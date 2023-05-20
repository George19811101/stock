package com.example.user.controller;

import com.example.user.common.Result;
import com.example.user.model.Order;
import com.example.user.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

@Api(tags = "定单信息")
@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @ApiOperation("添加银行信息")
    @PostMapping("/add")
    public    Callable<String>  add(@RequestBody Order order) {
        log.info("主线程开始");
        //使用Runnable异步处理Rest服务
        //开启一个线程,方便以后多个用户下单时出现等待现象。
        Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                // TODO Auto-generated method stub
                log.info("副线程开始");
                Thread.sleep(1000);
                log.info("副线程结束");
                orderService.add(order);
                return "success";
            }
        };
        log.info("主线程结束");
        return result;
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
