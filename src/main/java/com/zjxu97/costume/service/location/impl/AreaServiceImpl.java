package com.zjxu97.costume.service.location.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.model.entity.location.Area;
import com.zjxu97.costume.mapper.location.AreaMapper;
import com.zjxu97.costume.model.param.LocationParam;
import com.zjxu97.costume.model.vo.LocationVo;
import com.zjxu97.costume.service.location.AreaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * date    2019-08-22
 * time    15:22
 *
 * @author thisxzj - 中建
 */


@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {
    @Override
    public List<LocationVo> listParent(LocationParam locationParam) {
        Integer locationId = locationParam.getLocationId();
        Area area = this.getById(locationId);
        LocationVo locationVo = new LocationVo(0, area.getAreaName(), area.getId());
        ArrayList<LocationVo> locationVoArrayList = new ArrayList<>();
        locationVoArrayList.add(locationVo);
        return locationVoArrayList;
    }
}