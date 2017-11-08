package com.qiyue.bluecareer.model.enums;

import com.qiyue.bluecareer.model.ErrorResponse;

public enum ErrorEnum {
    ALREADY_HAVE_USER(500, "Already have user. "),
    REQUEST_PARAMETER_ERROR(501, "Request parameter error. ");

    private final int code;
    private final String message;
    private ErrorEnum(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public ErrorResponse getResponse(String msg){
        switch(code){
            case 500: return new ErrorResponse(400,ALREADY_HAVE_USER.getMessage()  + msg);
            case 501: return new ErrorResponse(501,REQUEST_PARAMETER_ERROR.getMessage() + msg);
        }
        return null;
    }
}
