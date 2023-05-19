package com.example.user.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@ApiModel(description = "CaptchaBo", value = "CaptchaBo")
public class SendCaptchaBo implements Serializable {

    @ApiModelProperty(value = "接收者的手机号或者邮箱")
    @NotBlank(message = "不能为空")
    private String to;
    @ApiModelProperty(value = "验证码",hidden = true)
    private String code;




}
