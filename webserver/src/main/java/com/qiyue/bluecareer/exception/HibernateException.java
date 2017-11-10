package com.qiyue.bluecareer.exception;

public class HibernateException extends Exception{
    public HibernateException(String message) {
        super(message);
    }

    public HibernateException(Throwable e) {
        super(e);
    }
}
