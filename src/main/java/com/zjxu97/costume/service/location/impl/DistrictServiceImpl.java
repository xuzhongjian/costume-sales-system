package com.zjxu97.costume.service.location.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.model.entity.location.District;
import com.zjxu97.costume.mapper.location.DistrictMapper;
import com.zjxu97.costume.service.location.DistrictService;
import com.zjxu97.costume.model.vo.DistrictVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistrictServiceImpl extends ServiceImpl<DistrictMapper, District> implements DistrictService {
    public List<DistrictVo> listDistsByCity(Integer cityId) {
        List<District> districtList = this.baseMapper.selectList(qw().eq("city_id", cityId));
        return districtList.stream().map(district -> {
            DistrictVo districtVo = new DistrictVo();
            BeanUtils.copyProperties(district, districtVo);
            return districtVo;
        }).collect(Collectors.toList());
    }

    private QueryWrapper<District> qw() {
        return new QueryWrapper<>();
    }
}
