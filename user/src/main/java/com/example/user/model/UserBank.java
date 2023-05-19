package com.example.user.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserBank {
    @ApiModelProperty(value = "主键id")
    private long  id;
    private long userId;
    private long bankId;
    private String code;
    private String name;
    private String address;


}
