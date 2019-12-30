package com.zjxu97.costume.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjxu97.costume.model.District;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DistrictMapper extends BaseMapper<District> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(District record);

    District selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);
}