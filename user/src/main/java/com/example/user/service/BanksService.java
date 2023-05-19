package com.example.user.service;

import com.example.user.common.Result;
import com.example.user.model.Banks;

import java.util.List;

public interface BanksService {
      Result<String> add(Banks banks);


      Result<String> updateBankById(Banks banks);
      List<Banks> getAllBanks();
}
