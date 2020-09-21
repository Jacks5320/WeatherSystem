package com.iotat.weather.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截过滤 天气获取 相关参数
 */
@Component
public class WeatherControllerInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String id = request.getParameter("id");
        if (province==null|| city==null || id == null){
            System.out.println("异常参数");
            //  重定向
            response.sendRedirect("400.html");
            return false;
        }

        //if (ids.length != 1){
        //    System.out.println("参数多余，判断了");
        //    return false;
        //}
        return id.matches("\\d+");

        //if (id.matches("\\d{1,}")){
        //    System.out.println(id);
        //    System.out.println("可以转换");
        //    return true;
        //}else {
        //    System.out.println(id);
        //    System.out.println("不能转换");
        //    return false;
        //}
    }
}
