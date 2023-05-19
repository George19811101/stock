package com.example.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.user.model.bo.DaySetting;
import com.example.user.model.bo.WalletLog;
import com.example.user.model.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WalletLogMapper  extends BaseMapper<WalletLog> {

    int saveWalletLogByEntity( WalletLog walletLog);
}
