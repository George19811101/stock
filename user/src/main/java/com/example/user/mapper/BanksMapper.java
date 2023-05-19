package com.example.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.user.model.Banks;
import com.example.user.model.bo.DaySetting;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BanksMapper extends BaseMapper<Banks> {
    int insertBanks(Banks banks);

    int updateBankById(Banks banks);

    List<Banks> getAllBanks();
}
