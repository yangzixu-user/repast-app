package com.aaa.lee.repast.base;

import com.aaa.lee.repast.page.PageInfos;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

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
    /**
     * @Author Adam
     * @Description
     *      根据
     * @Parme: [t]
     * @return : java.lang.Integer
     * @Date: 2020/3/9 23:24
     */
    protected Integer update(T t) throws Exception{
        return   getMapper().updateByPrimaryKey(t);
    }
    /**
     * @Author Adam
     * @Description
     *      根据主键进行删除
     * @Parme: [key]
     * @return : java.lang.Integer
     * @Date: 2020/3/10 0:01
     */
    protected Integer deleteByPrimaryKey(Object key) throws Exception{
        return getMapper().deleteByPrimaryKey(key);
    }
    /**
     * @Author Adam
     * @Description
     *      根据实体类属性来删除（一般情况下是通过唯一键进行删除）
     * @Parme: [t]
     * @return : java.lang.Integer
     * @Date: 2020/3/10 0:03
     */
    protected Integer delete(T t) throws Exception {
        return getMapper().delete(t);
    }
    /**
     * @Author Adam
     * @Description
     *      根据主键进行查询
     * @Parme: [key]
     * @return : T
     * @Date: 2020/3/10 0:06
     */
    protected T get(Object key) throws Exception{
        return getMapper().selectByPrimaryKey(key);
    }
    /** 
     * @Author Adam
     * @Description
     *      查询所有信息
     * @Parme: []
     * @return : java.util.List<T>
     * @Date: 2020/3/10 0:07
     */
    protected List<T> get() throws Exception{
        return getMapper().selectAll();
    }
    /**
     * @Author Adam
     * @Description
     *      通过实体类属性进行查询（select xxx form user where username =？）--->只能查询出来一个
     *      where 后面的字段一定是唯一键
     * @Parme: [t]
     * @return : T
     * @Date: 2020/3/10 0:09
     */
    protected T getOne(T t) throws Exception{
        return getMapper().selectOne(t);
    }
    /** 
     * @Author Adam
     * @Description
     *      根据实体类属性进行查询--->返回集合列表
     * @Parme: [t]
     * @return : java.util.List<T>
     * @Date: 2020/3/10 0:13
     */
    protected List<T> getModel(T t) throws Exception{
        return getMapper().select(t);
    }
    /**
     * @Author Adam
     * @Description
     *      代分页的查询条件
     *      offset；偏移量（当前页码）
     *      limit：查询条件
     *      select xxx from user limit（1，5）;
     *
     * @Parme: [t, pageInfos]
     * @return : java.util.List<T>
     * @Date: 2020/3/10 0:25
     */
    protected List<T> getPage(T t, PageInfos<T> pageInfos) throws Exception{
        return getMapper().selectByRowBounds(t,new RowBounds(pageInfos.getPageNum(),pageInfos.getPageSize()));

    }
    /** 
     * @Author Adam
     * @Description
     *      查询数据带条件
     *      select count(1) from user where username like 'zhangshan';
     *      如果不许要条件直接传null
     * @Parme: [t]
     * @return : java.lang.Integer
     * @Date: 2020/3/10 0:29
     */
    
    protected Integer getCount(T t) throws Exception{
        return getMapper().selectCount(t);
    }

    protected PageInfo<T> getPageInfo(PageInfos<T> pageInfos) throws Exception{
        /**
         * 第一次点击菜单进入右侧页面
         * 说明是没有页码数的--->这就可能报错空指针--->所以需要赋值一个初始化的页码数
         */
        if (pageInfos.getPageNum()==null){
            pageInfos.setPageNum(0);
        }
        // select * from user limit 0, 3
        PageHelper.startPage(pageInfos.getPageNum(),pageInfos.getPageSize());
        List<T> list = this.getModel(pageInfos.getT());

        PageInfo<T> pageInfo = new PageInfo<T>();
        /**
         * 为了计算总共的页数，所以需要传递一个总数量
         */
        pageInfo.setTotal(this.getCount(pageInfos.getT()));
        return pageInfo;
    }

}
