package com.example.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.user.model.bo.DaySetting;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DaySettingMapper extends BaseMapper<DaySetting> {
    int insertDaySetting(DaySetting daySetting);

    List<DaySetting> selectByUnSettled();

    int updateDaySetting(DaySetting ds);
}
