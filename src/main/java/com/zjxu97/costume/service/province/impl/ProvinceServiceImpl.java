package com.zjxu97.costume.service.province.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.model.Province;
import com.zjxu97.costume.mapper.ProvinceMapper;
import com.zjxu97.costume.service.province.ProvinceService;
import com.zjxu97.costume.vo.ProvinceVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province> implements ProvinceService {

    public List<ProvinceVo> listProvsByArea(Integer areaId) {
        List<Province> provinces = this.baseMapper.selectList(qw().eq("area_id", areaId));
        return provinces.stream().map(province -> {
            ProvinceVo provinceVo = new ProvinceVo();
            BeanUtils.copyProperties(province, provinceVo);
            return provinceVo;
        }).collect(Collectors.toList());
    }

    private QueryWrapper<Province> qw() {
        return new QueryWrapper<>();
    }
}
