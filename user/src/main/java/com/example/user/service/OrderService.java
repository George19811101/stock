package com.example.user.service;

import com.example.user.common.Result;
import com.example.user.model.Banks;
import com.example.user.model.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
      Result<String> add(Order order);


      Result<String> updateOrderById(Order order);
      List<Order> getAllOrders(Map map);
}
