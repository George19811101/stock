package com.example.user.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_order")
public class Order {
    @ApiModelProperty(value = "主键id")
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
