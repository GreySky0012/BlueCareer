package com.example.mercer.bluecareer;

import android.app.Application;
import android.content.Context;

/**
 * author: Husen
 * date: 2017.11.16
 * description：继承Application，获取全局上下文
 */

public class MyApplication extends Application {
    private static Context _instance;

    @Override
    public void onCreate(){
        super.onCreate();
        _instance = getApplicationContext();
    }

    public static Context getContext() {
        return _instance;
    }
}