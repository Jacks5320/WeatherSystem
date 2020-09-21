package com.iotat.weather;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WeatherApplicationTests {

    @Test
    void contextLoads() {
        try {
            //正则表达式
            //String s = "123s";
            //String s = null;
            String s = "6#";
            String regex = "\\d{1,}";
            boolean matches = s.matches(regex);
            System.out.println(matches);
        } catch (Throwable e) {
            System.out.println(e);
        }
    }

}
