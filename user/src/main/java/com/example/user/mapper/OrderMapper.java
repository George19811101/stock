package com.example.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.user.model.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    int insertOrder(Order order);

    int updateOrderById(Order order);

    List<Order> getAllOrders(Map map);
}
