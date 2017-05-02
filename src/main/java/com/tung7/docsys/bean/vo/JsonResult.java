package com.tung7.docsys.bean.vo;

import java.io.Serializable;

/**
 * JSON API统一返回格式
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/3.
 * @update
 */

public class JsonResult<T> implements Serializable {

    /**
     * 数据
     */
    private T data;
    /**
     * 信息
     */
    private String message = "";
    /**
     * 是否成功
     */
    private boolean success;

    public Object getData() {
        return data;
    }

    public JsonResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public JsonResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public JsonResult<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public JsonResult() {
        super();
    }

    public JsonResult(T data, String message, boolean success) {
        this.data = data;
        this.message = message;
        this.success = success;
    }

    public JsonResult(T data, String message) {
        this.data = data;
        this.message = message;
        this.success = true;
    }

    public JsonResult(T data) {
        this.data = data;
        this.success = true;
    }

    public JsonResult<T> setSuccessAndMsg(boolean success, String message) {
        this.success = success;
        this.message = message;
        return this;
    }
}