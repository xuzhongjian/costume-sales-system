package com.zjxu97.costume.service.location;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.location.Province;
import com.zjxu97.costume.vo.ProvinceVo;

import java.util.List;

public interface ProvinceService extends IService<Province> {
    List<ProvinceVo> listProvsByArea(Integer areaId);
}
