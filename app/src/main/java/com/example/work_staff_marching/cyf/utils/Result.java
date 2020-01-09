package com.example.work_staff_marching.cyf.utils;

/**
 * data 存放 数据库查询出来的数据
 *
 * @param <T>
 */

public class Result<T> {

    private String code;

    private String message;

    private T data;


    public String getCode() {

        return code;

    }


    public void setCode(String code) {

        this.code = code;

    }


    public String getMessage() {

        return message;

    }


    public void setMessage(String message) {

        this.message = message;

    }


    public T getData() {

        return data;

    }


    public void setData(T data) {

        this.data = data;

    }


}
