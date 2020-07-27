package com.iotat.weather.controller;

/**
 * @description: 地理位置访问接口
 * @author: Jacks丶
 * @date: 2020年7月21日21:05:37
 * @version: 1.0
 */

import com.alibaba.fastjson.JSONObject;
import com.iotat.weather.service.LocationService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/location")
@Api(tags = "地理位置接口")
public class LocationController {

    @Autowired
    LocationService ls;

    @ApiOperation(
            value = "获取天气接口参数[省，市，县]",
            notes = "传参格式：" +
                    "<br>{" +
                    "<br>\"city\":\"可查天气的城市名，如成都\"" +
                    "<br>}")
    @PostMapping("/getCity")
    public String getCity(@RequestBody JSONObject city) {
        return ls.getLocation(city.getString("city"));
    }
}
