package com.aaa.lee.repast.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
@Table(name = "ums_member_login_log")
public class LoginLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(max = 20)
    @NotNull
    private Long id;

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "create_time")
    private String createTime;

    private String ip;

    private String city;

    /**
     * 登录类型：0->PC；1->android;2->ios;3->小程序
     */
    @Column(name = "login_type")
    private Integer loginType;

    private String province;

    @Column(name = "open_id")
    @Size(max = 20)
    @NotNull
    private String openId;
    /**
     * 操作类型（eg:删除操作，登录操作...）
     */
    @Column(name = "operation_type")
    private String operationType;

    /**
     * 具体操作事项（eg:普通用户登录，管理严登录，删除图书，删除用户）
     */
    @Column(name = "operation_name")
    private String operationName;


}