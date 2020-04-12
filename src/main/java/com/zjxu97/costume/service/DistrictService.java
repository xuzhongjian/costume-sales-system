package com.zjxu97.costume.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.entity.District;
import com.zjxu97.costume.model.vo.LocationVo;

import java.util.List;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
public interface DistrictService extends IService<District> {
    /**
     * 更具城市id 获取下属的区
     *
     * @param cityId 城市id
     * @return 区
     */
    List<District> listDistsByCity(Integer cityId);

    /**
     * 根据地区的等级和地区的id查询它的上级
     *
     * @param locationParam 地区参数
     * @return 返回位置的列表
     */
    List<LocationVo> listParent(int locationId);
}
