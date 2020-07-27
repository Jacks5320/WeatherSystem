package com.iotat.weather.mapper;

import com.iotat.weather.pojo.DayWeather;
import com.iotat.weather.pojo.HourWeather;
import com.iotat.weather.pojo.NowWeather;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WeatherMapper {
    /*
    实时天气
     */
    void saveNow(NowWeather nowWeather);

    NowWeather findNow(int locationId);

    void updateNow(NowWeather nowWeather);

    /*
    24小时天气
     */
    void saveHour(List<HourWeather> hourWeathers);

    void updateHour(List<HourWeather> hourWeathers);

    List<HourWeather> findHour(int locationId);

    /*
    7天天气
     */
    void saveDay(List<DayWeather> dayWeather);

    void updateDay(List<DayWeather> dayWeathers);

    List<DayWeather> findDay(int locationId);

}
