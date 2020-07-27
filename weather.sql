-- 创建数据库
CREATE DATABASE IF NOT EXISTS iotat_weather CHARACTER SET utf8 ;

USE iotat_weather;

-- 地名表
CREATE TABLE tb_location(
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	name VARCHAR(10) COMMENT '地名',
	grade INT(1) COMMENT '行政区级别',
	parent_id INT COMMENT '上一级行政区'
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE tb_now_weather(
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	degree INT(2) COMMENT '气温',
	weather VARCHAR(5) COMMENT '气象描述',
	weather_code INT(2) COMMENT '气象图标',
	humidity INT(3) COMMENT '相对湿度',
	wind_direction VARCHAR(3) COMMENT '风向',
	wind_power INT(2) COMMENT '风力等级',
	update_time DATETIME COMMENT '更新时间',
	location_id INT COMMENT '地区名称',
	CONSTRAINT fk_location_wea_now FOREIGN KEY(location_id) REFERENCES tb_location(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE tb_hour_weather(
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	degree INT(2) COMMENT '气温',
	weather VARCHAR(5) COMMENT '气象描述',
	weather_code INT(2) COMMENT '气象图标',
	wind_direction VARCHAR(3) COMMENT '风向',
	wind_power INT(2) COMMENT '风力等级',
	time DATETIME COMMENT '天气实时时刻',
	time_id INT COMMENT '24个小时，第一个时刻为当前时刻',
	location_id INT COMMENT '地区名称',
	CONSTRAINT fk_location_wea_hour FOREIGN KEY(location_id) REFERENCES tb_location(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE tb_day_weather(
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	day_weather VARCHAR(5) COMMENT '白天气象描述',
	day_weather_code INT(2) COMMENT '白天气象图',
	day_wind_direction VARCHAR(3) COMMENT '白天风向',
	day_wind_power INT COMMENT '白天风力等级',
	night_weather VARCHAR(5) COMMENT '晚上气象描述',
	night_weather_code INT(2) COMMENT '晚上气象图',
	night_wind_direction VARCHAR(3) COMMENT '晚上风向',
	night_wind_power INT COMMENT '晚上风力等级',
	degree_max INT(2) COMMENT '最高气温',
	degree_min INT(2) COMMENT '最低气温',
	time DATETIME COMMENT '日期',
	date_id INT COMMENT '7天,第一天为今天',
	location_id INT COMMENT '地区名称',
	CONSTRAINT fk_location_wea_day FOREIGN KEY(location_id) REFERENCES tb_location(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;