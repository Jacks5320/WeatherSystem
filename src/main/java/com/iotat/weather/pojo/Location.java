package com.iotat.weather.pojo;

import io.swagger.annotations.Api;

/**
 * @description: 地区信息
 * @author: Jacks丶
 * @date: 2020年7月20日23:11:29
 * @version: 1.0
 */
public class Location {
    private Integer id;
    private String name;
    private int grade;//级别：省、城市、区县
    private Integer parentId;//上一级，如：成都市的上一级是四川省

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                ", parentId=" + parentId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
