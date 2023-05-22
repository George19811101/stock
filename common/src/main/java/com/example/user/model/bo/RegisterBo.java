package com.example.user.model.bo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@ApiModel(description = "RegisterBo", value = "RegisterBo")
public class RegisterBo implements Serializable {

    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String phoneNo;

    @ApiModelProperty(value = "验证码")
    @NotBlank(message = "验证码不能为空")
    private String captcha;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{8,12}",message = "请输入8~12位英文字母加数字的混合密码")
    private String password;

    @ApiModelProperty(value = "确认密码")
    @NotBlank(message = "确认密码不能为空")
    private String confirmPassword;



    @ApiModelProperty(value = "区号")
    private String areaCode;
    private int completeLevel;

    private String tradePassword;

    @ApiModelProperty(value = "邀请码")
    private String invitationCode;

}
