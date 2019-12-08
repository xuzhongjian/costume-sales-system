package com.zjxu97.costume.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjxu97.costume.entity.Area;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AreaMapper extends BaseMapper<Area> {
    int insert(Area record);

    int insertSelective(Area record);
}