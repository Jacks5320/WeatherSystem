package com.iotat.weather;

import com.iotat.weather.mapper.WeatherMapper;
import com.iotat.weather.pojo.NowWeather;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 测试 天气获取功能
 */
@SpringBootTest
public class WeatherTest {

    @Autowired
    WeatherMapper weatherMapper;

    @Test
    void testNow() {// TODO 测试实时天气
        NowWeather now = weatherMapper.findNow(2);
        System.out.println(now);
    }
}
