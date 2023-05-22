package com.example.user.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("banks")
public class Banks {
   @ApiModelProperty(value = "主键id")
   private long  id;
   private String bank;
   private String bankCode;
   private int b2c;

}
