package com.qiyue.bluecareer.model.enums;

import com.qiyue.bluecareer.model.ErrorResponse;

public enum ErrorEnum {
    SERVER_ERROR(499, "Server error. "),
    REQUEST_PARAMETER_ERROR(501, "Request parameter error. "),
    HIBERNATE_ERROR(502, "SQL Error. ");

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
            case 499: return new ErrorResponse(499, SERVER_ERROR.getMessage() + msg);
            case 501: return new ErrorResponse(501,REQUEST_PARAMETER_ERROR.getMessage() + msg);
            case 502: return new ErrorResponse(502, HIBERNATE_ERROR.getMessage() + msg);
        }
        return null;
    }
}
