package com.iotat.weather.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * @description: 从接口中提取JSON数据
 * @author: Jacks丶
 * @date: 2020年7月20日22:08:47
 * @version: 1.0
 */
public class WeatherUtil {

    //3个API可以卸载一起，用竖线隔开，缓存时间段有差异，所以分成3个接口
    private final String nowApi = "https://wis.qq.com/weather/common?source=xw&weather_type=observe";
    private final String hourApi = "https://wis.qq.com/weather/common?source=xw&weather_type=forecast_1h";
    private final String dayApi = "https://wis.qq.com/weather/common?source=xw&weather_type=forecast_24h";

    /**
     * 拼接接口连接
     *
     * @param api      调用的接口
     * @param province 传入接口的数据：省份
     * @param city     传入接口的数据：城市
     * @param county   传入接口的数据：区县
     * @return 拼接好的接口字符串
     */
    private static String getApiUrl(String api, String province, String city, String county) {
        return api +
                "&province=" +
                province +
                "&city=" +
                city +
                "&county=" +
                county;
    }

    /**
     * 拼接Date类型的字符串202007221755=》2020-07-22 17：55
     *
     * @param s 字符串 202007221755
     * @return date字符串：2020-07-22 17:55
     */
    public String getSdfDate(String s) {
        return s.substring(0, 4) +//2020
                "-" +
                s.substring(4, 6) +//07
                "-" +
                s.substring(6, 8) +//21
                " " +
                s.substring(8, 10) +//22
                ":" +
                s.substring(10, 12);//55
    }

    /**
     * 将接口返回的JSON格式的字符串转化为JSON对象
     *
     * @return json对象
     */
    private JSONObject getJsonData(String url) {
        StringBuilder jsonString = new StringBuilder();//接收JSON数据
        try {
            //打开api接口
            URLConnection uc = new URL(url).openConnection();
            //读入api数据
            BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream(),
                    StandardCharsets.UTF_8));//以字节流的形式读入字符
            //读取JSON格式的字符串
            String inputLine;
            //循环读入数据，组成json字符串
            while ((inputLine = br.readLine()) != null) {
                jsonString.append(inputLine);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // return new JSONObject(JSON.parseObject(jsonString.toString()));
        return JSON.parseObject(jsonString.toString());
    }

    /**
     * 获取指定位置当前的气象
     *
     * @param province 省分
     * @param city     城市
     * @param county   区县
     * @return json对象
     */
    public JSONObject getNow(String province, String city, String county) {
        String url = getApiUrl(nowApi, province, city, county);
        return getJsonData(url).getJSONObject("data").getJSONObject("observe");
    }

    /**
     * 获取指定位置未来24的气象预报
     *
     * @param province 省分
     * @param city     城市
     * @param county   区县
     * @return json对象
     */
    public JSONObject getHour(String province, String city, String county) {
        String url = getApiUrl(hourApi, province, city, county);
        return getJsonData(url).getJSONObject("data").getJSONObject("forecast_1h");
    }

    /**
     * 获取指定位置未来7天的气象预报
     *
     * @param province 省分
     * @param city     城市
     * @param county   区县
     * @return json对象
     */
    public JSONObject getDay(String province, String city, String county) {
        String url = getApiUrl(dayApi, province, city, county);
        return getJsonData(url).getJSONObject("data").getJSONObject("forecast_24h");
    }

}
