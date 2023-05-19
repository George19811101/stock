package com.example.user.service.impl;

import com.example.user.common.Result;
import com.example.user.mapper.BanksMapper;
import com.example.user.mapper.UserMapper;
import com.example.user.model.Banks;


import com.example.user.service.BanksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BanksServiceImpl implements BanksService {
    @Autowired
    private BanksMapper banksMapper;

    @Override
    public Result<String> add(Banks banks) {
        banksMapper.insertBanks(banks);
        return Result.ok();
    }

    @Override
    public Result<String> updateBankById(Banks banks) {
        banksMapper.updateBankById(banks);
        return Result.ok();
    }

    @Override
    public List<Banks> getAllBanks() {
        return banksMapper.getAllBanks();
    }
}
