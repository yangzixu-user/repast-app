package com.aaa.lee.repast.service;

import com.aaa.lee.repast.base.BaseService;

import com.aaa.lee.repast.mapper.MemberMapper;
import com.aaa.lee.repast.model.Member;
import com.aaa.lee.repast.utils.IDUtil;
import com.aaa.lee.repast.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;

/**
 * @ClassName MemberService
 * @Author Adam
 * @Date Create in 2020/3/10  22:52
 * @Description
 */
@Service
public class MemberService extends BaseService<Member> {
   @Autowired
    private MemberMapper memberMapper;
    @Override
    public Mapper<Member> getMapper() {
        return memberMapper;
    }
    /**
     * @Author Adam
     * @Description
     *      执行登录操作
     *      虽然目前让然使用的是微信小程序作为前端，登录必须要接收的是微信账号
     *      有两种可能：
     *          1.第一次进入到微信小程序中（这个用户根本没有登录过，第一次登录）
     *                  执行的是 insert 用户信息和token值
     *          2.用户之前已经登录过了（就根本不用再做insert操作）
     *                  执行的update token （再次登录要给用户生成一个新的token，防止用户被拦截，保证了用户的安全性）
     *              无状态：
     *              所有的登录不再进行存入session、存入cookie中（一旦存入就相当于记录了登录状态）
     *              所谓的无状态就是不再去记录登录状态，
     *              因为微服务项目--->session跨域（非常不好解决）--->所有的操作都使用无状态
     *              问题：
     *                  如何确保其它项目也都知道该用户已经处于登录状态
     *                  当用户每一次登录的时候都去给用户分配一个新的token(为了保证用户安全);
     *                  然后无论用户执行什么操作都要携带这个token值（也就是说controller中除了登录方法外，
     *                  其他的所有方法都必须要接收一个token参数（可以使用切面完成））
     *                 用户在每一次操作的时候都要去检测这个token---> 也就是说有了这个token就说明这个用户已经处于登录状态
     *                 没有这个token，用户没有登录，无法访问！！（而且这个token是由服务器内部生成的，并不是由微信提供的）
     *
     * @Parme: [member]
     * @return : java.lang.Boolean
     * @Date: 2020/3/10 22:55
     */

    public Boolean doLogin(Member member){

        //1.判断member是否为空，判断openId是否为空，判断openId是否为空字符串
        if (null != member && null != member.getOpenId() && StringUtil.isNotEmpty(member.getOpenId())){
            //能进id说明是从微信跳转的请求
            //2.需要再次确认是否是新用户（通过openId进行查询--->看看是否有这个用户）
            try {
                /**
                 *  这个member是由微信端传过来的
                 *     nickname:zhangsan
                 *     region:郑州（第区）
                 *     openId:微信的唯一标识符
                 *      ....
                 *      select xxx from member where nickname = 'zhangsan' and region = '郑州‘....;
                 *
                 *      假如用户改微信名了
                 *          nickname:lisi
                 *      select xxx from member where nickname = 'lisi' and region = '郑州‘....;
                 */
                Member mb = memberMapper.selectMemberByOpenId(member.getOpenId()); //这种情况只能根据唯一的键openId进行查询（有两个东西是不变的1.openId，微信名）
                 String token = IDUtil.getUUID() + member.getOpenId();
                //3.判断mb是否为null
                if (null != mb){
                    //说明不是用户新用户，之前一定登录过
                    //需要修改token即可
                    mb.setToken(token); //这个token一定是一个新的token
                    Integer resultUpdate = super.update(mb);
                    if ( resultUpdate > 0){
                        //修改成功
                        return true;
                    }
                }else{
                    //说明是新用户，需要执行insert操作
                    Integer saveResult = super.add(member);
                    //4.说明保存成功
                    if (saveResult > 0){
                        //保存成功
                        return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;


    }
}
