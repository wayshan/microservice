package com.common.result;

import lombok.Data;

import java.util.HashMap;
import java.util.function.Consumer;

/**
 * 统一接口响应类
 * <p>所有接口统一返回此类型，保证响应格式一致。</p>
 */
@Data
public class ResponseResult {

    /** 是否成功 */
    private boolean status;

    /** 提示信息 */
    private String msg;

    /** 状态码 */
    private String code;

    /** 业务数据 */
    private HashMap<String, Object> data;

    /**
     * 成功（无数据）
     */
    public static ResponseResult ok() {
        ResponseResult result = new ResponseResult();
        result.status = true;
        result.code = "200";
        result.msg = "success";
        result.data = new HashMap<>();
        return result;
    }

    /**
     * 成功（单个数据）
     */
    public static ResponseResult ok(String key, Object value) {
        ResponseResult result = ok();
        result.data.put(key, value);
        return result;
    }

    /**
     * 成功（多个数据，通过 Consumer 填充）
     */
    public static ResponseResult ok(Consumer<HashMap<String, Object>> dataConsumer) {
        ResponseResult result = ok();
        dataConsumer.accept(result.data);
        return result;
    }

    /**
     * 失败（默认提示）
     */
    public static ResponseResult error() {
        ResponseResult result = new ResponseResult();
        result.status = false;
        result.code = "500";
        result.msg = "操作失败";
        return result;
    }

    /**
     * 失败（自定义提示）
     */
    public static ResponseResult error(String msg) {
        ResponseResult result = error();
        result.msg = msg;
        return result;
    }

    /**
     * 失败（自定义状态码和提示）
     */
    public static ResponseResult error(String code, String msg) {
        ResponseResult result = error();
        result.code = code;
        result.msg = msg;
        return result;
    }
}
