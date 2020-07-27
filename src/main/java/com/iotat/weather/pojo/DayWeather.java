package com.iotat.weather.pojo;

import java.util.Date;

/**
 * @description: 7天的天气预报
 * @author: Jacks丶
 * @date: 2020年7月20日23:11:55
 * @version: 1.0
 */
public class DayWeather {
    private Integer id;
    private String dayWeather;//白天气象
    private int dayWeatherCode;//白天图标
    private String dayWindDirection;//白天风向
    private String dayWindPower;//白天风力
    private String nightWeather;//夜晚气象
    private int nightWeatherCode;//夜晚气象图标
    private String nightWindDirection;//夜晚风向
    private String nightWindPower;//夜晚风力
    private int degreeMax;//最高气温
    private int degreeMin;//最低气温
    private String time;    //日期
    private int dateId;
    private int locationId;     //地区

    @Override
    public String toString() {
        return "DayWeather{" +
                "id=" + id +
                ", dayWeather='" + dayWeather + '\'' +
                ", dayWeatherCode=" + dayWeatherCode +
                ", dayWindDirection='" + dayWindDirection + '\'' +
                ", dayWindPower='" + dayWindPower + '\'' +
                ", nightWeather='" + nightWeather + '\'' +
                ", nightWeatherCode=" + nightWeatherCode +
                ", nightWindDirection='" + nightWindDirection + '\'' +
                ", nightWindPower='" + nightWindPower + '\'' +
                ", degreeMax=" + degreeMax +
                ", degreeMin=" + degreeMin +
                ", time='" + time + '\'' +
                ", dateId=" + dateId +
                ", locationId=" + locationId +
                '}';
    }

    public int getDateId() {
        return dateId;
    }

    public void setDateId(int dateId) {
        this.dateId = dateId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDayWeather() {
        return dayWeather;
    }

    public void setDayWeather(String dayWeather) {
        this.dayWeather = dayWeather;
    }

    public int getDayWeatherCode() {
        return dayWeatherCode;
    }

    public void setDayWeatherCode(int dayWeatherCode) {
        this.dayWeatherCode = dayWeatherCode;
    }

    public String getDayWindDirection() {
        return dayWindDirection;
    }

    public void setDayWindDirection(String dayWindDirection) {
        this.dayWindDirection = dayWindDirection;
    }

    public String getDayWindPower() {
        return dayWindPower;
    }

    public void setDayWindPower(String dayWindPower) {
        this.dayWindPower = dayWindPower;
    }

    public String getNightWeather() {
        return nightWeather;
    }

    public void setNightWeather(String nightWeather) {
        this.nightWeather = nightWeather;
    }

    public int getNightWeatherCode() {
        return nightWeatherCode;
    }

    public void setNightWeatherCode(int nightWeatherCode) {
        this.nightWeatherCode = nightWeatherCode;
    }

    public String getNightWindDirection() {
        return nightWindDirection;
    }

    public void setNightWindDirection(String nightWindDirection) {
        this.nightWindDirection = nightWindDirection;
    }

    public String getNightWindPower() {
        return nightWindPower;
    }

    public void setNightWindPower(String nightWindPower) {
        this.nightWindPower = nightWindPower;
    }

    public int getDegreeMax() {
        return degreeMax;
    }

    public void setDegreeMax(int degreeMax) {
        this.degreeMax = degreeMax;
    }

    public int getDegreeMin() {
        return degreeMin;
    }

    public void setDegreeMin(int degreeMin) {
        this.degreeMin = degreeMin;
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
