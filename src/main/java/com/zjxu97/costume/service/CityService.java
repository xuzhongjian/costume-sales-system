package com.zjxu97.costume.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.entity.City;
import com.zjxu97.costume.model.vo.LocationVo;

import java.util.List;

/**
 * date    2019-08-22
 * time    15:14
 *
 * @author thisxzj - 中建
 */

public interface CityService extends IService<City> {

    /**
     * 根据省份的id获取下面的城市
     *
     * @param provId 省份ID
     * @return 下属城市
     */
    List<City> listCityByProv(Integer provId);

    /**
     * 根据地区的等级和地区的id查询它的上级
     *
     * @return 返回位置的列表
     */
    List<LocationVo> listParent(int locationId);

    List<City> test();
}
