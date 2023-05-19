package com.example.user.service.impl;

import cn.hutool.core.date.DateUtil;
import com.example.user.common.ExceptionEnum;
import com.example.user.common.Result;
import com.example.user.mapper.DaySettingMapper;
import com.example.user.mapper.UserMapper;
import com.example.user.mapper.WalletLogMapper;
import com.example.user.mapper.WalletMapper;
import com.example.user.model.bo.DaySetting;
import com.example.user.model.bo.Wallet;
import com.example.user.model.bo.WalletLog;
import com.example.user.service.DaySettingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@Service
public class DaySettingServiceImpl implements DaySettingService {
    @Autowired
    private DaySettingMapper daySettingMapper;
    @Autowired
    private WalletLogMapper walletLogMapper;
    @Autowired
    private WalletMapper walletMapper;
    @Override
    @Transactional
    public Result<String> save(DaySetting daySetting) {
        /**
         * 这里面涉及到奖金的出入问题，最好使用事务进行处理
         */
        //获取当前用户的钱包
        Wallet wallet=walletMapper.getWalletByUserId(daySetting.getUserId());
        int result = daySetting.getCost().compareTo(wallet.getAfterBalance());
        //用户的钱包余额不够支付
        if(result>0){
            return Result.exception(ExceptionEnum.NO_ENOUGHT_MONEY);
        }
        //先写walletlog日志表
        WalletLog walletLog=new WalletLog();
        walletLog.setUserId(daySetting.getUserId())
                 .setInOut(1)
                .setAmount(daySetting.getTotalAllocationFund().subtract(daySetting.getCost()))
                .setDescr("按天配资加款进钱包");
        walletLogMapper.saveWalletLogByEntity(walletLog);
        BigDecimal  afterAmount=wallet.getAfterBalance();
        wallet.setBeforeBalance(afterAmount) ;
        wallet.setAfterBalance(afterAmount.add(walletLog.getAmount()));
        wallet.setWalletLogId(walletLog.getId());
        walletMapper.updateWalletById(wallet);

        daySettingMapper.insertDaySetting(daySetting);
        return Result.ok();
    }

    /**
     * 用户撤销当前的时间在开始之前，可以进行撤销日配信息，但是管理费用不会退还，只通还购买的原始投资部分
     * @param id 是退销哪一个日配的id
     * @return
     */

    @Override
    @Transactional
    public Result<String> revoke(long id) {
        String dateToday = DateUtil.format(new Date(), "yyyy-MM-dd");

        DaySetting daySetting=daySettingMapper.selectById(id);
        //这次日配还没有开始，可以进行退款，但是管理费用不退。
        if(dateToday.compareTo(daySetting.getStartTime())<0) {
            //查以对daySetting进行结算，怎么结算，暂时不知道
            //先生成钱包日志，先写入钱包日志
            WalletLog walletLog=new WalletLog();
            walletLog.setAmount(daySetting.getTotalAllocationFund().subtract(daySetting.getInvestAmount()))
                    .setDescr("按天配资撤销减钱")
                    .setUserId(daySetting.getUserId())
                    .setInOut(0);
            walletLogMapper.saveWalletLogByEntity(walletLog);
            //再对用户的钱包进行加钱
            Wallet wallet=walletMapper.getWalletByUserId(daySetting.getUserId());
            BigDecimal afterBalance=wallet.getAfterBalance();
            wallet.setBeforeBalance(afterBalance);
            int flag = wallet.getAfterBalance().compareTo(walletLog.getAmount());
            if(flag<=0){
                return Result.exception(ExceptionEnum.NO_ENOUGHT_MONEY);
            }
            wallet.setAfterBalance(afterBalance.subtract(walletLog.getAmount()));
            wallet.setWalletLogId(walletLog.getId());
            walletMapper.updateWalletById(wallet);
            ////再对用户的日配信息进行终止修改
            daySetting.setIsSettled(1);
            daySettingMapper.updateDaySetting(daySetting);

        }else{
            return Result.exception(ExceptionEnum.SCHEDULE_IS_START);
        }
        return Result.ok();
    }
}
