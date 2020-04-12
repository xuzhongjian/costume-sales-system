package com.zjxu97.costume.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.mapper.CityMapper;
import com.zjxu97.costume.model.entity.City;
import com.zjxu97.costume.model.vo.LocationVo;
import com.zjxu97.costume.service.CityService;
import com.zjxu97.costume.service.ProvinceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * date    2019-08-22
 * time    15:23
 *
 * @author thisxzj - 中建
 */

@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {

    @Resource
    ProvinceService provinceService;

    @Override
    public List<City> listCityByProv(Integer provId) {
        return this.baseMapper.selectList(qw().eq("province_id", provId));
    }

    @Override
    public List<LocationVo> listParent(int locationId) {
        // 获取本级别的entity
        City city = this.getById(locationId);
        Integer provinceId = city.getProvinceId();

        //获取上级的parentList 其中包含上级别本身的Vo
        List<LocationVo> locationVoList = provinceService.listParent(provinceId);

        //添加本级别的locationVo
        LocationVo locationVo = new LocationVo(provinceId, city.getCityName(), city.getId());

        //添加进返回的list中
        locationVoList.add(locationVo);

        return locationVoList;
    }

    @Override
    public List<City> test() {
        QueryWrapper<City> qw = qw().eq("province_id", "140000").ne("city_name", "太原市")
                .or().eq("province_id", "320000").ne("city_name", "南通市");
        return this.list(qw);
    }

    private QueryWrapper<City> qw() {
        return new QueryWrapper<>();
    }
}
















