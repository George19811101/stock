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
@ApiModel(description = "DaySetting", value = "DaySetting")
public class WalletLog {

    @Id
    @ApiModelProperty(value = "主键id")
    private long id;

    @ApiModelProperty(value = "用户Id")
    @NotBlank(message = "用户Id不能为空")
    private long userId;
    @ApiModelProperty(value = "金额")
    @NotBlank(message = "金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "是进帐还是出帐，1表示进帐，0表示出帐")
    @NotBlank(message = "是进帐还是出帐，1表示进帐，0表示出帐")
    private Integer inOut;

    @ApiModelProperty(value = "创建时间")
    @NotBlank(message = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "使用奖金说明")
    @NotBlank(message = "使用奖金说明")
    private String  descr;

}
