package com.zjxu97.costume.controllers;

import com.baomidou.mybatisplus.extension.api.R;
import com.zjxu97.costume.commons.CostumeConstants;
import com.zjxu97.costume.commons.Ans;
import com.zjxu97.costume.commons.LocationClassConstants;
import com.zjxu97.costume.model.entity.location.Area;
import com.zjxu97.costume.model.entity.location.City;
import com.zjxu97.costume.model.entity.location.District;
import com.zjxu97.costume.model.entity.location.Province;
import com.zjxu97.costume.model.param.LocationParam;
import com.zjxu97.costume.model.vo.*;
import com.zjxu97.costume.service.location.AreaService;
import com.zjxu97.costume.service.location.CityService;
import com.zjxu97.costume.service.location.DistrictService;
import com.zjxu97.costume.service.location.ProvinceService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 无需分页
 *
 * @author zjxu97
 * @date 2019/12/28 01:37
 */
@RestController
@Api(tags = "位置相关")
@RequestMapping(CostumeConstants.API_PREFIX + "/location")
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

    @ApiOperation(value = "列出地区")
    @GetMapping(value = "list-location")
    public R<List<LocationVo>> listLocation(LocationParam locationParam) {
        Byte locationClass = locationParam.getLocationClass();
        Integer locationId = locationParam.getLocationId();
        List<LocationVo> locationVoList = null;
        switch (locationClass) {
            case LocationClassConstants.AREA:
                List<Area> areas = areaService.list(null);
                locationVoList = areas.stream().map(area -> {
                    LocationVo locationVo = new LocationVo();
                    locationVo.setLocationId(area.getId());
                    locationVo.setLocationName(area.getAreaName());
                    locationVo.setParentId(0);
                    return locationVo;
                }).collect(Collectors.toList());
                break;
            case LocationClassConstants.PROVINCE:
                List<Province> provinceList = provinceService.listProvsByArea(locationId);
                locationVoList = provinceList.stream().map(province -> {
                    LocationVo locationVo = new LocationVo();
                    locationVo.setLocationId(province.getId());
                    locationVo.setLocationName(province.getProvinceName());
                    locationVo.setParentId(locationId);
                    return locationVo;
                }).collect(Collectors.toList());
                break;
            case LocationClassConstants.CITY:
                List<City> cityList = cityService.listCityByProv(locationId);
                locationVoList = cityList.stream().map(province -> {
                    LocationVo locationVo = new LocationVo();
                    locationVo.setLocationId(province.getId());
                    locationVo.setLocationName(province.getCityName());
                    locationVo.setParentId(locationId);
                    return locationVo;
                }).collect(Collectors.toList());
                break;
            case LocationClassConstants.DISTRICT:
                List<District> districtList = districtService.listDistsByCity(locationId);
                locationVoList = districtList.stream().map(province -> {
                    LocationVo locationVo = new LocationVo();
                    locationVo.setLocationId(province.getId());
                    locationVo.setLocationName(province.getDistrictName());
                    locationVo.setParentId(locationId);
                    return locationVo;
                }).collect(Collectors.toList());
                break;
            default:
                log.error("参数输入错误{}", locationParam);
                break;

        }
        return Ans.success(locationVoList);
    }


    @ApiOperation(value = "获取上级")
    @GetMapping(value = "list-parent")
    public R<List<LocationVo>> listParent(LocationParam locationParam) {
        Byte locationClass = locationParam.getLocationClass();
        List<LocationVo> locationVoList = new ArrayList<>();
        switch (locationClass) {
            case LocationClassConstants.AREA:
                locationVoList = areaService.listParent(locationParam);
                log.error("本级别没有上级{}", locationParam);
                break;
            case LocationClassConstants.PROVINCE:
                locationVoList = provinceService.listParent(locationParam);
                break;
            case LocationClassConstants.CITY:
                locationVoList = cityService.listParent(locationParam);
                break;
            case LocationClassConstants.DISTRICT:
                locationVoList = districtService.listParent(locationParam);
                break;
            default:
                log.error("参数输入错误{}", locationParam);
                break;
        }
        return Ans.success(locationVoList);
    }
}