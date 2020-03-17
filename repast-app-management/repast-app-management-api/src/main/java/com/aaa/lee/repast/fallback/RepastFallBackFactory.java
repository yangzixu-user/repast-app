package com.aaa.lee.repast.fallback;

import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.model.LoginLog;
import com.aaa.lee.repast.model.Member;
import com.aaa.lee.repast.service.IRepastService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName RepastFallBackFactory
 * @Author Adam
 * @Date Create in 2020/3/10  21:13
 * @Description
 */
@Component
public class RepastFallBackFactory implements FallbackFactory<IRepastService> {
    @Override
    public IRepastService create(Throwable throwable) {
        IRepastService repastService = new IRepastService(){
            @Override
            public Boolean doLogin(Member member) {
                System.out.println("熔断方法");
                return null;
            }

            @Override
            public ResultData saveLog(Map map) {
                System.out.println("熔断日志方法");
                return null;
            }


        };
        return repastService;
    }
}
