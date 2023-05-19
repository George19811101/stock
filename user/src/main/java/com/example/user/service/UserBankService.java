package com.example.user.service;

import com.example.user.common.Result;
import com.example.user.model.UserBank;

import java.util.List;

public interface UserBankService {
    Result<String> add(UserBank userBank);

    Result<String> updateBankById(UserBank userBank);

    List getAllBanks();
}
