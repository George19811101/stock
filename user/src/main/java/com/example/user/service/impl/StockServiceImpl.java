package com.example.user.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.example.user.common.Result;
import com.example.user.mapper.StockMapper;
import com.example.user.mapper.UserMapper;
import com.example.user.model.vo.StockVo;
import com.example.user.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockMapper stockMapper;

    private String url = "https://stockapi.com.cn/v1/base/dayAll";

    @Override
    public Result<String> getStockIncrease() {
        JSONObject result2 = JSON.parseObject(HttpUtil.get(url));

        String arrObj = result2.getString("data");


        List<StockVo> stockVoList = JSON.parseArray(arrObj, StockVo.class);
        if (CollectionUtils.isNotEmpty(stockVoList)) {
            for (StockVo stockVo : stockVoList) {
                StockVo stockVotemp = stockMapper.getByCode(stockVo.getCode());
                if (ObjectUtil.isNull(stockVotemp)) {
                    stockMapper.saveStockVoByEntity(stockVo);
                }
            }
            System.out.println("总存储：" + stockVoList.size());
        }


        return Result.ok();
    }
}
