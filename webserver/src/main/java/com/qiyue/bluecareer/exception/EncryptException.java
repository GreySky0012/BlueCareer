package com.qiyue.bluecareer.exception;

/**
 * Created by Qiyue on 2017/11/7
 */
public class EncryptException extends Exception{

    public EncryptException(String message) {
        super(message);
    }

    public EncryptException(Throwable e) {
        super(e);
    }
}
