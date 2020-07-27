package com.iotat.weather;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iotat.weather.mapper.LocationMapper;
import com.iotat.weather.mapper.WeatherMapper;
import com.iotat.weather.service.LocationService;
import com.iotat.weather.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;

@SpringBootTest
class WeatherApplicationTests {

    @Autowired
    WeatherMapper weatherMapper;

    @Autowired
    WeatherService ws;

    @Autowired
    LocationMapper lm;

    @Autowired
    LocationService ls;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    void contextLoads() {
        String json = ls.getLocation("阳");
        System.out.println(json);
    }

    @Test
    void testGetJson() {
        String url = "https://wis.qq.com/weather/common?" +
                "source=xw&weather_type=" +
                "forecast_1h|forecast_24h|limit|observe&" +
                "province=四川&city=成都&county=都江堰";
        StringBuilder json = new StringBuilder();
        try {
            URL u = new URL(url);
            URLConnection yc = u.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),
                    StandardCharsets.UTF_8));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject a = new JSONObject(JSON.parseObject(json.toString()));
        // System.out.println(a.get("data"));
        JSONObject b = a.getJSONObject("data");
        JSONObject c = b.getJSONObject("forecast_24h");
        System.out.println(c.get("1"));

    }

    @Test
    void getCityTest(){
        String city = ls.getLocation("朝阳");
        JSONObject json = JSON.parseObject(city);
        System.out.println(json.get("data"));
    }

}
