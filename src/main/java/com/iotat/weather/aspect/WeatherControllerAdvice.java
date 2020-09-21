package com.iotat.weather.aspect;

import com.iotat.weather.mapper.LocationMapper;
import com.iotat.weather.pojo.Location;
import com.iotat.weather.utils.ResultJSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * 控制天气预报查询参数
 */
@Component
@Aspect
public class WeatherControllerAdvice {

    @Autowired
    LocationMapper lm;

    @Pointcut("execution(* *..WeatherController.getWeather(..))")
    private void pointcut() {
    }

    @Around("pointcut()")
    public Object aroundAdvice(ProceedingJoinPoint jp) {
        try {
            Object[] args = jp.getArgs();       //拦截到方法的参数
            String province = (String) args[0];
            String city = (String) args[1];
            String county = (String) args[2];
            Integer id = (Integer) args[3];
            //
            //System.out.println(province);
            //System.out.println(city);
            //System.out.println(county);
            //System.out.println(idString);

            //int id = 0;
            //
            //// ID 不能转换为 Integer 类型
            //if (idString.matches("\\d+")) {
            //    id = Integer.parseInt(idString);
            //} else {
            //    return ResultJSON.resultData(201, "请输入正确参数", null);
            //}

            ////  参数异常
            //if (province == null || city == null) {
            //    return ResultJSON.resultData(201, "请输入正确参数", null);
            //}

            Location location = lm.findById(id);

            //  数据库中没有查询地区
            if (location == null) {
                return ResultJSON.resultData(201, "未找到相关地区的天气预报信息", null);
            }

            // 防止通过URL链接手动输入参数造成数据库数据异常
            if (county == null) {
                args[2] = "";
                if (!location.getName().equals(city)) {
                    return ResultJSON.resultData(201, "参数信息与数据库不匹配", null);
                }
            } else {
                if (!location.getName().equals(county)) {
                    return ResultJSON.resultData(201, "参数信息与数据库不匹配", null);
                }
            }
            return jp.proceed(args);
        } catch (Throwable e) {
            System.out.println(e);
            return ResultJSON.resultData(404, "未找到", null);
        }
    }
}
