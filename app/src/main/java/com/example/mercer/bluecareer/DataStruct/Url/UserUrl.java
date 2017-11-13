package com.example.mercer.bluecareer.DataStruct.Url;

import android.util.Log;

/**
 * Created by GreySky on 2017/11/8.
 */
public class UserUrl extends Url{

    public UserUrl(String tail) {
        super(tail);
        _url = "http://120.25.240.242:8080/BlueCareer/api/v1/user/"+tail;
        Log.i("向后端请求数据, url", _url);
    }
}