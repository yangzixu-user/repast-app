package com.aaa.lee.repast.base;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

/**
 * @ClassName CommonController
 * @Author Adam
 * @Date Create in 2020/3/17  17:55
 * @Description
 */
public abstract class CommonController<T> extends BaseController {
    /** 
     * @Author Adam
     * @Description
     *      定义钩子函数：
     *          在新增方法之前先执行的内容
     * @Parme: [map]
     * @return : void
     * @Date: 2020/3/17 17:57
     */
    protected void beforeAdd(Map map){
        //TODO AddMethod Before to do
    }
    
    /** 
     * @Author Adam
     * @Description
     *      定义钩子函数
     *          在新增方法之后执行的内容
     * @Parme: [map]
     * @return : void
     * @Date: 2020/3/17 18:02
     */
    
    protected void afterAdd(Map map){
        //TODO AddMethod After to do
    }

    public abstract BaseService<T> getBaseService();
    /**
     * @Author Adam
     * @Description
     *      新增操作
     * @Parme: [map]
     * @return : com.aaa.lee.repast.base.ResultData
     * @Date: 2020/3/17 20:20
     */
    public ResultData add(@RequestBody Map map){
        beforeAdd(map);
        //insert into user(id,username...) values(?,?,?...)
        //如果正好从前端传递过来的json格式--->Map的key与你数据库中表的字段一模一样，
        //那么你可以直接使用Map进行存储（<select id parameterType="map" resultType="xxx")
        //如果对应不上，需要转换为实体类
        //1.通过Map转换成实体类型
        T instance = getBaseService().newInstance(map);
        Integer insertResult = getBaseService().add(instance);
        if (insertResult>0){
            afterAdd(map);
            return super.operationSuccess();
        }
        return super.operationFailed();
    }
}
