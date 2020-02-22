package com.zjxu97.costume.service.location.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.commons.LocationClassConstants;
import com.zjxu97.costume.mapper.location.ProvinceMapper;
import com.zjxu97.costume.model.entity.location.Province;
import com.zjxu97.costume.model.param.LocationParam;
import com.zjxu97.costume.model.vo.LocationVo;
import com.zjxu97.costume.service.location.AreaService;
import com.zjxu97.costume.service.location.ProvinceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
@Service
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province> implements ProvinceService {

    @Resource
    AreaService areaService;

    @Override
    public List<Province> listProvsByArea(Integer areaId) {
        return this.baseMapper.selectList(qw().eq("area_id", areaId));
    }

    @Override
    public List<LocationVo> listParent(LocationParam locationParam) {
        Integer locationId = locationParam.getLocationId();

        // 获取本级别的entity
        Province province = this.getById(locationId);
        Integer areaId = province.getAreaId();

        // 组装上级的查询param
        LocationParam parentParam = new LocationParam();
        parentParam.setLocationId(areaId);
        parentParam.setLocationClass(LocationClassConstants.AREA);

        //获取上级的parentList 其中包含上级别本身的Vo
        List<LocationVo> parentVoList = areaService.listParent(parentParam);

        //添加本级别的locationVo
        LocationVo locationVo = new LocationVo(areaId, province.getProvinceName(), province.getId());

        //添加进返回的list中
        parentVoList.add(locationVo);

        return parentVoList;
    }

    private QueryWrapper<Province> qw() {
        return new QueryWrapper<>();
    }
}
