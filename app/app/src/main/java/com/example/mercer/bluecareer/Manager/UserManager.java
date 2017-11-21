package com.example.mercer.bluecareer.Manager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import com.example.mercer.bluecareer.Activities.BActivity;
import com.example.mercer.bluecareer.Activities.RegistActivity;
import com.example.mercer.bluecareer.DataStruct.JsonStruct.LoginData;
import com.example.mercer.bluecareer.DataStruct.JsonStruct.ReturnCode;
import com.example.mercer.bluecareer.DataStruct.Url.ImageUrl;
import com.example.mercer.bluecareer.DataStruct.Url.UserUrl;
import com.example.mercer.bluecareer.DataStruct.User;
import com.example.mercer.bluecareer.Database.LocalUser;
import com.example.mercer.bluecareer.Database.LocalUserStorer;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GreySky on 2017/10/21.
 */
public class UserManager {
    private static UserManager _instance;
    public User _currentUser;

    private UserManager() {
        _currentUser = new User();
    }

    public static UserManager getInstance() {
        if (_instance == null) {
            _instance = new UserManager();
        }
        return _instance;
    }

    public User getUserInsatance(){
        return _currentUser;
    }


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

    public boolean login(BActivity activity,LoginData data) throws IOException {
        //连接数据库查询用户名和密码是否正确
        UserUrl url = new UserUrl("/login");
        String json = new Gson().toJson(data);
        ReturnCode result = ServerManager.GetInstance().RequestSync(ServerManager.Method.post,url,json);
        if (result.code!=0)
            return false;

        LinkedTreeMap userData = (LinkedTreeMap)result.data;
        int id = ((Double)userData.get("id")).intValue();
        String userName = (String) userData.get("userName");
        String realName = (String) userData.get("realName");
        String accessKey = (String) userData.get("accessKey");
        String imagePath = (String) userData.get("imagePath");
        String careerMessage = (String) userData.get("careerMessage");
        String qq = (String) userData.get("qq");

        _currentUser = new User(data.email,userName);
        _currentUser._password = data.password;
        _currentUser._name = realName;
        _currentUser._major = careerMessage;
        _currentUser._qq = qq;
        _currentUser.SetHeader(id,accessKey);

        _currentUser._image = null;
        Bitmap image = GetImage(activity,data.email);
        if (image != null)
            _currentUser._image = image;

        if (imagePath!=null){
            _currentUser._image = GetImageOnline(imagePath);
        }

        LocalUserStorer.Store(activity,_currentUser._email,_currentUser._password);

        return true;
    }

    public boolean tryRegist(String email) throws IOException {
        //check username and email in database
        UserUrl url = new UserUrl("/email_exist?email="+email);
        ReturnCode result = ServerManager.GetInstance().RequestSync(ServerManager.Method.get,url);

        if ((boolean) result.data)
            return false;
        return true;
    }

    public boolean regist(RegistActivity.RegistUserData user) throws IOException {
        UserUrl url = new UserUrl("/add");
        String json = new Gson().toJson(user);
        ReturnCode result = ServerManager.GetInstance().RequestSync(ServerManager.Method.post,url,json);
        if (result.code == 0){
            return true;
        }
        return false;
    }

    public Bitmap GetImageOnline() throws IOException{
        String dir = GetImagePathOnline();
        if (dir==null)
            return null;
        return GetImageOnline(dir);
    }

    //获取用户头像的网络地址
    private String GetImagePathOnline() throws IOException {
        UserUrl url = new UserUrl("/image_path");
        ReturnCode result = ServerManager.GetInstance().RequestSync(ServerManager.Method.get,url,_currentUser.GetHeader());
        if (result.data == null)
            return null;
        return result.data.toString();
    }

    private Bitmap GetImageOnline(String path) throws IOException{
        ImageUrl url = new ImageUrl(path);
        return ServerManager.GetInstance().RequestFileSync(url,_currentUser.GetHeader());
    }

    public void SetImage(BActivity activity) throws IOException {
        String dir = _currentUser.SaveImage(activity);
        if (dir == null)
            return;
        UserUrl url = new UserUrl("/image_upload");
        ServerManager.GetInstance().RequestAsync(ServerManager.Method.post, url, new File(dir),_currentUser.GetHeader(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                SystemManager.getInstance().PrintLog(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //SystemManager.getInstance().PrintLog(response.body().string());
                ReturnCode code = new Gson().fromJson(response.body().string(),ReturnCode.class);
            }
        });
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
    /*public User getUserInfo() throws IOException {
        //请求url，测试使用，后期改为按照主键查询
        UserUrl url = new UserUrl("/list");

        //请求远程数据
        ReturnCode resultJson = ServerManager.GetInstance().RequestSync(ServerManager.Method.get, url);
        //校验结果
        if (resultJson.code != 0) {
            return _currentUser;
        }
        ArrayList list = (ArrayList<String>) resultJson.data;
        JSONObject jsonObject = new JSONObject((LinkedTreeMap) list.get(0));

        //更新用户信息
        updateUserInstance(jsonObject);

        return _currentUser;
    }*/

    /**
     * 更新用户单例信息
     *
     * @param jsonObject
     */
    public void updateUserInstance(JSONObject jsonObject) {
        Object object;

        int id;
        String key;

        id = ((Double)jsonObject.optDouble("id")).intValue();
        _currentUser._username = (object = jsonObject.optString("userName")) == "" ? _currentUser._username : (String) object;
        _currentUser._name = (object = jsonObject.optString("realName")) == "" ? _currentUser._name : (String) object;
        _currentUser._password = (object = jsonObject.optString("password")) == "" ? _currentUser._password : (String) object;
        _currentUser._email = (object = jsonObject.optString("email")) == "" ? _currentUser._email : (String) object;
        key = (object = jsonObject.optString("accessKey")) == "" ? null : (String) object;
        _currentUser.SetHeader(id,key);
        //处理头像
        //jsonObject.optString("imagePath"));
        _currentUser._qq = (object = jsonObject.optString("qq")) == "" ? _currentUser._qq : (String) object;
        _currentUser._major = (object = jsonObject.optString("major")) == "" ? _currentUser._major : (String) object;
    }

    /**
     * 更新用户信息到服务器
     */
    public int updateUserinfo2Server(String json) throws IOException {
        //请求url，测试使用，后期改为按照主键查询
        UserUrl url = new UserUrl("/modify");
        ServerManager.GetInstance().RequestAsync(ServerManager.Method.put,url,json,_currentUser.GetHeader());
        return 0;
    }
}