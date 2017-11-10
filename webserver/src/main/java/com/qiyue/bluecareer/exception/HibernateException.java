package com.qiyue.bluecareer.exception;

/**
 * Created by Qiyue on 2017/11/7
 */
public class HibernateException extends Exception{
    public HibernateException(String message) {
        super(message);
    }

    public HibernateException(Throwable e) {
        super(e);
    }
}
