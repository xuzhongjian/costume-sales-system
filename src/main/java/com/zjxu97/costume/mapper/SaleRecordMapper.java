package com.zjxu97.costume.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjxu97.costume.entity.SaleRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SaleRecordMapper extends BaseMapper<SaleRecord> {
    int deleteByPrimaryKey(Integer id);

    int insert(SaleRecord record);

    int insertSelective(SaleRecord record);

    SaleRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SaleRecord record);

    int updateByPrimaryKey(SaleRecord record);
}