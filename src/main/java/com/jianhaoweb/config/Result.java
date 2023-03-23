package com.jianhaoweb.config;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Data;

import java.lang.reflect.Type;


/**
 * @Auther:剑豪
 * @Date:2023/3/14
 * @VERSON:1.8
 */
@Data
public class Result {

    private Integer code; // 数字代码 如: 成功 200;
    private String msg; // "错误描述"
    private Object data; // 数据 {}

    public Result() {
        this.code = 200;
        this.msg = "success";
    }

    public static Result error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static Result error(String msg) {
        return error(500, msg);
    }

    public static Result error(int code, String msg) {
        Result r = new Result();
        r.code = code;
        r.msg = msg;
        return r;
    }

    public static Result ok(String msg) {
        Result r = new Result();
        r.msg = msg;
        return r;
    }


    public static Result ok() {
        return new Result();
    }




    public Result data(Object data) {
        Result r = new Result();
        r.data = data;
        return r;
    }

    /**
     * 把map里key为data的数据转换成某个类型的数据
     * @param typeReference
     * @param <T>
     * @return
     */
    public <T> T getData(TypeReference<T> typeReference) {
        Object obj = this.getData();

        String json = JSON.toJSONString(obj);

        T t = JSON.parseObject(json, (Type) typeReference);

        return t;
    }

    public <T> T getData(String key, TypeReference<T> typeReference) {
        Object obj = this.getData();

        String json = JSON.toJSONString(obj);

        T t = JSON.parseObject(json, (Type) typeReference);

        return t;
    }


}
