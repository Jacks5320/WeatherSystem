package com.iotat.weather.controller;

import com.alibaba.fastjson.JSONObject;
import com.iotat.weather.pojo.DayWeather;
import com.iotat.weather.pojo.HourWeather;
import com.iotat.weather.pojo.NowWeather;
import com.iotat.weather.service.WeatherService;
import com.iotat.weather.utils.ResultJSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "气象数据接口")
public class WeatherController {

    @Autowired
    WeatherService ws;

    @GetMapping("/weather")
    public String getWeather(String province, String city, String county, Integer id) {
        JSONObject result = new JSONObject();
        NowWeather now = ws.getNowWeather(id, province, city, county);
        List<HourWeather> hours = ws.getHourWeather(id, province, city, county);
        List<DayWeather> days = ws.getDayWeather(id, province, city, county);
        result.put("now", now);
        result.put("hour", hours);
        result.put("day", days);
        return ResultJSON.resultData(200, "查询成功", result);
    }
}
