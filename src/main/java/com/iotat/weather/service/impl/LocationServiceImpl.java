package com.iotat.weather.service.impl;

import com.iotat.weather.mapper.LocationMapper;
import com.iotat.weather.pojo.Location;
import com.iotat.weather.service.LocationService;
import com.iotat.weather.utils.ResultJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationMapper lm;

    @Override
    public String getLocation(String name) {

        // name = name.trim();//只能去掉两边的多余的空格
        // 去掉所有空格
        if (name.contains(" ")){
            name = name.replace(" ","");
        }

        if (name.equals("")) {
            return ResultJSON.resultStatus(201, "搜索市、区、县等");
        }

        List<Location> list = lm.findByName(name);//根据模糊查询，查出所有的城市列表

        if (list.isEmpty()) {
            return ResultJSON.resultStatus(201, "抱歉，未找到相关地区");
        }

        List<Map<String, String>> cities = new ArrayList<>();//返还给前端的data数据

        for (Location l : list) {
            cities.add(getApiParam(l));//遍历出模糊查询出的所有地区
        }

        return ResultJSON.resultData(200, "查询成功", cities);
    }

    /**
     * 拼接地区参数
     *
     * @param l 地区对象
     * @return map集合：["city":"四川,成都,","id","2"]
     */
    private Map<String, String> getApiParam(Location l) {

        Map<String, String> city = new HashMap<>();

        List<String> list = new ArrayList<>();//接收一个地区的完整参数，如[四川，成都,]，用list方便倒序

        city.put("id", l.getId().toString());

        while (l.getId().intValue() != l.getParentId().intValue()) {//递归
            list.add(l.getName());
            l = lm.findById(l.getParentId());
        }

        list.add(l.getName());

        Collections.reverse(list);//倒序，将[成都，四川] => [四川，成都]

        if (list.size() == 2) {
            list.add("");
        }

        city.put("province",list.get(0));
        city.put("city", list.get(1));
        city.put("county",list.get(2));

        return city;
    }
}
