package com.jianhaoweb.config;

import com.google.common.base.Predicates;
import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther:剑豪
 * @Date:2023/3/14
 * @VERSON:1.8
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {
    @Value("${swagger.enable}")
    private boolean swaggerEnable;
    //是否允许显示swagger。此值可在application.yml中设定。
    //作为开关，可在生产环境和开发环境打开或关闭，简便易行。

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerEnable)
                .apiInfo(apiInfo())
                .select()
//        指定扫描包
                .apis(RequestHandlerSelectors.basePackage("com.jianhaoweb.controller"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                //只显示api路径下的页面
//                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("客户记录API文档")
                .description("客户记录API文档")
                .version("1.0.0")
                .build();
    }

    /**
     * 增加如下配置可解决Spring Boot  与Swagger 3.0.0 不兼容问题
     **/


}
