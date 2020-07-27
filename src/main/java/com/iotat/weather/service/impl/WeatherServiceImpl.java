package com.iotat.weather.service.impl;

import com.alibaba.fastjson.JSON;
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

    @Autowired
    LocationMapper lm;

    @Autowired
    LocationService ls;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 更新缓存实时气象数据
     *
     * @param id       地名id
     * @param province 省
     * @param city     市
     * @param county   区
     * @return json字符串
     */
    @Override
    public String updateNowWeather(int id,
                                   String province,
                                   String city,
                                   String county) {

        NowWeather nowData = wm.findNow(id);

        //当前系统时间
        Date dateNow = new Date(System.currentTimeMillis());

        try {
            //未缓存或缓存时间超过30分钟
            if (nowData == null || dateNow.getTime() - sdf.parse(nowData.getUpdateTime()).getTime() > 1800000) {

                WeatherUtil w = new WeatherUtil();

                JSONObject json = w.getNow(province, city, county);

                if (json.isEmpty()) {
                    return ResultJSON.resultStatus(201, "没有当前地区气象数据");
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

                if (wm.findNow(id) == null) {
                    wm.saveNow(now);
                } else {
                    wm.updateNow(now);
                }

                return ResultJSON.resultStatus(200, "缓存或更新成功");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return ResultJSON.resultStatus(200, "访问成功,无需缓存");
    }

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
    public String getNowWeather(int id, String province, String city, String county) {

        String json = updateNowWeather(id, province, city, county);

        JSONObject data = JSON.parseObject(json);

        return ResultJSON.resultData(
                Integer.parseInt(data.get("code").toString()),
                data.get("msg").toString(),
                wm.findNow(id));
    }

    //缓存或更新24小时天气信息
    @Override
    public String updateHourWeather(int id, String province, String city, String county) {

        List<HourWeather> listHour = wm.findHour(id);

        Date dateNow = new Date(System.currentTimeMillis());

        try {

            //没缓存数据或者数据更新时间超过2小时
            if (listHour.isEmpty() || dateNow.getTime() - sdf.parse(listHour.get(0).getTime()).getTime() > 7200000) {

                WeatherUtil w = new WeatherUtil();

                JSONObject json = w.getHour(province, city, county);

                if (json.isEmpty()) {
                    return ResultJSON.resultStatus(201, "没有当前地区气象数据");
                }

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

                if (wm.findHour(id).isEmpty()) {
                    wm.saveHour(weatherList);
                } else {
                    wm.updateHour(weatherList);
                }

                return ResultJSON.resultStatus(200, "更新或缓存成功");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return ResultJSON.resultStatus(200, "访问成功，无需更新");
    }

    //获取24小时气象
    @Override
    public String getHourWeather(int id, String province, String city, String county) {

        String json = updateHourWeather(id, province, city, county);

        JSONObject data = JSON.parseObject(json);

        return ResultJSON.resultData(
                Integer.parseInt(data.get("code").toString()),
                data.get("msg").toString(),
                wm.findHour(id));
    }

    //缓存更新7天数据
    @Override
    public String updateDayWeather(int locationId, String province, String city, String county) {

        List<DayWeather> list = wm.findDay(locationId);

        Date date = new Date(System.currentTimeMillis());

        try {
            //无缓存，或时间超过2天
            if (list.isEmpty() || date.getTime() - sdf.parse(list.get(0).getTime()).getTime() > (172800000)) {
                WeatherUtil w = new WeatherUtil();
                JSONObject json = w.getDay(province, city, county);
                if (json.isEmpty()) {
                    return ResultJSON.resultStatus(201, "没有该地区的缓存信息");
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
                if (wm.findDay(locationId).isEmpty()) {
                    wm.saveDay(days);
                } else {
                    wm.updateDay(days);
                }
                return ResultJSON.resultStatus(200, "访问成功，已缓存或更新数据");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return ResultJSON.resultStatus(200, "访问成功，无需更新。");
    }

    //获取7天数据
    @Override
    public String getDayWeather(int id, String province, String city, String county) {

        String json = updateDayWeather(id, province, city, county);

        JSONObject data = JSON.parseObject(json);

        return ResultJSON.resultData(
                Integer.parseInt(data.getString("code")),
                data.getString("msg"),
                wm.findDay(id));
    }
}
