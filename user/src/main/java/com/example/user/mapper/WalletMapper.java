package com.example.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.user.model.bo.Wallet;
import com.example.user.model.bo.WalletLog;
import com.example.user.model.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WalletMapper  extends BaseMapper<Wallet> {
    Wallet getWalletByUserId(@Param("userId") long userId);

    int saveWalletByEntity( Wallet  wallet );

    int updateWalletById(Wallet wallet);
}
