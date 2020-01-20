package com.zjxu97.costume.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.zjxu97.costume.commons.Constants;
import com.zjxu97.costume.commons.Rx;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author zjxu97
 * @date 2020/1/19 21:37
 */
@RestController
@Api(tags = "数据相关")
@RequestMapping(Constants.API_PREFIX + "/data")
public class DataController {

    @ApiOperation(value = "测试")
    @GetMapping(value = "test")
    public R<String> out() {
        return Rx.success("hello world");
    }

}
