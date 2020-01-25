package com.zjxu97.costume.service.location;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.entity.location.Province;
import com.zjxu97.costume.model.vo.ProvinceVo;

import java.util.List;

public interface ProvinceService extends IService<Province> {
    List<ProvinceVo> listProvsByArea(Integer areaId);
}
