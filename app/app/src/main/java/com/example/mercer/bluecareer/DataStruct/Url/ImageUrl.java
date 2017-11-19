package com.example.mercer.bluecareer.DataStruct.Url;

import android.util.Log;

import com.example.mercer.bluecareer.Manager.SystemManager;

/**
 * Created by GreySky on 2017/11/19.
 */

public class ImageUrl extends Url {
    public ImageUrl(String tail) {
        super(tail);
        _url = "http://120.25.240.242:8080"+tail;
        SystemManager.getInstance().PrintLog("向后端请求数据, url ="+ _url);
    }
}
