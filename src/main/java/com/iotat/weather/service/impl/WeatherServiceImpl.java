package com.iotat.weather.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.iotat.weather.mapper.LocationMapper;
import com.iotat.weather.mapper.WeatherMapper;
import com.iotat.weather.pojo.DayWeather;
import com.iotat.weather.pojo.HourWeather;
import com.iotat.weather.pojo.NowWeather;
import com.iotat.weather.service.LocationService;
import com.iotat.weather.service.WeatherService;
import com.iotat.weather.utils.ResultJSON;
import com.iotat.weather.utils.WeatherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    WeatherMapper wm;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取当前气象信息
     *
     * @param id       地区id
     * @param province 省
     * @param city     市
     * @param county   区县
     * @return json字符串，含当前气象数据
     */
    @Override
    public NowWeather getNowWeather(int id, String province, String city, String county) {

        NowWeather now = wm.findNow(id);

        if (now == null) {
            return updateNowWeather(id, province, city, county, true);
        }
        // 当前时间
        Date date = new Date(System.currentTimeMillis());

        try {
            if (date.getTime() - sdf.parse(now.getUpdateTime()).getTime() > 1800000) {
                return updateNowWeather(id, province, city, county, false);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return wm.findNow(id);
    }

    /**
     * 更新缓存实时气象数据
     *
     * @param id       地名id
     * @param province 省
     * @param city     市
     * @param county   区
     * @param flag     确定是保存操作还是更新操作
     * @return 更新的对象
     */
    @Override
    public NowWeather updateNowWeather(int id, String province, String city, String county, boolean flag) {

        WeatherUtil w = new WeatherUtil();
        JSONObject json = w.getNow(province, city, county);

        if (json == null){
            throw new NullPointerException();
        }

        NowWeather now = new NowWeather();

        now.setDegree(Integer.parseInt(json.getString("degree")));
        now.setWeather(json.getString("weather"));
        now.setWeatherCode(Integer.parseInt(json.getString("weather_code")));
        now.setHumidity(Integer.parseInt(json.getString("humidity")));
        now.setWindDirection(json.getString("wind_direction"));
        now.setWindPower(json.getString("wind_power"));
        now.setUpdateTime(w.getSdfDate(json.getString("update_time")));
        now.setLocationId(id);

        if (flag) {
            wm.saveNow(now);
        } else {
            wm.updateNow(now);
        }
        return now;
    }

    //获取24小时气象
    @Override
    public List<HourWeather> getHourWeather(int id, String province, String city, String county) {

        List<HourWeather> lists = wm.findHour(id);

        if (lists.isEmpty()) {
            return updateHourWeather(id, province, city, county, true);
        }

        String updateTime = lists.get(0).getTime();

        Date now = new Date(System.currentTimeMillis());

        try {
            if (now.getTime() - sdf.parse(updateTime).getTime() > 3600000) {
                return updateHourWeather(id, province, city, county, false);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lists;
    }

    //缓存或更新24小时天气信息
    @Override
    public List<HourWeather> updateHourWeather(int id, String province, String city, String county, boolean flag) {

        WeatherUtil w = new WeatherUtil();

        JSONObject json = w.getHour(province, city, county);

        if (json == null) throw new RuntimeException("未找到");

        List<HourWeather> weatherList = new ArrayList<>();

        for (int i = 0; i < 24; i++) {

            JSONObject data = json.getJSONObject(String.valueOf(i));//获取每一条数据

            HourWeather h = new HourWeather();

            h.setDegree(Integer.parseInt(data.getString("degree")));
            h.setWeather(data.getString("weather"));
            h.setWeatherCode(Integer.parseInt(data.getString("weather_code")));
            h.setWindDirection(data.getString("wind_direction"));
            h.setWindPower(Integer.parseInt(data.getString("wind_power")));
            h.setTime(w.getSdfDate(data.getString("update_time")));
            h.setTimeId(i);
            h.setLocationId(id);

            weatherList.add(h);//添加到列表，方便批量存储
        }

        if (flag) {
            wm.saveHour(weatherList);
        } else {
            wm.updateHour(weatherList);
        }
        return weatherList;
    }

    //获取7天数据
    @Override
    public List<DayWeather> getDayWeather(int id, String province, String city, String county) {

        List<DayWeather> lists = wm.findDay(id);

        if (lists.isEmpty()) {
            return updateDayWeather(id, province, city, county, true);
        }

        String updateTime = lists.get(1).getTime();

        Date now = new Date(System.currentTimeMillis());

        try {
            if (now.getTime() - sdf.parse(updateTime).getTime() > 86400000) {
                return updateDayWeather(id, province, city, county, false);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lists;
    }

    //缓存更新7天数据
    @Override
    public List<DayWeather> updateDayWeather(int locationId, String province, String city, String county, boolean flag) {

        WeatherUtil w = new WeatherUtil();
        JSONObject json = w.getDay(province, city, county);
        if (json.isEmpty()) {
            throw new RuntimeException("未查询到该地区天气情况");
        }
        List<DayWeather> days = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            JSONObject data = json.getJSONObject(String.valueOf(i));
            DayWeather day = new DayWeather();
            day.setDayWeather(data.getString("day_weather"));
            day.setDayWeatherCode(Integer.parseInt(data.getString("day_weather_code")));
            day.setDayWindDirection(data.getString("day_wind_direction"));
            day.setDayWindPower(data.getString("day_wind_power"));

            day.setNightWeather(data.getString("night_weather"));
            day.setNightWeatherCode(Integer.parseInt(data.getString("night_weather_code")));
            day.setNightWindDirection(data.getString("night_wind_direction"));
            day.setNightWindPower(data.getString("night_wind_power"));

            day.setDegreeMax(Integer.parseInt(data.getString("max_degree")));
            day.setDegreeMin(Integer.parseInt(data.getString("min_degree")));

            day.setTime(data.getString("time"));
            day.setDateId(i);
            day.setLocationId(locationId);

            days.add(day);
        }
        if (flag) {
            wm.saveDay(days);
        } else {
            wm.updateDay(days);
        }
        return days;
    }
}
