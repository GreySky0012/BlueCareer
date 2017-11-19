package com.example.mercer.bluecareer.DataStruct.Url;

import android.util.Log;

import com.example.mercer.bluecareer.Manager.SystemManager;

/**
 * Created by GreySky on 2017/11/8.
 */
public class UserUrl extends Url{

    public UserUrl(String tail) {
        super(tail);
        _url = "http://120.25.240.242:8080/BlueCareer/api/v1/user"+tail;
        SystemManager.getInstance().PrintLog("向后端请求数据, url ="+ _url);
    }
}