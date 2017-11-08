package com.qiyue.bluecareer.exception;

public class BlueCareerException extends Exception{
    public BlueCareerException(String message) {
        super(message);
    }

    public BlueCareerException(Throwable e) {
        super(e);
    }
}
