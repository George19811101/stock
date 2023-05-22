package com.example.user.model.po;

//import com.example.user.common.IdWorker;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * User实体
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "User", value = "User")
public class User implements Serializable {

    @Id
    @ApiModelProperty(value = "主键id")
    private long id;

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

    private long invitationCode;
    private int status;
    private int completeLevel;


    private long parentId;
    private Date createTime;

}
