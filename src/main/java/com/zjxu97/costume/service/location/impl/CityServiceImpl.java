package com.zjxu97.costume.service.location.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.model.location.City;
import com.zjxu97.costume.mapper.location.CityMapper;
import com.zjxu97.costume.service.location.CityService;
import com.zjxu97.costume.vo.CityVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * date    2019-08-22
 * time    15:23
 *
 * @author thisxzj - 中建
 */

@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {
    @Override
    public List<CityVo> listCityByProv(Integer provId) {
        List<City> cityList = this.baseMapper.selectList(qw().eq("province_id", provId));
        return cityList.stream().map(city -> {
            CityVo cityVo = new CityVo();
            BeanUtils.copyProperties(city, cityVo);
            return cityVo;
        }).collect(Collectors.toList());
    }

    private QueryWrapper<City> qw() {
        return new QueryWrapper<>();
    }
}
