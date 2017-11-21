package com.example.mercer.bluecareer.DataStruct.Url;

import com.example.mercer.bluecareer.Manager.SystemManager;

/**
 * Created by GreySky on 2017/11/20.
 */

public class ArticleUrl extends Url {
    public ArticleUrl(String tail) {
        super(tail);
        _url = "http://120.25.240.242:8080/BlueCareer/api/v1/article"+tail;
        SystemManager.getInstance().PrintLog("向后端请求数据, url ="+ _url);
    }
}
