package com.aaa.lee.repast.controller;


import com.aaa.lee.repast.base.BaseService;
import com.aaa.lee.repast.base.CommonController;
import com.aaa.lee.repast.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.aaa.lee.repast.service.MemberService;


/**
 * @ClassName MemberController
 * @Author Adam
 * @Date Create in 2020/3/10  22:50
 * @Description
 */
@RestController
public class MemberController extends CommonController<Member> {
    @Autowired
    private MemberService memberService;
    @Override
    public BaseService<Member> getBaseService() {
        return memberService;
    }
    /**
     * @Author Adam
     * @Description
     *      执行登录操作
     * @Parme: [member]
     * @return : java.lang.Boolean
     * @Date: 2020/3/10 22:51
     */
    @PostMapping("/doLogin")
    public Boolean doLogin(@RequestBody Member member){
        return memberService.doLogin(member);
    }


}
