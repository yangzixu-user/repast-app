package com.aaa.lee.repast.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @ClassName RedisProperties
 * @Author Adam
 * @Date Create in 2020/3/9  18:54
 * @Description
 *      @Component：就相当于把这个类作为一个springboot的组件
 *      可以使用自动注入(@Autowired)
 *      之前使用的配置文件是放在springboot的默认文件中--->当项目启动的时候就会去直接加载
 *      但是现在把自定义的属性参数放在自定义的properties文件中，项目启动的时候并不会去自动加载
 *      需要程序员手动的加载到项目中：PropertySource()
 *
 *      在配置文件中使用这种方式进行编写max-attempts不是为了好看，就是为了加载到java文件中使用驼峰的形式名
 *      其实RedisProperties.java所对应的就是redis.properties配置文件
 *      就是通过最后的一段名称来给属性赋值
 *      也就是说properties中最后一段名字必须要和java文件中属性的名字一模一样，否者映射不到，无法进行赋值
 *      properties中前面所定义的必须保持一致
 *
 *
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redis")
@PropertySource("classpath:properties/redis.properties")
public class RedisProperties {
    private String nodes;
    private Integer maxAttempts;
    private Integer commandTimeout;
}
