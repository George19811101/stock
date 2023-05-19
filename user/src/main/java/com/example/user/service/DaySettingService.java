package com.example.user.service;

import com.example.user.common.Result;
import com.example.user.model.bo.DaySetting;

public interface DaySettingService {


    Result<String> save(DaySetting daySetting);

    Result<String> revoke(long id);
}
