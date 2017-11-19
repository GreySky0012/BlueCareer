package com.example.mercer.bluecareer.DataStruct.Url;

/**
 * Created by GreySky on 2017/11/8.
 */
public class Url {

    protected String _url;

    public Url(String tail){
        _url = "http://120.25.240.242:8080/BlueCareer/api/v1"+tail;
    }

    public String Get(){
        return _url;
    }
}
