package com.example.user.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order {

    private long id;
    private long userId;

    private BigDecimal amount;
    private String type;
    private Date createTime;
    private int result;
    private String channel;
    private long stockId;
    private long bankId;


}
