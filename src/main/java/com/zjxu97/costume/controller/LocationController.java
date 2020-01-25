package com.zjxu97.costume.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.zjxu97.costume.commons.Constants;
import com.zjxu97.costume.commons.Rx;
import com.zjxu97.costume.model.entity.location.Area;
import com.zjxu97.costume.model.entity.location.City;
import com.zjxu97.costume.model.entity.location.Province;
import com.zjxu97.costume.service.location.AreaService;
import com.zjxu97.costume.service.location.CityService;
import com.zjxu97.costume.service.location.DistrictService;
import com.zjxu97.costume.service.location.ProvinceService;
import com.zjxu97.costume.model.vo.AreaVo;
import com.zjxu97.costume.model.vo.CityVo;
import com.zjxu97.costume.model.vo.DistrictVo;
import com.zjxu97.costume.model.vo.ProvinceVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zjxu97
 * @date 2019/12/28 01:37
 */
@RestController
@Api(tags = "位置相关")
@RequestMapping(Constants.API_PREFIX + "/location")
public class LocationController {
    private final static Logger log = LoggerFactory.getLogger(LocationController.class);

    @Resource
    private AreaService areaService;

    @Resource
    private ProvinceService provinceService;

    @Resource
    private CityService cityService;

    @Resource
    private DistrictService districtService;

    @ApiOperation(value = "列出所有地区")
    @GetMapping(value = "list-areas")
    public R<List<AreaVo>> listArea() {
        List<Area> areas = areaService.list(null);
        List<AreaVo> areaVos = areas.stream().map(area -> {
            AreaVo areaVo = new AreaVo();
            BeanUtils.copyProperties(area, areaVo);
            return areaVo;
        }).collect(Collectors.toList());
        return Rx.success(areaVos);
    }

    @ApiOperation(value = "按照地区列出所有省份")
    @GetMapping(value = "list-provs-by-area")
    public R<List<ProvinceVo>> listProvsByArea(@RequestParam Integer areaId) {
        List<ProvinceVo> provinceVos = provinceService.listProvsByArea(areaId);
        return Rx.success(provinceVos);
    }

    @ApiOperation(value = "按照省份列出所有城市")
    @GetMapping(value = "list-citys-by-prov")
    public R<List<CityVo>> listCitysByProv(@RequestParam Integer provId) {
        List<CityVo> cityVos = cityService.listCityByProv(provId);
        return Rx.success(cityVos);
    }

    @ApiOperation(value = "按照城市列出所有区县")
    @GetMapping(value = "list-dists-by-city")
    public R<List<DistrictVo>> listDistsByCity(@RequestParam Integer cityId) {
        List<DistrictVo> districtVos = districtService.listDistsByCity(cityId);
        return Rx.success(districtVos);
    }

    @ApiOperation(value = "获取县区所属的城市")
    @GetMapping(value = "get-city-by-dist")
    public R<CityVo> getCityByDist(@RequestParam Integer distId) {
        Integer cityId = districtService.getById(distId).getCityId();
        City city = cityService.getById(cityId);
        CityVo cityVo = new CityVo();
        BeanUtils.copyProperties(city, cityVo);
        return Rx.success(cityVo);
    }

    @ApiOperation(value = "获取城市所属的省份")
    @GetMapping(value = "get-prov-by-city")
    public R<ProvinceVo> getProvByCity(@RequestParam Integer cityId) {
        Integer provinceId = cityService.getById(cityId).getProvinceId();
        Province province = provinceService.getById(provinceId);
        ProvinceVo provinceVo = new ProvinceVo();
        BeanUtils.copyProperties(province, provinceVo);
        return Rx.success(provinceVo);
    }

    @ApiOperation(value = "获取省份所属的区域")
    @GetMapping(value = "get-area-by-prov")
    public R<AreaVo> getAreaByProv(@RequestParam Integer provId) {
        Integer areaId = provinceService.getById(provId).getAreaId();
        Area area = areaService.getById(areaId);
        AreaVo areaVo = new AreaVo();
        BeanUtils.copyProperties(area, areaVo);
        return Rx.success(areaVo);
    }
}