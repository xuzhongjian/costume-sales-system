package com.zjxu97.costume.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjxu97.costume.entity.SalesRecord;

public interface SalesRecordMapper extends BaseMapper<SalesRecord> {
    int deleteByPrimaryKey(Integer id);

    int insert(SalesRecord record);

    int insertSelective(SalesRecord record);

    SalesRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SalesRecord record);

    int updateByPrimaryKey(SalesRecord record);
}