package com.qiyue.bluecareer.utils;

import java.util.UUID;

public class KeyUtil {

    private KeyUtil(){
        /**/
    }

    public static String getNewKey() {
        return UUID.randomUUID().toString();
    }
}
