package com.iotat.weather.controller;

import com.alibaba.fastjson.JSONObject;
import com.iotat.weather.service.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
@Api(tags = "气象数据接口")
public class WeatherController {

    @Autowired
    WeatherService ws;

    @ApiOperation(
            value = "获取实时天气数据",
            notes = "传参格式：" +
                    "<br>{" +
                    "<br>\"id\":\"地区id，由地名查询函数获取\"," +
                    "<br>\"province\":\"省名\"," +
                    "<br>\"city\":\"城市名\"," +
                    "<br>\"county\":\"区县名\"" +
                    "<br>}"
    )
    @PostMapping("/now")
    public String getNow(@RequestBody JSONObject json) {
        Integer id = json.getInteger("id");
        String province = json.getString("province");
        String city = json.getString("city");
        String county = json.getString("county");

        return ws.getNowWeather(id, province, city, county);
    }

    @ApiOperation(
            value = "获取未来24小时天气数据",
            notes = "传参格式：" +
                    "<br>{" +
                    "<br>\"id\":\"地区id，由地名查询函数获取\"," +
                    "<br>\"province\":\"省名\"," +
                    "<br>\"city\":\"城市名\"," +
                    "<br>\"county\":\"区县名\"" +
                    "<br>}"
    )
    @PostMapping("/24h")
    public String getHour(@RequestBody JSONObject json) {
        Integer id = json.getInteger("id");
        String province = json.getString("province");
        String city = json.getString("city");
        String county = json.getString("county");
        return ws.getHourWeather(id, province, city, county);
    }

    @ApiOperation(
            value = "获取未来7天天气数据",
            notes = "传参格式：" +
                    "<br>{" +
                    "<br>\"id\":\"地区id，由地名查询函数获取\"," +
                    "<br>\"province\":\"省名\"," +
                    "<br>\"city\":\"城市名\"," +
                    "<br>\"county\":\"区县名\"" +
                    "<br>}")
    @PostMapping("/7d")
    public String getDay(@RequestBody JSONObject json) {
        Integer id = json.getInteger("id");
        String province = json.getString("province");
        String city = json.getString("city");
        String county = json.getString("county");
        return ws.getDayWeather(id, province, city, county);
    }
}
