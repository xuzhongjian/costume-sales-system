package com.zjxu97.costume.service.location.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.commons.LocationClassConstants;
import com.zjxu97.costume.mapper.location.DistrictMapper;
import com.zjxu97.costume.model.entity.location.District;
import com.zjxu97.costume.model.param.LocationParam;
import com.zjxu97.costume.model.vo.LocationVo;
import com.zjxu97.costume.service.location.CityService;
import com.zjxu97.costume.service.location.DistrictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DistrictServiceImpl extends ServiceImpl<DistrictMapper, District> implements DistrictService {

    @Resource
    CityService cityService;

    public List<District> listDistsByCity(Integer cityId) {
        return this.baseMapper.selectList(qw().eq("city_id", cityId));
    }

    @Override
    public List<LocationVo> listParent(LocationParam locationParam) {
        Integer locationId = locationParam.getLocationId();

        // 获取本级别的entity
        District district = this.getById(locationId);
        Integer cityId = district.getCityId();

        //组装查询上级的param
        LocationParam parentParam = new LocationParam();
        parentParam.setLocationId(cityId);
        parentParam.setLocationClass(LocationClassConstants.CITY);

        //获取上级的parentList 其中包含上级别本身的Vo
        List<LocationVo> locationVoList = cityService.listParent(parentParam);

        //添加本级别的locationVo
        LocationVo locationVo = new LocationVo();
        locationVo.setLocationId(district.getId());
        locationVo.setLocationName(district.getDistrictName());
        locationVo.setParentId(cityId);

        //添加进返回的list中
        locationVoList.add(locationVo);

        return locationVoList;
    }

    private QueryWrapper<District> qw() {
        return new QueryWrapper<>();
    }
}
