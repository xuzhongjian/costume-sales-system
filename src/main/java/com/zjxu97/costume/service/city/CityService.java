package com.zjxu97.costume.service.city;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.City;
import com.zjxu97.costume.vo.CityVo;

import java.util.List;

/**
 * date    2019-08-22
 * time    15:14
 *
 * @author thisxzj - 中建
 */

public interface CityService extends IService<City> {
    public List<CityVo> listCitysByProv(Integer provId);
}
