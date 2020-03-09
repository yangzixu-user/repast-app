package com.aaa.lee.repast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName ApplicationRun7082
 * @Author Adam
 * @Date Create in 2020/3/9  18:03
 * @Description TODO
 */
@SpringBootApplication
@EnableEurekaServer
public class ApplicationRun7082 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun7082.class,args);
    }
}
