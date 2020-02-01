package com.zjxu97.costume.service.location;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.entity.location.City;
import com.zjxu97.costume.model.param.LocationParam;
import com.zjxu97.costume.model.vo.CityVo;
import com.zjxu97.costume.model.vo.LocationVo;

import java.util.List;

/**
 * date    2019-08-22
 * time    15:14
 *
 * @author thisxzj - 中建
 */

public interface CityService extends IService<City> {
    List<City> listCityByProv(Integer provId);

    List<LocationVo> listParent(LocationParam locationParam);
}
