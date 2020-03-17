package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.base.BaseService;
import com.aaa.lee.repast.base.CommonController;
import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.model.LoginLog;
import com.aaa.lee.repast.service.LoginLogService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName LoginLogController
 * @Author Adam
 * @Date Create in 2020/3/17  23:36
 * @Description
 */
@RestController
public class LoginLogController extends CommonController<LoginLog> {
    @Autowired
    private LoginLogService loginLogService;
    @Override
    public BaseService<LoginLog> getBaseService() {
        return loginLogService;
    }
    /**
     * @Author Adam
     * @Description
     *      新增操作
     * @Parme: [loginLog]
     * @return : java.lang.Boolean
     * @Date: 2020/3/12 0:37
     */
    @PostMapping("/add")
    ResultData saveLog(@RequestBody Map map){
        ResultData resultData = super.add(map);
        return resultData;
    }

}
