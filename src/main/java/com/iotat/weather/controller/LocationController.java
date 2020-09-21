package com.iotat.weather.controller;

/**
 * @description: 地理位置访问接口
 * @author: Jacks丶
 * @date: 2020年7月21日21:05:37
 * @version: 1.0
 */

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

    @GetMapping("/getCity")
    public String getCity(String city) {
        return ls.getLocation(city);
    }
}
