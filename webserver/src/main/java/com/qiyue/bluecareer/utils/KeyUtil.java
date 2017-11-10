package com.qiyue.bluecareer.utils;

import java.util.UUID;

public class KeyUtil {

    public static String getNewKey() {
        return UUID.randomUUID().toString();
    }
}
