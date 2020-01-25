package com.zjxu97.costume.mapper.location;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjxu97.costume.model.entity.location.Area;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AreaMapper extends BaseMapper<Area> {
    int insertSelective(Area record);
}