package com.example.user.service.task;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.example.user.mapper.DaySettingMapper;
import com.example.user.mapper.WalletLogMapper;
import com.example.user.mapper.WalletMapper;
import com.example.user.model.bo.DaySetting;
import com.example.user.model.bo.Wallet;
import com.example.user.model.bo.WalletLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Slf4j
@Component
public class DaySettingTask {
    @Resource
    private DaySettingMapper daySettingMapper;
    @Resource
    private WalletLogMapper walletLogMapper;
    @Resource
    private WalletMapper walletMapper;

    @Async("threadPoolTaskScheduler")
//    @Scheduled(cron = "0 0/1  * * * ? ")
    public void daySettledSettingTask() {
        log.info("日配置结算开始");
        List<DaySetting> daySettingList=daySettingMapper.selectByUnSettled();
        if(CollectionUtils.isNotEmpty(daySettingList)){
            String dateToday = DateUtil.format(new Date(), "yyyy-MM-dd");
            for(DaySetting ds:daySettingList){
               if(dateToday.compareTo(ds.getEndTime())>0){
                   //查以对daySetting进行结算，怎么结算，暂时不知道
                   //先生成钱包日志，先写入钱包日志
                   WalletLog walletLog=new WalletLog();
                   walletLog.setAmount(ds.getTotalAllocationFund().subtract(ds.getInvestAmount()))
                           .setDescr("按天配资结算，系统收回融资资金")
                           .setUserId(ds.getUserId())
                           .setInOut(0);
                   walletLogMapper.saveWalletLogByEntity(walletLog);
                   //再对用户的钱包进行加钱
                   Wallet wallet=walletMapper.getWalletByUserId(ds.getUserId());
                   BigDecimal afterBalance=wallet.getAfterBalance();
                   wallet.setBeforeBalance(afterBalance);
                   wallet.setAfterBalance(afterBalance.subtract(walletLog.getAmount()));
                   wallet.setWalletLogId(walletLog.getId());
                   walletMapper.updateWalletById(wallet);
                   ////再对用户的日配信息进行终止修改
                    ds.setIsSettled(1);
                   daySettingMapper.updateDaySetting(ds);
               }
            }
        }

        Date date = new Date();
    }
}
