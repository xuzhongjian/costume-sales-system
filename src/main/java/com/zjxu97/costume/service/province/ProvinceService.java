package com.zjxu97.costume.service.province;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.Province;
import com.zjxu97.costume.vo.ProvinceVo;

import java.util.List;

public interface ProvinceService extends IService<Province> {
    public List<ProvinceVo> listProvsByArea(Integer areaId);
}
