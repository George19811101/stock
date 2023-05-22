package com.example.user.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
@ApiModel(description = "updateTradePassword", value = "updateTradePassword")
public class updateTradePassword {

    @ApiModelProperty(value = "用户Id")
    @NotBlank(message = "用户Id不能为空")
    private long userId;

    @ApiModelProperty(value = "原先密码")
    @NotBlank(message = "原先密码不能为空")
    private String oldTradePassword;

    @ApiModelProperty(value = "新密码")
    @NotBlank(message = "新密码不能为空")
    private String newTradePassword;

    @ApiModelProperty(value = "重新输入新密码")
    @NotBlank(message = "重新输入不能为空")
    private String renewTradePassword;
}
