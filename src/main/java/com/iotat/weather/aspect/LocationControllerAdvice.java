package com.iotat.weather.aspect;

import com.iotat.weather.mapper.LocationMapper;
import com.iotat.weather.utils.ResultJSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 控制地区查询参数
 */
@Component
@Aspect
public class LocationControllerAdvice {

    @Autowired
    LocationMapper lm;

    @Pointcut("execution(* *..LocationController.*(..))")
    private void pointcut() {
    }

    @Around("pointcut()")
    public Object aroundAdvice(ProceedingJoinPoint jp) {
        try {
            Object[] args = jp.getArgs();
            String city = (String) args[0];

            if (city == null){
                return ResultJSON.resultData(200, "请输入城市信息", "请输入城市信息");
            }

            if (city.contains(" ")) {
                city = city.replace(" ", "");
            }

            if ("".equals(city)) {
                return ResultJSON.resultData(200, "请输入城市信息", "请输入城市信息");
            }

            args[0] = city;
            return jp.proceed(args);
        } catch (Throwable t) {
            return ResultJSON.resultData(500, "服务器出错", null);
        }
    }
}
