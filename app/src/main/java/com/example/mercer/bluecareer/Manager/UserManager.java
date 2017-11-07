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


    //检查输入用户名的合法性
    public String localCheck(String id,String key){
        if(id.length()<6||id.length()>16){
            return "用户名长度不能小于6或者大于16";
        }
        if(!(id.charAt(0) <= 'z'&&id.charAt(0) >= 'a')&&!(id.charAt(0) <= 'Z'&&id.charAt(0) >= 'A')){
            return "用户名必须以数字开头";
        }
        if(key.length()<6||key.length()>16){
            return "密码长度不能小于6或者大于16";
        }
        return "true";
    }

    public boolean login(String id,String key){
        //alextest
        if(id.equals("GreySky0012")&&key.equals("huhaoran0012")) {
            return true;
        }

        //连接数据库查询用户名和密码是否正确
        return false;
    }

    public boolean tryRegist(String id,String email){
        return true;
    }

    public void regist(User user){
        //check username and email in database

    }

    public Bitmap getImage(String id){
        //从本地数据库中获取头像
        return null;
    }
}
