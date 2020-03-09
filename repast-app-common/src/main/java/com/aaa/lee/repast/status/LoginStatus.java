package com.aaa.lee.repast.status;

/**
 * @ClassName LoginStatus
 * @Author Adam
 * @Date Create in 2020/3/9  19:32
 * @Description
 *      规定：
 *          不要和http的状态码重复--->如果一旦重复就会覆盖http的状态码
 *
 */
public enum  LoginStatus {

    LOGIN_SUCCESS("201","登录成功"),
    LOGIN_FAILED("501","登录失败"),
    USER_EXIST("202","用户存在"),
    USER_NOT_EXIST("401","用户不存在"),
    PASSWORD_WRONG("502","密码错误"),
    LOGOUT_WRONG("503","用户突出异常");

    LoginStatus(String code ,String msg){
        this.code=code;
        this.msg=msg;
    }
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
