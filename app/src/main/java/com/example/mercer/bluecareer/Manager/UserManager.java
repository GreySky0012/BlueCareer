package com.example.mercer.bluecareer.Manager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import com.example.mercer.bluecareer.Activities.BActivity;
import com.example.mercer.bluecareer.Activities.LoginActivity;
import com.example.mercer.bluecareer.Activities.RegistActivity;
import com.example.mercer.bluecareer.DataStruct.ReturnCode;
import com.example.mercer.bluecareer.DataStruct.Url.UserUrl;
import com.example.mercer.bluecareer.DataStruct.User;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by GreySky on 2017/10/21.
 */
public class UserManager {
    private static UserManager _instance;
    private User _user;

    private UserManager() {
        _user = new User();
    }

    public static UserManager getInstance() {
        if (_instance == null) {
            _instance = new UserManager();
        }
        return _instance;
    }

    public User _currentUser;


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
    public String localCheck(String email, String key) {
        if (!isEmail(email)) {
            return "请输入正确的邮箱";
        }
        if (key.length() < 6 || key.length() > 16) {
            return "密码长度不能小于6或者大于16";
        }
        return "true";
    }

    public String localCheck(String email, String key, String id) {
        if (id.length() < 6 || id.length() > 16) {
            return "用户名长度不能小于6或者大于16";
        }
        return localCheck(email, key);
    }

    public boolean login(LoginActivity.LoginData data) throws IOException {
        //连接数据库查询用户名和密码是否正确
        UserUrl url = new UserUrl("login");
        String json = new Gson().toJson(data);
        ReturnCode result = ServerManager.GetInstance().RequestSync(ServerManager.Method.post,url,json);
        if (result.code!=0)
            return false;
        SystemManager.getInstance().AccessKey = (String) result.data;
        return true;
    }

    public boolean tryRegist(String email) throws IOException {
        //check username and email in database
        UserUrl url = new UserUrl("email_exist?email="+email);
        ReturnCode result = ServerManager.GetInstance().RequestSync(ServerManager.Method.get,url);

        if ((boolean) result.data)
            return false;
        return true;
    }

    public boolean regist(RegistActivity.RegistUserData user) throws IOException {
        UserUrl url = new UserUrl("add");
        String json = new Gson().toJson(user);
        //ReturnCode result = ServerManager.GetInstance().RequestSync(ServerManager.Method.post,url,json);
        //if (result.code == 0){
            _currentUser = new User(user.email,user.userName,null);
            return true;
        //}
        //return false;
    }

    public Bitmap GetOnlineImage() throws IOException {
        UserUrl url = new UserUrl("image_path?email="+_currentUser._email);
        ReturnCode result = ServerManager.GetInstance().RequestSync(ServerManager.Method.get,url,_currentUser.GetKeyHeader());
        return null;
    }

    public void SetImage(BActivity activity,User user) throws IOException {
        //UserUrl url = new UserUrl("");
        //ReturnCode result = ServerManager.GetInstance().RequestSync(ServerManager.Method);

        user.SaveImage(activity);
    }

    public String GetImagePath(BActivity context,String email){
        File file = new File(SystemManager.getInstance().SystemPath(context)+"/Image");
        if (!file.exists()){
            file.mkdir();
        }else if (!file.isDirectory()){
            file.delete();
            file.mkdir();
        }
        return SystemManager.getInstance().SystemPath(context)+"/Image/"+email+".jpg";
    }

    public Bitmap GetImage(BActivity context, String email) {
        //获取头像
        String path = GetImagePath(context, email);

        File file = new File(path);
        if (!file.exists())
            return null;

        Bitmap image = BitmapFactory.decodeFile(path);
        return image;
    }

    /**
     * 获取用户信息
     */
    public User getUserInfo() throws IOException {
        //请求url，测试使用，后期改为按照主键查询
        UserUrl url = new UserUrl("list");

        //请求远程数据
        ReturnCode resultJson = ServerManager.GetInstance().RequestSync(ServerManager.Method.get, url);
        //校验结果
        if (resultJson.code != 0) {
            return _user;
        }
        ArrayList list = (ArrayList<String>) resultJson.data;
        JSONObject jsonObject = new JSONObject((LinkedTreeMap) list.get(0));

        //更新用户信息
        updateUserInstance(jsonObject);

        return _user;
    }

    /**
     * 更新用户单例信息
     *
     * @param jsonObject
     */
    public void updateUserInstance(JSONObject jsonObject) {
        Object object;

        _user._id = jsonObject.optInt("id") == 0 ? _user._id : jsonObject.optInt("id");
        _user._username = (object = jsonObject.optString("userName")) == "" ? _user._username : (String) object;
        _user._name = (object = jsonObject.optString("realName")) == "" ? _user._name : (String) object;
        _user._password = (object = jsonObject.optString("password")) == "" ? _user._password : (String) object;
        _user._email = (object = jsonObject.optString("email")) == "" ? _user._email : (String) object;
        _user._key = (object = jsonObject.optString("accessKey")) == "" ? _user._key : (String) object;
        //处理头像
        //jsonObject.optString("imagePath"));
        _user._qq = (object = jsonObject.optString("qq")) == "" ? _user._qq : (String) object;
        _user._major = (object = jsonObject.optString("major")) == "" ? _user._major : (String) object;
    }
}