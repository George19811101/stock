package com.example.user.model.bo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@ApiModel(description = "UserBo", value = "UserBo")
public class UserBo implements Serializable {

    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String phoneNo;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String loginPassword;

    @ApiModelProperty(value = "区号")
    @NotBlank(message = "区号不能为空")
    private String areaCode;

    private String headImgUrl;
    private String tradePassword;

    private String invitationCode;


    private String parentId;

}
