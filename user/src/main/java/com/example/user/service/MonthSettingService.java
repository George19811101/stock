package com.example.user.service;

import com.example.user.common.Result;
import com.example.user.model.bo.MonthSetting;

public interface MonthSettingService {

    Result<String> save(MonthSetting monthSetting);

    Result<String> revoke(long id);

}
