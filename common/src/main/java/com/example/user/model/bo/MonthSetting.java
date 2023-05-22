package com.example.user.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@ApiModel(description = "MonthSetting", value = "MonthSetting")
public class MonthSetting {
    @Id
    @ApiModelProperty(value = "主键id")
    private long id;
    @ApiModelProperty(value = "用户Id")
    @NotBlank(message = "用户Id不能为空")
    private long userId;
    @ApiModelProperty(value = "投资本金")
    @NotBlank(message = "投资本金")
    private BigDecimal investAmount;


    @ApiModelProperty(value = "杠杆")
    @NotBlank(message = "杠杆不能为空")
    private Integer levels;

    @ApiModelProperty(value = "配资周期")
    @NotBlank(message = "配资周期不能为空")
    private Integer fundCycle;

    @ApiModelProperty(value = "支付金额")
    @NotBlank(message = "支付金额不能为空")
    private BigDecimal cost;
    @ApiModelProperty(value = "总配资资金")
    @NotBlank(message = "总配资资金不能为空")
    private BigDecimal totalAllocationFund;

    @ApiModelProperty(value = "亏损平仓线")
    @NotBlank(message = "亏损平仓线不能为空")
    private BigDecimal lossCloseLine;

    @ApiModelProperty(value = "亏损警戒线")
    @NotBlank(message = "亏损警戒线不能为空")
    private BigDecimal lossWarnLine;

    @ApiModelProperty(value = "合同是否阅读")
    @NotBlank(message = "合同是否阅读不能为空")
    private Integer isread;

    @ApiModelProperty(value = "开始时间")
    @NotBlank(message = "开始时间不能为空")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    @NotBlank(message = "结束时间不能为空")
    private String endTime;

    private int isSettled;
}
