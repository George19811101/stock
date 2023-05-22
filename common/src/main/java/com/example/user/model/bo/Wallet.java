package com.example.user.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain = true)
@ApiModel(description = "Wallet", value = "Wallet")
public class Wallet {
    @Id
    @ApiModelProperty(value = "主键id")
    private long id;
    @ApiModelProperty(value = "用户Id")
    @NotBlank(message = "用户Id不能为空")
    private long userId;
    @ApiModelProperty(value = "使用后的金额")
    @NotBlank(message = "使用后的金额")
    private BigDecimal afterBalance;
    @ApiModelProperty(value = "使用前的金额")
    @NotBlank(message = "使用前的金额")
    private BigDecimal beforeBalance;
    @ApiModelProperty(value = "使用后的冻结金额")
    @NotBlank(message = "使用后的冻结金额")
    private BigDecimal afterFreeze;
    @ApiModelProperty(value = "使用前的冻结金额")
    @NotBlank(message = "使用前的冻结金额")
    private BigDecimal beforeFreeze;


    private Date createTime;
    private Date updateTime;

    private long walletLogId;

}
