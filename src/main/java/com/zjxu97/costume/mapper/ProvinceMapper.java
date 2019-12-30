package com.zjxu97.costume.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjxu97.costume.model.Province;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProvinceMapper extends BaseMapper<Province> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Province record);

    Province selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Province record);

    int updateByPrimaryKey(Province record);
}