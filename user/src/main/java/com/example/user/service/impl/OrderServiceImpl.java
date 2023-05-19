package com.example.user.service.impl;

import com.example.user.common.Result;
import com.example.user.mapper.BanksMapper;
import com.example.user.mapper.OrderMapper;
import com.example.user.model.Banks;
import com.example.user.model.Order;
import com.example.user.model.UserBank;
import com.example.user.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public Result<String> add(Order order) {
        orderMapper.insertOrder(order);
        return Result.ok();
    }

    @Override
    public Result<String> updateOrderById(Order order) {
        orderMapper.updateOrderById(order);
        return Result.ok();
    }

    @Override
    public List<Order> getAllOrders(Map map) {
        List<Order> userBankList=orderMapper.getAllOrders(map);
        return userBankList;
    }
}
