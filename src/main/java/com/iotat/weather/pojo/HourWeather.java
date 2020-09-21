package com.iotat.weather.pojo;

import java.util.Date;

/**
 * @description: 24小时的天气预报
 * @author: Jacks丶
 * @date: 2020年7月20日23:12:12
 * @version: 1.0
 */
public class HourWeather {

    private Integer id;
    private int degree;//气温
    private String weather;//天气
    private int weatherCode;//天气图标
    private String windDirection;//风向
    private int windPower;//风力
    private String time;//时刻
    private int timeId;//第几个时刻
    private int locationId;//城市

    @Override
    public String toString() {
        return "HourWeather{" +
                "id=" + id +
                ", degree=" + degree +
                ", weather='" + weather + '\'' +
                ", weatherCode=" + weatherCode +
                ", windDirection='" + windDirection + '\'' +
                ", windPower=" + windPower +
                ", time=" + time +
                ", timeId=" + timeId +
                ", locationId=" + locationId +
                '}';
    }

    public int getTimeId() {
        return timeId;
    }

    public void setTimeId(int timeId) {
        this.timeId = timeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public int getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(int weatherCode) {
        this.weatherCode = weatherCode;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public int getWindPower() {
        return windPower;
    }

    public void setWindPower(int windPower) {
        this.windPower = windPower;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
}
