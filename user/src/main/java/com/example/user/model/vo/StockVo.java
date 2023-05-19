package com.example.user.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "StockVo", value = "StockVo")
public class StockVo {
     private long id;
     private String code;
     private String time;
     private String open;
     private String turnoverRatio;
     private String amount ;
     private String change;
     private String high;
     private String low;
     private String changeRatio;
     private String close;
     private String volume;
}
