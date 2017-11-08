package com.example.mercer.bluecareer.Manager;

import android.graphics.Bitmap;
import android.media.Image;
import android.text.TextUtils;

import com.example.mercer.bluecareer.Activities.LoginActivity;
import com.example.mercer.bluecareer.Activities.RegistActivity;
import com.example.mercer.bluecareer.DataStruct.RetureCode;
import com.example.mercer.bluecareer.DataStruct.Url.Url;
import com.example.mercer.bluecareer.DataStruct.Url.UserUrl;
import com.example.mercer.bluecareer.DataStruct.User;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Response;

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


    //邮箱验证
    private boolean isEmail(String strEmail) {
        String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        if (TextUtils.isEmpty(strPattern)) {
            return false;
        } else {
            return strEmail.matches(strPattern);
        }
    }

    //检查输入邮箱和密码的合法性
    public String localCheck(String email,String key){
        if (!isEmail(email)){
            return "请输入正确的邮箱";
        }
        if(key.length()<6||key.length()>16){
            return "密码长度不能小于6或者大于16";
        }
        return "true";
    }

    public String localCheck(String email,String key,String id){
        if(id.length()<6||id.length()>16){
            return "用户名长度不能小于6或者大于16";
        }
        return localCheck(email,key);
    }

    public boolean login(LoginActivity.LoginData data) throws IOException {
        //连接数据库查询用户名和密码是否正确
        UserUrl url = new UserUrl("login");
        String json = new Gson().toJson(data);
        RetureCode result = ServerManager.GetInstance().PostSync(url,json);
        if (result.code!=0)
            return false;
        SystemManager.getInstance().AccessKey = (String)result.data;
        return true;
    }

    public boolean tryRegist(String email) throws IOException {
        //check username and email in database
        UserUrl url = new UserUrl("email_exit?email="+email);
        RetureCode result = ServerManager.GetInstance().GetSync(url);

        if ((boolean)result.data)
            return false;
        return true;
    }

    public boolean regist(RegistActivity.RegistUserData user) throws IOException {
        UserUrl url = new UserUrl("add");
        String json = new Gson().toJson(user);
        RetureCode result = ServerManager.GetInstance().PostSync(url,json);
        if (result.code == 0)
            return true;
        return false;
    }

    public Bitmap getImage(String id){
        //从本地数据库中获取头像
        return null;
    }
}
