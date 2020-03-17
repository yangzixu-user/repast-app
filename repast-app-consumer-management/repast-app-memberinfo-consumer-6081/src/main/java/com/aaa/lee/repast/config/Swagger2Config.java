package com.aaa.lee.repast.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName Swagger
 * @Author Adam
 * @Date Create in 2020/3/11  00:36
 * @Description
 *      配置swagger的配置类 并没有实际意义
 *      配置配要加 configuration 注解
 *          1.@Configuration注解
 *          2.@Bean
 *          3.返回值
 *
 *
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket crateRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())//描述该网站的信息
                .select()//查看对外提供接口的都是什么（就是consumer中的controller）
                .apis(RequestHandlerSelectors.basePackage("com.aaa.lee.repast.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("订单项目，服务接口")
                .description("提供了项目中所有接口的信息")
                .contact(new Contact("yang","http://yang.com","yang@Gmail.com"))
                .version("1.0 beta")
                .build();
    }

}
