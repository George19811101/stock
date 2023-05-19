package com.example.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.example.user.model.vo.StockVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StockMapper  extends BaseMapper<StockVo> {
    StockVo getByCode(@Param("code") String code);

    int saveStockVoByEntity(StockVo stockVo);
}
