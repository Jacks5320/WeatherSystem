package com.iotat.weather;

import com.iotat.weather.mapper.LocationMapper;
import com.iotat.weather.pojo.Location;
import com.iotat.weather.service.LocationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 测试 Mapper
 */
@SpringBootTest
public class LocationTest {

    @Autowired
    LocationMapper lm;

    @Autowired
    LocationService ls;

    @Test
    void testMapper(){// TODO 测试 LocationMapper 的 SQL 注入问题
        /*
            测试 SQL 注入，sql 注入的原理是恶意拼接 sql 语句
                这种不是 字符串类型的参数不存在sql注入。
         */
        //Location l1 = lm.findById(3);
        //System.out.println(l1);
        // findByName
        /*
            测试 SQL 注入，测试结果
                MyBatis 使用 ${} 取值时，存在 sql 注入，因为 ${} 的原理是字符串拼接。
                MyBatis 使用 #{} 取值时，不存在 sql 注入，因为 #{} 的原理是作为一个占位符，但又不能直接在 SQL 语句中拼接 % 号。
            解决方案：
                使用 #{} 配合 <bind> 标签使用模糊查询，能有效防止 sql 注入
         */
        List<Location> l2 = lm.findByName("成都 or 1=1");
        List<Location> l3 = lm.findByName("都");
        System.out.println(l2);
        System.out.println(l3);
    }

    @Test
    void testService(){// TODO 地区获取功能测试
        String location = ls.getLocation("成");
        System.out.println(location);
    }
}
