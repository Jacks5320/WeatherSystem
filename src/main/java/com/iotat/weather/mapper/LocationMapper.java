package com.iotat.weather.mapper;

import com.iotat.weather.pojo.Location;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LocationMapper {

    //根据名字查
    List<Location> findByName(String name);

    //根据id查
    Location findById(Integer id);
}
