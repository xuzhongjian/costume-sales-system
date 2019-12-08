package com.zjxu97.costume.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.zjxu97.costume.common.RetFunc;
import com.zjxu97.costume.service.city.CityService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.zjxu97.costume.common.Constants;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "课程相关")
@RequestMapping(Constants.API_PREFIX + "/costume-test")
public class HelloController {
    private final static Logger log = LoggerFactory.getLogger(HelloController.class);


    @Resource
    CityService cityService;

    @ApiOperation(value = "测试用")
    @GetMapping(value = "city")
    public R<Integer> listCourse() {
        Integer integer = cityService.getBaseMapper().selectCount(null);
        log.info("" + integer);
        return RetFunc.success(integer);
    }


}
