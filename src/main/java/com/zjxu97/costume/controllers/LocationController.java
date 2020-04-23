package com.zjxu97.costume.controllers;

import com.baomidou.mybatisplus.extension.api.R;
import com.zjxu97.costume.commons.CostumeConstants;
import com.zjxu97.costume.commons.LocationClassConstants;
import com.zjxu97.costume.model.vo.LocationVo;
import com.zjxu97.costume.service.AreaService;
import com.zjxu97.costume.service.CityService;
import com.zjxu97.costume.service.DistrictService;
import com.zjxu97.costume.service.ProvinceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@Api(tags = "位置相关")
@RequestMapping(CostumeConstants.API_PREFIX + "/location")
public class LocationController {

    @Resource
    private AreaService areaService;

    @Resource
    private ProvinceService provinceService;

    @Resource
    private CityService cityService;

    @Resource
    private DistrictService districtService;

    @ApiOperation(value = "列出下辖地区")
    @GetMapping(value = "locations")
    public R<List<LocationVo>> listLocation(@ApiParam(value = "locationId") @RequestParam(value = "locationId") int locationId) {
        byte locationClass = LocationClassConstants.getLocationClass(locationId);
        List<LocationVo> locationVoList = null;
        switch (locationClass) {
            case LocationClassConstants.ROOT:
                locationVoList = areaService.list(null).stream().map(
                        area -> new LocationVo(0, area.getAreaName(), area.getId())
                ).collect(Collectors.toList());
                break;
            case LocationClassConstants.AREA:
                locationVoList = provinceService.listProvsByArea(locationId).stream().map(
                        province -> new LocationVo(locationId, province.getProvinceName(), province.getId())
                ).collect(Collectors.toList());
                break;
            case LocationClassConstants.PROVINCE:
                locationVoList = cityService.listCityByProv(locationId).stream().map(
                        city -> new LocationVo(locationId, city.getCityName(), city.getId())
                ).collect(Collectors.toList());
                break;
            case LocationClassConstants.CITY:
                locationVoList = districtService.listDistsByCity(locationId).stream().map(
                        district -> new LocationVo(locationId, district.getDistrictName(), district.getId())
                ).collect(Collectors.toList());
                break;
            default:
                log.error("参数输入错误, locationId:{}, locationClass:{}", locationId, locationClass);
                break;
        }
        return R.ok(locationVoList);
    }

    @ApiOperation(value = "获取上级地区")
    @GetMapping(value = "parent")
    public R<List<LocationVo>> listParent(@ApiParam(value = "locationId") @RequestParam(value = "locationId") int locationId) {
        byte locationClass = LocationClassConstants.getLocationClass(locationId);
        List<LocationVo> locationVoList = new ArrayList<>();
        switch (locationClass) {
            case LocationClassConstants.AREA:
                locationVoList = areaService.listParent(locationId);
                break;
            case LocationClassConstants.PROVINCE:
                locationVoList = provinceService.listParent(locationId);
                break;
            case LocationClassConstants.CITY:
                locationVoList = cityService.listParent(locationId);
                break;
            case LocationClassConstants.DISTRICT:
                locationVoList = districtService.listParent(locationId);
                break;
            default:
                log.error("参数输入错误, locationId:{}, locationClass:{}", locationId, locationClass);
                break;
        }
        return R.ok(locationVoList);
    }
}