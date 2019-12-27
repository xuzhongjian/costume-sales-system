package com.zjxu97.costume.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.zjxu97.costume.common.Constants;
import com.zjxu97.costume.common.RetFunc;
import com.zjxu97.costume.model.Area;
import com.zjxu97.costume.model.City;
import com.zjxu97.costume.model.Province;
import com.zjxu97.costume.service.area.AreaService;
import com.zjxu97.costume.service.city.CityService;
import com.zjxu97.costume.service.district.DistrictService;
import com.zjxu97.costume.service.province.ProvinceService;
import com.zjxu97.costume.vo.AreaVo;
import com.zjxu97.costume.vo.CityVo;
import com.zjxu97.costume.vo.DistrictVo;
import com.zjxu97.costume.vo.ProvinceVo;
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
    AreaService areaService;

    @Resource
    ProvinceService provinceService;

    @Resource
    CityService cityService;

    @Resource
    DistrictService districtService;

    @ApiOperation(value = "列出所有地区")
    @GetMapping(value = "list-areas")
    public R<List<AreaVo>> listArea() {
        List<Area> areas = areaService.getBaseMapper().selectList(null);
        List<AreaVo> areaVos = areas.stream().map(area -> {
            AreaVo areaVo = new AreaVo();
            BeanUtils.copyProperties(area, areaVo);
            return areaVo;
        }).collect(Collectors.toList());
        return RetFunc.success(areaVos);
    }

    @ApiOperation(value = "按照地区列出所有省份")
    @GetMapping(value = "list-provs-by-area")
    public R<List<ProvinceVo>> listProvsByArea(@RequestParam(value = "area_id") Integer areaId) {
        List<ProvinceVo> provinceVos = provinceService.listProvsByArea(areaId);
        return RetFunc.success(provinceVos);
    }

    @ApiOperation(value = "按照省份列出所有城市")
    @GetMapping(value = "list-citys-by-prov")
    public R<List<CityVo>> listCitysByProv(@RequestParam(value = "prov_id") Integer provId) {
        List<CityVo> cityVos = cityService.listCitysByProv(provId);
        return RetFunc.success(cityVos);
    }

    @ApiOperation(value = "按照城市列出所有区县")
    @GetMapping(value = "list-dists-by-city")
    public R<List<DistrictVo>> listDistsByCity(@RequestParam(value = "city_id") Integer cityId) {
        List<DistrictVo> districtVos = districtService.listDistsByCity(cityId);
        return RetFunc.success(districtVos);
    }

    @ApiOperation(value = "获取县区所属的城市")
    @GetMapping(value = "get-city-by-dist")
    public R<CityVo> getCityByDist(@RequestParam(value = "dist_id") Integer distId) {
        Integer cityId = districtService.getById(distId).getCityId();
        City city = cityService.getById(cityId);
        CityVo cityVo = new CityVo();
        BeanUtils.copyProperties(city, cityVo);
        return RetFunc.success(cityVo);
    }

    @ApiOperation(value = "获取城市所属的省份")
    @GetMapping(value = "get-prov-by-city")
    public R<ProvinceVo> getProvByCity(@RequestParam(value = "city_id") Integer cityId) {
        Integer provinceId = cityService.getById(cityId).getProvinceId();
        Province province = provinceService.getById(provinceId);
        ProvinceVo provinceVo = new ProvinceVo();
        BeanUtils.copyProperties(province, provinceVo);
        return RetFunc.success(provinceVo);
    }

    @ApiOperation(value = "获取省份所属的区域")
    @GetMapping(value = "get-area-by-prov")
    public R<AreaVo> getAreaByProv(@RequestParam(value = "prov_id") Integer provId) {
        Integer areaId = provinceService.getById(provId).getAreaId();
        Area area = areaService.getById(areaId);
        AreaVo areaVo = new AreaVo();
        BeanUtils.copyProperties(area, areaVo);
        return RetFunc.success(areaVo);
    }
}