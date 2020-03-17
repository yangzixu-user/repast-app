package com.aaa.lee.repast.utils;

import com.esotericsoftware.reflectasm.MethodAccess;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName Map2BeanUtil
 * @Author Adam
 * @Date Create in 2020/3/17  18:21
 * @Description
 *      java高性能反射工具类
 * log4j
 */
public class Map2BeanUtil {
    //定义ava高性能反射工具类 useCache:true 使用缓存
    private final static Objenesis OBJENESIS = new ObjenesisStd(true);

    private final static StringBuilder STRING_BUILDER = new StringBuilder();

    //因为Map转换为Java Bean --->需要把Map的数据转换成字节--->再把这些字节重组为一个JavaBean
    //这个转换过程必须要求性能（需要上缓存）//定义缓存池
    private final static ConcurrentHashMap<Class, MethodAccess> CONCURRENT_HASH_MAP =
            new ConcurrentHashMap<Class, MethodAccess>(16);
    /**
     * @Author Adam
     * @Description
     *      Map转换Java Bean
     * @Parme: [map, clazz]
     * @return : T
     * @Date: 2020/3/17 18:54
     */
    public static <T> T map2Bean(Map<String,Object> map,Class<T> clazz){
        //每一个实体对象都会有一个Class对象
        // T instance = clazz.newInstance();通过Java自带的--->有可能是一个null对象（空指针异常）
        //为了提高性能就不在使用java自带的反射了，使用jar形式性能会更高，而且防止空指针
        T instance = OBJENESIS.newInstance(clazz);
        //2.从缓存池中取方法，Map.put("name","zhansan")--->每一次put name 之后，要判断是否存在
        MethodAccess methodAccess = CONCURRENT_HASH_MAP.get(clazz);
        if (null == methodAccess){
            methodAccess = MethodAccess.get(clazz);
            CONCURRENT_HASH_MAP.putIfAbsent(clazz,methodAccess);
        }
        //3.遍历转换
        for (Map.Entry<String,Object> entry : map.entrySet()){
            //需要获取到Map中所有的方法--->再把这些方法转换成JavaBean（先得获取到这些方法的方法名）
            /**
             *  Java Bean(User.java);
             *      private String name;
             *      private String password;
             *      private String age;
             *
             *      public String getName(){
             *
             *      }
             *      Public void setName(String name){
             *
             *      }
             */
            //获取方法名
            String setMethodName = getSetMethodName(entry.getKey());
            int index = methodAccess.getIndex(setMethodName, entry.getValue().getClass());
            //通过反射来获取对象（属性，set方法有了，set方法中的内容也有了）
            methodAccess.invoke(instance,index,entry.getValue());
        }
        return instance;

    }
    /**
     * @Author Adam
     * @Description
     *      通过字段获取set方法
     * @Parme: [filedName]
     * @return : java.lang.String
     * @Date: 2020/3/17 19:31
     */
    private static String getSetMethodName(String filedName){
        STRING_BUILDER.setLength(0);
        //name ---> setName
      return   STRING_BUILDER.append("set").append(firstToUpperCase(filedName)).toString();
    }
    /**
     * @Author Adam
     * @Description
     *      定义首字母大写的转换方法
     * @Parme: [str]
     * @return : java.lang.String
     * @Date: 2020/3/17 19:27
     */
    private static String firstToUpperCase(String str){
        //setName(); --->set N要大写（并不确定在Map中N是否大写--->所以统一转换）
      return   str.substring(0,1).toUpperCase()+str.substring(1,str.length());

    }
}
