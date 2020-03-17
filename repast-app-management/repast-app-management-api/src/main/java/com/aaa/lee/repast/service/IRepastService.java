package com.aaa.lee.repast.service;

import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.fallback.RepastFallBackFactory;
import com.aaa.lee.repast.model.LoginLog;
import com.aaa.lee.repast.model.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @ClassName IRepastService
 * @Author Adam
 * @Date Create in 2020/3/10  21:11
 * @Description
 *      springcloud2.x之后，在feign中只能出现一次
 *       !!!value的值指向的就是provider项目中的application.properties文件中所配置的spring.application.name!!!
 *       !!!api接口中的方法，一定要和provider中controller中的方法一模一样
 */
//@FeignClient(value = "memberinfo-interface",fallbackFactory = RepastFallBackFactory.class)
@FeignClient(value = "memberinfo-interface")
public interface IRepastService {
    /**
     * @Author Adam
     * @Description
     *      执行登录操作
     * @Parme:
     * @return : java.lang.Boolean
     * @Date: 2020/3/10 21:50
     */

    @PostMapping("/doLogin")
    Boolean doLogin(@RequestBody Member member);
    /**
     * @Author Adam
     * @Description
     *      登录日志保存
     * @Parme: [loginLog]
     * @return : java.lang.Boolean
     * @Date: 2020/3/12 0:37
     */
    @PostMapping("/add")
    ResultData saveLog(@RequestBody Map map);

}
