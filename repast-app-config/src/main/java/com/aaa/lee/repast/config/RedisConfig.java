package com.aaa.lee.repast.config;

import com.aaa.lee.repast.properties.RedisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName RedisConfig
 * @Author Adam
 * @Date Create in 2020/3/9  19:14
 * @Description TODO
 */
@Configuration
public class RedisConfig {

   @Resource
    private RedisProperties redisProperties;
    @Bean
   public JedisCluster getJedisCluster(){
       String nodes = redisProperties.getNodes();
        String[] nodesArray = nodes.split(",");
        Set<HostAndPort> hostAndPortSet = new HashSet<HostAndPort>();
        for (String hostPort: nodesArray) {
            String[] ipAndPort = hostPort.split(":");
            HostAndPort hostAndPort = new HostAndPort(ipAndPort[0],Integer.parseInt(ipAndPort[1]));
            hostAndPortSet.add(hostAndPort);
        }
        JedisCluster jedisCluster = new JedisCluster(hostAndPortSet,redisProperties.getCommandTimeout(),redisProperties.getMaxAttempts());
        return jedisCluster;
    }
}
