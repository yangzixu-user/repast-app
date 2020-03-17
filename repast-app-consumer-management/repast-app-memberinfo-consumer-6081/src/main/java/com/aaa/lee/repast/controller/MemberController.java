package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.annotation.LoginLogAnnotation;
import com.aaa.lee.repast.base.BaseController;
import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.model.Member;
import com.aaa.lee.repast.service.IRepastService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MemberController
 * @Author Adam
 * @Date Create in 2020/3/10  20:34
 * @Description
 */
@RestController
@Api(value = "用户信息",tags = "用户信息接口（提供用户有关操作")
public class MemberController extends BaseController {
    @Autowired
    private IRepastService repastService;
    /**
     * @Author Adam
     * @Description
     *      执行登录操作
     * @Parme: [member]
     * @return : com.aaa.lee.repast.base.ResultData
     * @Date: 2020/3/10 20:55
     */
    @PostMapping("/doLogin")
    @ApiOperation(value = "登录",notes = "用户执行登录操作（接收微信端传递数据）")
    @LoginLogAnnotation(operationType = "登录操作",operationName = "普通用户登录")
    public ResultData doLogin(Member member){
            //需要调用api工程（feign）
        Boolean ifSuccess = repastService.doLogin(member);
        if (ifSuccess) {
            return super.LoginSuccess();
        }
        return super.LoginFailed();
    }
}
