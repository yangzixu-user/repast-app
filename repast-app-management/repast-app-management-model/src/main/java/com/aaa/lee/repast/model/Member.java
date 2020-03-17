package com.aaa.lee.repast.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 使用jap模式做的映射
 *      @Table：映射的就是数据库中的表
 *      @Id：所标识的就是表中的主键
 *      @GeneratedValue(strategy = GenerationType.IDENTITY)：是否自增主键
 *      @Column：所标识的就是数据库表中的列名
 *              如果数据库中所涉及的字段遵循java的命名规则，则不需要
 *              如果没有遵循java的命名规则，遵循的是数据中的命名规则（使用_下划线）
 *              这个时候必须加上Column注解
 *          @Size:定义数据库中字段的最大值
 *              现在java中进行验证--->如果在项目中验证没有问题--->才会去连接数据库
 *          @NotNull：是一个非空字段
 *              也是先在java中验证
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
@Table(name = "ums_member")
public class Member implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(max = 20)
    @NotNull
    private Long id;

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "member_level_id")
    private Long memberLevelId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 帐号启用状态:0->禁用；1->启用
     */
    private Integer status;

    /**
     * 注册时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 头像
     */
    private String icon;

    /**
     * 性别：0->未知；1->男；2->女
     */
    private Integer gender;

    /**
     * 生日
     */
    private Date birthday;


    /**
     * 所做城市
     */
    private String city;

    /**
     * 职业
     */
    private String job;

    /**
     * 个性签名
     */
    @Column(name = "personalized_signature")
    private String personalizedSignature;

    /**
     * 用户来源
     */
    @Column(name = "source_type")
    private Integer sourceType;

    /**
     * 积分
     */
    private Integer integration;

    /**
     * 成长值
     */
    private Integer growth;

    /**
     * 剩余抽奖次数
     */
    @Column(name = "luckey_count")
    private Integer luckeyCount;

    /**
     * 历史积分数量
     */
    @Column(name = "history_integration")
    private Integer historyIntegration;

    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 微信返回的open_id
     */
    @Column(name = "open_id")
    @Size(max = 255)
    @NotNull
    private String openId;

    /**
     * 微信返回的session_key
     */
    @Column(name = "session_key")
    private String sessionKey;

    /**
     * 登录验证token
     */
    private String token;


}