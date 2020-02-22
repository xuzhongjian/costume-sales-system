package com.zjxu97.costume.service.location;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.entity.location.Province;
import com.zjxu97.costume.model.param.LocationParam;
import com.zjxu97.costume.model.vo.LocationVo;

import java.util.List;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
public interface ProvinceService extends IService<Province> {
    /**
     * 获取大区域的所有省份
     *
     * @param areaId 大区域id
     * @return 省份列表
     */
    List<Province> listProvsByArea(Integer areaId);

    /**
     * 根据地区的等级和地区的id查询它的上级
     *
     * @param locationParam 地区参数
     * @return 返回位置的列表
     */
    List<LocationVo> listParent(LocationParam locationParam);

}
