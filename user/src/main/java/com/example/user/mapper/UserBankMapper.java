package com.example.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.user.model.Banks;
import com.example.user.model.UserBank;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserBankMapper extends BaseMapper<UserBank> {
    void insertUserBanks(UserBank userBank);

    void updateBankById(UserBank userBank);

    List<UserBank> getAllUserBank();
}
