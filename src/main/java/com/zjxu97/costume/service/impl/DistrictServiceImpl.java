package com.zjxu97.costume.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.mapper.DistrictMapper;
import com.zjxu97.costume.model.entity.District;
import com.zjxu97.costume.model.vo.LocationVo;
import com.zjxu97.costume.service.CityService;
import com.zjxu97.costume.service.DistrictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
@Service
public class DistrictServiceImpl extends ServiceImpl<DistrictMapper, District> implements DistrictService {

    @Resource
    CityService cityService;

    @Override
    public List<District> listDistsByCity(Integer cityId) {
        return this.baseMapper.selectList(qw().eq("city_id", cityId));
    }

    @Override
    public List<LocationVo> listParent(int locationId) {
        // 获取本级别的entity
        District district = this.getById(locationId);
        Integer cityId = district.getCityId();

        //获取上级的parentList 其中包含上级别本身的Vo
        List<LocationVo> locationVoList = cityService.listParent(cityId);

        //添加本级别的locationVo
        LocationVo locationVo = new LocationVo(cityId, district.getDistrictName(), district.getId());

        //添加进返回的list中
        locationVoList.add(locationVo);

        return locationVoList;
    }

    private QueryWrapper<District> qw() {
        return new QueryWrapper<>();
    }
}
