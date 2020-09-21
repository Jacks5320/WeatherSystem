package com.iotat.weather.pojo;

public class NowWeather {
    private Integer id;
    private int degree;//温度
    private String weather;//气象
    private int weatherCode;//天气图标
    private int humidity;//湿度%
    private String windDirection;//风向
    private String windPower;//风力
    private String updateTime;//更新时间
    private int locationId;//城市

    @Override
    public String toString() {
        return "NowWeather{" +
                "id=" + id +
                ", degree=" + degree +
                ", weather='" + weather + '\'' +
                ", weatherCode=" + weatherCode +
                ", humidity=" + humidity +
                ", windDirection='" + windDirection + '\'' +
                ", windPower='" + windPower + '\'' +
                ", updateTime=" + updateTime +
                ", locationId=" + locationId +
                '}';
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

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getWindPower() {
        return windPower;
    }

    public void setWindPower(String windPower) {
        this.windPower = windPower;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
}
