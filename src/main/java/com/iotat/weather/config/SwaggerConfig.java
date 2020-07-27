package com.iotat.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2//开启swagger
public class SwaggerConfig {

    //配置swagger的Docket的bean实例
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.iotat.weather.controller"))//配置扫描接口的方式
                .paths(PathSelectors.any())
                .build();
    }

    //配置swagger信息apiInfo
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("天气预报接口")
                .description("基于腾讯天气网接口的天气预报系统")
                .version("1.0.0")
                .build();
    }
}
