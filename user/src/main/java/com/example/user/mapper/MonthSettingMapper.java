package com.example.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.user.model.bo.DaySetting;
import com.example.user.model.bo.MonthSetting;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MonthSettingMapper extends BaseMapper<MonthSetting> {
    int insertMonthSetting(MonthSetting monthSetting);

    List<MonthSetting> selectByUnSettled();

    int updateMonthSetting(MonthSetting ds);
}
