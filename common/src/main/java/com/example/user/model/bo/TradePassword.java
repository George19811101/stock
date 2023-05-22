package com.example.user.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
@Data
@Accessors(chain = true)
@ApiModel(description = "TradePassword", value = "TradePassword")
public class TradePassword {

    @ApiModelProperty(value = "用户Id")
    @NotBlank(message = "用户Id不能为空")
    private long userId;


    @ApiModelProperty(value = "新提现密码")
    @NotBlank(message = "新提现密码不能为空")
    private String newTradePassword;

    @ApiModelProperty(value = "重新输入新提现密码")
    @NotBlank(message = "重新输入新提现密码不能为空")
    private String renewTradePassword;

}
