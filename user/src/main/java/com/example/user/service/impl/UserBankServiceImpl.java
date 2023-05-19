package com.example.user.service.impl;

import com.example.user.common.Result;
import com.example.user.mapper.BanksMapper;
import com.example.user.mapper.UserBankMapper;
import com.example.user.model.UserBank;
import com.example.user.service.UserBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserBankServiceImpl implements UserBankService {
    @Autowired
    private UserBankMapper userBankMapper;
    @Override
    public Result<String> add(UserBank userBank) {
        userBankMapper.insertUserBanks(userBank);
        return Result.ok();
    }

    @Override
    public Result<String> updateBankById(UserBank userBank) {
        userBankMapper.updateBankById(userBank);
        return Result.ok();
    }

    @Override
    public List getAllBanks() {
        List<UserBank> userBankList=userBankMapper.getAllUserBank();
        return userBankList;
    }
}
