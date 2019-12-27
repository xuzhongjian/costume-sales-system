package com.zjxu97.costume.service.district;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.District;
import com.zjxu97.costume.vo.DistrictVo;

import java.util.List;

public interface DistrictService extends IService<District> {
    public List<DistrictVo> listDistsByCity(Integer cityId);
}
