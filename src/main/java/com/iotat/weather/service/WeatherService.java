package com.iotat.weather.service;

public interface WeatherService {

    //缓存或更新实时气象
    String updateNowWeather(int id,
                            String province,
                            String city,
                            String county);

    //获取实时气象
    String getNowWeather(int id,
                         String province,
                         String city,
                         String county);

    //缓存或更新24小时预报
    String updateHourWeather(int id,
                             String province,
                             String city,
                             String county);

    //获取未来24小时预报
    String getHourWeather(int id,
                          String province,
                          String city,
                          String county);

    //缓存或更新7天数据
    String updateDayWeather(int locationId,
                            String province,
                            String city,
                            String county);

    //获取7天数据
    String getDayWeather(int id,
                         String province,
                         String city,
                         String county);
}
