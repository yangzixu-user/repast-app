package com.aaa.lee.repast.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ResultData
 * @Author Adam
 * @Date Create in 2020/3/9  20:49
 * @Description
 */
@Data
public class ResultData<T> implements Serializable {
    private String code;
    private String msg;
    private String detail;
    private T data;
}
