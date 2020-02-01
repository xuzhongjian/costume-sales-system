package com.zjxu97.costume.service.location.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.commons.LocationClassConstants;
import com.zjxu97.costume.model.entity.location.City;
import com.zjxu97.costume.mapper.location.CityMapper;
import com.zjxu97.costume.model.param.LocationParam;
import com.zjxu97.costume.model.vo.LocationVo;
import com.zjxu97.costume.service.location.CityService;
import com.zjxu97.costume.model.vo.CityVo;
import com.zjxu97.costume.service.location.ProvinceService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Resource
    ProvinceService provinceService;

    @Override
    public List<City> listCityByProv(Integer provId) {
        return this.baseMapper.selectList(qw().eq("province_id", provId));
    }

    @Override
    public List<LocationVo> listParent(LocationParam locationParam) {
        Integer locationId = locationParam.getLocationId();

        // 获取本级别的entity
        City city = this.getById(locationId);
        Integer provinceId = city.getProvinceId();

        //组装查询上级的param
        LocationParam parentParam = new LocationParam();
        parentParam.setLocationId(provinceId);
        parentParam.setLocationClass(LocationClassConstants.PROVINCE);

        //获取上级的parentList 其中包含上级别本身的Vo
        List<LocationVo> locationVoList = provinceService.listParent(parentParam);

        //添加本级别的locationVo
        LocationVo locationVo = new LocationVo();
        locationVo.setLocationId(city.getId());
        locationVo.setLocationName(city.getCityName());
        locationVo.setParentId(provinceId);

        //添加进返回的list中
        locationVoList.add(locationVo);

        return locationVoList;
    }

    private QueryWrapper<City> qw() {
        return new QueryWrapper<>();
    }
}
















