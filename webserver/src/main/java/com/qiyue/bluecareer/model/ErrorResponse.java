package com.qiyue.bluecareer.model;



/**
 * Created by Qiyue on 2017/10/13
 */
public class ErrorResponse extends CommonResponse{
    public ErrorResponse() {
        this.setCode(-1);
        this.setMessage("error");
    }

    public ErrorResponse(String message) {
        this.setCode(-1);
        this.setMessage(message);
    }

    public ErrorResponse(int code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }
}