package com.zjxu97.costume.service.location;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.entity.location.District;
import com.zjxu97.costume.model.vo.DistrictVo;

import java.util.List;

public interface DistrictService extends IService<District> {
    List<DistrictVo> listDistsByCity(Integer cityId);
}
