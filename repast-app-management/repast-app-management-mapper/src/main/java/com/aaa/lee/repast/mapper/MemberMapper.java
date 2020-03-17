package com.aaa.lee.repast.mapper;

import com.aaa.lee.repast.model.Member;
import tk.mybatis.mapper.common.Mapper;


/**
 * @ClassName MemberMapper
 * @Author Adam
 * @Date Create in 2020/3/10  18:20
 * @Description
 */
public interface MemberMapper extends Mapper<Member> {
    //通过opedId查询member（会员）
    Member selectMemberByOpenId(String opedId);

}
