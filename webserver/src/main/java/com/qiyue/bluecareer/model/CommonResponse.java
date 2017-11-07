package com.qiyue.bluecareer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by qiyue on 2017/8/21
 */
public class CommonResponse<T> {

    @JsonProperty("code")
    private int code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private T data;

    public CommonResponse(){
        this.code = 0;
        this.message = "OK";
        this.data = null;
    }

    public CommonResponse(T data){
        this.code = 0;
        this.message = "OK";
        this.data = data;
    }

    /**
     * set code
     * @param code
     * @return
     */
    public CommonResponse<T> code(int code) {
        this.code = code;
        return this;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    /**
     * set message
     * @param message
     * @return
     */
    public CommonResponse<T> message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    /**
     * set data
     * @param data
     * @return
     */
    public CommonResponse<T> data(T data) {
        this.data = data;
        return this;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
