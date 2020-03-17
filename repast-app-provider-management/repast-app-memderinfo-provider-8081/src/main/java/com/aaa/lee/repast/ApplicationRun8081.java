package com.aaa.lee.repast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * @ClassName ApplicationRun8081
 * @Author Adam
 * @Date Create in 2020/3/10  22:08
 * @Description
 *      @EnableDiscoveryClient：能让eureka和其他注册中心发现该服务
 *      @EnableEurekaClient:只能让Eureka服务注册中心发现该服务
 *      @MapperScan:扫描mapper
 *
 */
@SpringBootApplication
@MapperScan("com.aaa.lee.repast.mapper")
@EnableDiscoveryClient
@EnableCircuitBreaker
public class ApplicationRun8081 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun8081.class,args);
    }
}
