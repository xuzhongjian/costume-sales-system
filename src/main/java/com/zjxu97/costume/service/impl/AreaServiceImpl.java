package com.zjxu97.costume.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.mapper.AreaMapper;
import com.zjxu97.costume.model.entity.Area;
import com.zjxu97.costume.model.vo.LocationVo;
import com.zjxu97.costume.service.AreaService;
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
    public List<LocationVo> listParent(int locationId) {

        Area area = this.getById(locationId);
        LocationVo locationVo = new LocationVo(0, area.getAreaName(), area.getId());
        ArrayList<LocationVo> locationVoArrayList = new ArrayList<>();
        locationVoArrayList.add(locationVo);
        return locationVoArrayList;
    }
}