package com.aaa.lee.repast.status;

/**
 * @ClassName StatusCode
 * @Author Adam
 * @Date Create in 2020/3/17  17:31
 * @Description
 *  在jvm编译java文件的时候--->class--->Jvm会给所有的类以及通过这个类生成的对象都默认添加上一个final关键字
 */
public final class StatusCode {
    public static final String ENCODING = "UTF-8";
    public static final String PROVINCE = "province";
    public static final String COUNTRY = "country";
    public static final String CITY = "city";
    public static final String FORMAT_DATE = "yyyy-MM-dd hh:mm:ss";
    //TODO 这里的Ip是测试数据，上线时注意替换
    public static final String TEST_IP = "222.137.210.37";
}
