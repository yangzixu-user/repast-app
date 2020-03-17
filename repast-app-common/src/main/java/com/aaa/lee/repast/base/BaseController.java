package com.aaa.lee.repast.base;

import org.springframework.stereotype.Controller;
import static com.aaa.lee.repast.status.LoginStatus.*;
import static com.aaa.lee.repast.status.StatusEnums.*;

/**
 * @ClassName BaseContorller
 * @Author Adam
 * @Date Create in 2020/3/9  20:52
 * @Description
 *      所有父类的controller的父类--->里面定义的就是controller所需要返回的内容
 *      统一返回值
 *      使用父类中的方法进行返回
 *      UserController extents BaseController{
 *          public XXXXX selectAllUser(){
 *              return super.success();
 *          }
 *      }
 *
 */
@Controller
public class BaseController {
    /**
     * @Author Adam
     * @Description
     *      登录成功，使用系统消息
     * @Parme: []
     * @return : com.aaa.lee.repast.base.ResultData
     * @Date: 2020/3/9 20:59
     */
    protected ResultData LoginSuccess(){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(LOGIN_SUCCESS.getMsg());
        return resultData;
    }
    /**
     * @Author Adam
     * @Description
     *      有些情况，使用系统消息太过于简单，所需要自定义（框架是程序员使用越简单，框架就越复杂）
     *      登录成功，自定义返回消息
     * @Parme: [msg]
     * @return : com.aaa.lee.repast.base.ResultData
     * @Date: 2020/3/9 21:02
     */
    protected ResultData LoginSuccess(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(msg);
        return resultData;
    }
    /**
     * @Author Adam
     * @Description
     *      登录成功，自定义返回消息、解释说明
     * @Parme: [msg, detail]
     * @return : com.aaa.lee.repast.base.ResultData
     * @Date: 2020/3/9 21:07
     */
    protected ResultData LoginSuccess(String msg,String detail){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setDetail(detail);
        return resultData;
    }
    /**
     * @Author Adam
     * @Description
     *        登录成功，自定义返回值
     * @Parme: [data]
     * @return : com.aaa.lee.repast.base.ResultData
     * @Date: 2020/3/9 21:13
     */
    protected ResultData LoginSuccess(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(LOGIN_SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }
    /**
     * @Author Adam
     * @Description
     *      登录成功，自定义消息，自定义返回的值
     * @Parme: [msg, data]
     * @return : com.aaa.lee.repast.base.ResultData
     * @Date: 2020/3/9 21:17
     */
    protected ResultData LoginSuccess(String msg , Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }
    /**
     * @Author Adam
     * @Description
     *      登录成功，自定义解释说明，自定义返回数据
     * @Parme: [msg, detail, data]
     * @return : com.aaa.lee.repast.base.ResultData
     * @Date: 2020/3/9 21:20
     */
    protected ResultData LoginSuccess(String msg,String detail ,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setDetail(detail);
        resultData.setData(data);
        return resultData;
    }
    /**
     * @Author Adam
     * @Description
     *      登录失败，使用系统消息
     * @Parme: []
     * @return : com.aaa.lee.repast.base.ResultData
     * @Date: 2020/3/9 21:23
     */
    protected ResultData LoginFailed(){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMsg(LOGIN_FAILED.getMsg());
        return resultData;
    }
    /**
     * @Author Adam
     * @Description
     *      登录失败，使用自定义消息
     * @Parme: [msg]
     * @return : com.aaa.lee.repast.base.ResultData
     * @Date: 2020/3/9 21:25
     */
    protected ResultData LoginFailed(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMsg(msg);
        return resultData;
    }
    /**
     * @Author Adam
     * @Description
     *      登录失败，使用自定义消息，自定义解释说明
     * @Parme: [msg, detail]
     * @return : com.aaa.lee.repast.base.ResultData
     * @Date: 2020/3/9 21:27
     */
    protected ResultData LoginFailed(String msg,String detail){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMsg(msg);
        resultData.setDetail(detail);
        return resultData;
    }
    /**
     * @Author Adam
     * @Description
     *      登录失败，使用系统消息，自定义返回数据
     * @Parme: [msg, detail]
     * @return : com.aaa.lee.repast.base.ResultData
     * @Date: 2020/3/9 21:27
     */
    protected ResultData LoginFailed(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMsg(LOGIN_FAILED.getMsg());
        resultData.setData(data);
        return resultData;
    }
    /**
     * @Author Adam
     * @Description
     *      登录失败，自定义解释说明,自定义返回数据
     * @Parme: [msg, detail]
     * @return : com.aaa.lee.repast.base.ResultData
     * @Date: 2020/3/9 21:27
     */
    protected ResultData LoginFailed(String msg,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }
    /**
     * @Author Adam
     * @Description
     *      登录失败，使用自定义消息，自定义解释说明，自定义返回对象
     * @Parme: [msg, detail]
     * @return : com.aaa.lee.repast.base.ResultData
     * @Date: 2020/3/9 21:27
     */
    protected ResultData LoginFailed(String msg,String detail,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMsg(msg);
        resultData.setDetail(detail);
        resultData.setData(data);
        return resultData;
    }
    /**
     * @Author Adam
     * @Description
     *      操作成功，返回系统消息
     * @Parme: []
     * @return : com.aaa.lee.repast.base.ResultData
     * @Date: 2020/3/17 20:09
     */
    protected ResultData operationSuccess(){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(SUCCESS.getMsg());
        return resultData;
    }
    /**
     * @Author Adam
     * @Description
     *      操作成功，返回自定义消息
     * @Parme: []
     * @return : com.aaa.lee.repast.base.ResultData
     * @Date: 2020/3/17 20:09
     */
    protected ResultData operationSuccess(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(msg);
        return resultData;
    }
    /**
     * @Author Adam
     * @Description
     *      操作成功，使用系统消息，自定义返回值
     * @Parme: []
     * @return : com.aaa.lee.repast.base.ResultData
     * @Date: 2020/3/17 20:09
     */
    protected ResultData operationSuccess(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setData(data);
        return resultData;
    }
    /**
     * @Author Adam
     * @Description
     *      操作成功，使用自定义消息，自定义返回值
     * @Parme: []
     * @return : com.aaa.lee.repast.base.ResultData
     * @Date: 2020/3/17 20:09
     */
    protected ResultData operationSuccess(String msg,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }
    /**
     * @Author Adam
     * @Description
     *      操作失败，使用系统消息
     * @Parme: []
     * @return : com.aaa.lee.repast.base.ResultData
     * @Date: 2020/3/9 21:23
     */
    protected ResultData operationFailed(){
        ResultData resultData = new ResultData();
        resultData.setCode(FAILED.getCode());
        resultData.setMsg(FAILED.getMsg());
        return resultData;
    }
    /**
     * @Author Adam
     * @Description
     *      操作失败，使用自定义消息
     * @Parme: [msg]
     * @return : com.aaa.lee.repast.base.ResultData
     * @Date: 2020/3/9 21:25
     */
    protected ResultData operationFailed(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(FAILED.getCode());
        resultData.setMsg(msg);
        return resultData;
    }
    /**
     * @Author Adam
     * @Description
     *      操作失败，使用自定义消息，自定义解释说明
     * @Parme: [msg, detail]
     * @return : com.aaa.lee.repast.base.ResultData
     * @Date: 2020/3/9 21:27
     */
    protected ResultData operationFailed(String msg,String detail){
        ResultData resultData = new ResultData();
        resultData.setCode(FAILED.getCode());
        resultData.setMsg(msg);
        resultData.setDetail(detail);
        return resultData;
    }
    /**
     * @Author Adam
     * @Description
     *      操作失败，使用系统消息，自定义返回数据
     * @Parme: [msg, detail]
     * @return : com.aaa.lee.repast.base.ResultData
     * @Date: 2020/3/9 21:27
     */
    protected ResultData operationFailed(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(FAILED.getCode());
        resultData.setMsg(FAILED.getMsg());
        resultData.setData(data);
        return resultData;
    }
    /**
     * @Author Adam
     * @Description
     *      操作失败，自定义解释说明,自定义返回数据
     * @Parme: [msg, detail]
     * @return : com.aaa.lee.repast.base.ResultData
     * @Date: 2020/3/9 21:27
     */
    protected ResultData operationFailed(String msg,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(FAILED.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }
    /**
     * @Author Adam
     * @Description
     *      操作失败，使用自定义消息，自定义解释说明，自定义返回对象
     * @Parme: [msg, detail]
     * @return : com.aaa.lee.repast.base.ResultData
     * @Date: 2020/3/9 21:27
     */
    protected ResultData operationFailed(String msg,String detail,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(FAILED.getCode());
        resultData.setMsg(msg);
        resultData.setDetail(detail);
        resultData.setData(data);
        return resultData;
    }
    //TODO 查漏补缺

}
