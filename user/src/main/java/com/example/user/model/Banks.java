package com.example.user.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Banks {
   @ApiModelProperty(value = "主键id")
   private long  id;
   private String bank;
   private String bankCode;
   private int b2c;

}
