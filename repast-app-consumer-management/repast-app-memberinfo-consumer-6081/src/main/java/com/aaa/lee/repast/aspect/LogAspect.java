package com.aaa.lee.repast.aspect;

import com.aaa.lee.repast.annotation.LoginLogAnnotation;
import com.aaa.lee.repast.model.LoginLog;
import com.aaa.lee.repast.model.Member;
import com.aaa.lee.repast.service.IRepastService;
import com.aaa.lee.repast.utils.AddressUtil;
import com.aaa.lee.repast.utils.DateUtil;
import com.aaa.lee.repast.utils.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.aaa.lee.repast.status.StatusCode.*;

/**
 * @ClassName LogAspect
 * @Author Adam
 * @Date Create in 2020/3/11  22:04
 * @Description
 *
 *      当使用切面的时候必须添加Slf4j注解
 *      @Component：把这个类标识为springboot的组件：项目启动的时候就会去加载该类
 *      @Aspect：把这个类标识为切面
 *      @Before：实现切面的五种方式  运行方式 也叫 业务方式
 *      @After：实现切面的五种方式
 */
@Slf4j
@Component
@Aspect
public class LogAspect {
    @Autowired
    private IRepastService repastService;
    /**
     * @Author Adam
     * @Description
     *      @Pointcut：就是为了定义切面，也就是说AOP在哪里生效
     *       也就是说当AOP检测到LoginLogAnnotation注解时，被该注解所标识的方法就会执行切面业务代码
     * @Parme: []
     * @return : void
     * @Date: 2020/3/11 22:32
     */
    @Pointcut("@annotation(com.aaa.lee.repast.annotation.LoginLogAnnotation)")
    public void pointcut(){
        // TODO nothing todo

    }
    /**
     * @Author Adam
     * @Description
     *      定义环形切面（具体要执行业逻辑的代码）
     *      proceedingJoinPoint：封装了目标路径（被LoginLogAnnotation注解所标识的）
     *      中的所有参数
     *      也就是说可以通过这个ProceedingJoinPoint参数获取（目标路径的方法名，方法的参数个数，
     *      方法的返回值，方法的参数值）
     * @Parme: [proceedingJoinPoint]
     * @return : java.lang.Object
     * @Date: 2020/3/11 22:39
     */

    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Exception{
            Object result = null;
            try{
                result=proceedingJoinPoint.proceed();
            }catch (Throwable t){
                t.printStackTrace();
            }
        //获取request对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        //获取用户的Ip地址(需要获取HttpServiceRequest对象）
        String ipAddr = IPUtil.getIpAddr(request);
        //如何获取MemberController中doLogin方法中Member对象
        Object[] args = proceedingJoinPoint.getArgs();
        /**
         * 第一种（这种情况只适用于方法中有一个参数，如果方法中有多个方法需要，进行判断）
         *  通过member来获取
         *  通过这种方式发现member中没有Province
         *  通过这种方式只能获取openId
         *  member是从微信端传递过来的--->城市--->爱尔兰、、、是用户自己设置的不够准确
         */
        Member member = null;
        for (Object arg : args){
           /* Member mb1 =(Member) arg.getClass().newInstance();
            System.out.println(mb1);//直接是一个空对象，通过反射创建一个null的实例对象*/
            member = (Member) arg;
        }
        //获取OperationType and OperationName 的值
        //1.获取去目标路径（只能指的是类--->MemberController的完全限定名）
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        //2.获取目标类中的所有方法，即：MemberController中的所有方法
        Class targetClass = Class.forName(className);
        //3.获取到要切的具体方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        //4.获取MemberController中所有的方法
        Method[] methods = targetClass.getMethods();
        String operationType = "";
        String operationName = "";
        for (Method method : methods){
            //5.判断方法名是否一致，则就是所需要的方法
            if (method.getName().equals(methodName)){
                //说明这个方法就是咱们所需要的方法
                //因为方法可能出现重载，一旦出现重载  方法名一样
                //需要再次判断，方法参数类型的个数是否一致
                //获取方法参数类型
                Class[] parameterTypes = method.getParameterTypes();
                //6.再次判断
                if (parameterTypes.length == args.length){
                     operationType = method.getAnnotation(LoginLogAnnotation.class).operationType();
                     operationName = method.getAnnotation(LoginLogAnnotation.class).operationName();
                    break;
                }
            }
        }
        /**
         *第二种方式：
         *  通过用户ip获取用具的地理位置
         *  会使用一个外部的API（百度）
         *  --->自己定义个工具类(向百度api发送请求，再去接收百度api响应返回的数据)
         * https://api.map.baidu.com/location/ip?ak=jZaCrCSGmhEb5srPmT6OirxGdAlS7CgL&ip=222.64.58.188&coor=bd09ll
         */
        //百度Api只能获取静态公网Ip（俗称服务器Ip）--->获取获取运营商的手机Ip
        //只能模拟ip
        Map<String, Object> addressesMap = AddressUtil.getAddresses(TEST_IP,ENCODING );
        LoginLog loginLog = new LoginLog();
            //在Member对象中
            loginLog.setProvince((String)addressesMap.get(PROVINCE));//省份信息
            loginLog.setLoginType(3);//登录类型（3：小程序）
            loginLog.setCity((String)addressesMap.get(CITY));//获取城市信息
            //使用DateUtils工具类
            String dateString = DateUtil.formatDate(new Date(), FORMAT_DATE);
            loginLog.setCreateTime(dateString);//日志创建时间
            loginLog.setIp(ipAddr);//用户登录的Ip地址
            loginLog.setOpenId(member.getOpenId());//微信传来的openId
            //在loginLog注解中
            loginLog.setOperationType(operationType);// 操作类型
            loginLog.setOperationName(operationName);//具体操作事项
        //Boolean ifSuccess = repastService.saveLog(loginLog);
        Map map = new HashMap();
        map.put("province",(String)addressesMap.get(PROVINCE));
        map.put("loginType",3);
        map.put("city",(String)addressesMap.get(CITY));
        map.put("createTime",dateString);
        map.put("ip",ipAddr);
        map.put("openId",member.getOpenId());
        map.put("operationType",operationType);
        map.put("operationName",operationName);

        /*if (ifSuccess){
            return result; //从切面重新返回到controller
        }*/
        repastService.saveLog(map);
        return result; //表示直接从代码返回，返回到controller

    }



}
