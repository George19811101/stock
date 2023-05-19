package com.example.user.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@ApiModel(description = "UserVo", value = "UserVo")
public class UserVo implements Serializable {

    @ApiModelProperty(value = "主键id")
    private String id;
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
    @ApiModelProperty(value = "用户token")
    private String token;
    private String headImgUrl;
    private String tradePassword;

    private int completeLevel;

    private String invitationCode;


    private String parentId;
}
