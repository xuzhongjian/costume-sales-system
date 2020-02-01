package com.zjxu97.costume.service.location;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.entity.location.District;
import com.zjxu97.costume.model.param.LocationParam;
import com.zjxu97.costume.model.vo.LocationVo;

import java.util.List;

public interface DistrictService extends IService<District> {
    List<District> listDistsByCity(Integer cityId);

    List<LocationVo> listParent(LocationParam locationParam);
}
