package com.zjxu97.costume.service.location;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.entity.location.Area;
import com.zjxu97.costume.model.param.LocationParam;
import com.zjxu97.costume.model.vo.LocationVo;

import java.util.List;

/**
 * date    2019-08-22
 * time    15:13
 *
 * @author thisxzj - 中建
 */


public interface AreaService extends IService<Area> {
    /**
     * 根据地区的等级和地区的id查询它的上级
     *
     * @param locationParam 地区参数
     * @return 返回位置的列表
     */
    List<LocationVo> listParent(LocationParam locationParam);
}

