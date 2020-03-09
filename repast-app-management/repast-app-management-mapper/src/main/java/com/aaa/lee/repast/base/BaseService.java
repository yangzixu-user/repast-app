package com.aaa.lee.repast.base;

import tk.mybatis.mapper.common.Mapper;

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
 *
 */
public abstract class BaseService<T> {
    public abstract Mapper<T> getMapper();
    /**
     * @Author Adam
     * @Description
     *      保存方法
     * @Parme: [t]
     * @return : java.lang.Integer
     * @Date: 2020/3/9 22:02
     */
    protected Integer save(T t) throws Exception{
        return getMapper().insert(t);
    }

    protected Integer update(T t) throws Exception{
            return 1;
    }

}
