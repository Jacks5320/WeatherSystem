package com.iotat.weather.service;

import com.iotat.weather.pojo.DayWeather;
import com.iotat.weather.pojo.HourWeather;
import com.iotat.weather.pojo.NowWeather;

import java.util.List;

public interface WeatherService {

    //缓存或更新实时气象
    NowWeather updateNowWeather(int id, String province, String city, String county, boolean flag);

    //获取实时气象
    NowWeather getNowWeather(int id, String province, String city, String county);

    //缓存或更新24小时预报
    List<HourWeather> updateHourWeather(int id, String province, String city, String county, boolean flag);

    //获取未来24小时预报
    List<HourWeather> getHourWeather(int id, String province, String city, String county);

    //缓存或更新7天数据
    List<DayWeather> updateDayWeather(int locationId, String province, String city, String county, boolean flag);

    //获取7天数据
    List<DayWeather> getDayWeather(int id, String province, String city, String county);
}
