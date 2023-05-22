package com.example.user.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_bank")
public class UserBank {
    @ApiModelProperty(value = "主键id")
    private long  id;
    private long userId;
    private long bankId;
    private String card;
    private String name;
    private String address;


}
