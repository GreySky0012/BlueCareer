package com.qiyue.bluecareer.utils;

import java.util.UUID;

/**
 * Created by Qiyue on 2017/11/7
 */
public class KeyUtil {

    private KeyUtil(){
        /**/
    }

    public static String getNewKey() {
        return UUID.randomUUID().toString();
    }
}
