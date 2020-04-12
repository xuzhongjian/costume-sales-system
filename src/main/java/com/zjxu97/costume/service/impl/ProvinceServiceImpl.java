package com.zjxu97.costume.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.mapper.ProvinceMapper;
import com.zjxu97.costume.model.entity.Province;
import com.zjxu97.costume.model.vo.LocationVo;
import com.zjxu97.costume.service.AreaService;
import com.zjxu97.costume.service.ProvinceService;
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
    public List<LocationVo> listParent(int locationId) {

        // 获取本级别的entity
        Province province = this.getById(locationId);
        Integer areaId = province.getAreaId();

        //获取上级的parentList 其中包含上级别本身的Vo
        List<LocationVo> parentVoList = areaService.listParent(areaId);

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
