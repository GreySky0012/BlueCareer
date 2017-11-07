package com.example.mercer.bluecareer.Manager;

import android.graphics.Bitmap;
import android.media.Image;

import com.example.mercer.bluecareer.DataStruct.User;

/**
 * Created by GreySky on 2017/10/21.
 */
public class UserManager {
    private static UserManager _instance;
    private UserManager(){}
    public static UserManager getInstance() {
        if (_instance == null){
            _instance = new UserManager();
        }
        return  _instance;
    }

    private User user;

    public boolean login(String id,String key){
        //alextest
        if(id.equals("GreySky0012")&&key.equals("huhaoran0012")) {
            return true;
        }

        //连接数据库查询用户名和密码是否正确
        return false;
    }

    public Bitmap getImage(String id){
        //从数据库中获取头像
        return null;
    }
}
