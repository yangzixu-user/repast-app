package com.aaa.lee.repast.base;


import com.aaa.lee.repast.utils.Map2BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import java.lang.reflect.ParameterizedType;
import java.util.Map;

/**
 * @ClassName BaseService
 * @Author Adam
 * @Date Create in 2020/3/9  21:40
 * @Description
 *      所有service的父类
 *      既然说一个业务逻辑层必须要穿mapper
 *      UserService extends BaseService
 *      OrderService extends BaseService
 *      ...
 *      传mapper的时候说传UserMapper还是OrderMapper
 *      tk.mapper--->就是传通用mapper
 *      这个通用mapper写的再厉害也不可能覆盖所有的方法 (多表查询的时候(order,admin,permission))
 *      所一让然需要自定义sql语句--->单纯的传递一个通用mapper就失去意义了
 *
 *      --->最好的结果就是如果有自定义的就用自定义，如果不需要自定义的则直接就使用通用mapper
 *
 *      T：传递实体类
 *      --->User--->Mapper<User>--->UserMapper
 *
 *       protected
 *          同一包下
 *          子类
 *          当前类
 *
 */
/*
 * 一个service解决了单表业务逻辑（不在需要通过一个实体类对应一个service了--->所有的实体类对应一个service）
 */
public abstract class BaseService<T> {

    private Class<T> cache = null; //定义一个缓存--->来存储泛型，

    @Autowired
    private Mapper<T> mapper;

    protected Mapper getMapper(){
        return mapper;
    }
    /**
     * @Author Adam
     * @Description
     *      新增操作
     * @Parme: [t]
     * @return : java.lang.Integer
     * @Date: 2020/3/17 20:01
     */
    public Integer add(T t){
        return mapper.insertSelective(t);
    }
    /**
     * @Author Adam
     * @Description
     *      通过Map类型转换成Java实体类
     * @Parme: [map]
     * @return : T
     * @Date: 2020/3/17 18:19
     */
    public T newInstance(Map map){
        //定义高性能反射工具
        //自定义高性能反射工具类（有些是有泛型的（List，Map，Set，BaseService）
      return  (T) Map2BeanUtil.map2Bean(map,getTypeArgument());

    }
    /**
     * @Author Adam
     * @Description
     *      更新操作
     * @Parme: [t]
     * @return : java.lang.Integer
     * @Date: 2020/3/18 0:04
     */

    public Integer update(T t){
        return getMapper().updateByPrimaryKeySelective(t);
    }
    /**
     * @Author Adam
     * @Description
     *      获取泛型
     * @Parme: []
     * @return : java.lang.Class<T>
     * @Date: 2020/3/18 0:02
     */

    public Class<T> getTypeArgument(){
        if (null == cache){
            cache = (Class<T>) (((ParameterizedType)this.getClass().getGenericSuperclass())
                    .getActualTypeArguments()[0]);

        }
        return cache;
    }



}
