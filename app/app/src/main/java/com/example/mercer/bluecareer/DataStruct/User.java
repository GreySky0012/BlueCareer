package com.example.mercer.bluecareer.DataStruct;

import android.graphics.Bitmap;
import android.os.Environment;

import com.example.mercer.bluecareer.Activities.BActivity;
import com.example.mercer.bluecareer.Manager.SystemManager;
import com.example.mercer.bluecareer.Manager.UserManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by GreySky on 2017/9/9.
 */
public class User {
    public String _username;//昵称
    public String _name;//真实姓名
    public String _password;
    public String _email;
    public String _major;
    public Bitmap _image;
    public String _qq;
    private String _accessKey;
    private int _id;

    public User(){};

    public User(String email, String username){
        _email = email;
        _username = username;
        _image = null;
        _accessKey = "123456";
        _id = 0;
    }

    public void SetHeader(int id,String key){
        _id = id;
        _accessKey = key!=null?key:"123456";
    }

    public String SaveImage(BActivity context) throws IOException {
        if (_image == null){
            return null;
        }
        String dir = UserManager.getInstance().GetImagePath(context,_email);
        File file = new File(dir);
        if (!file.exists()){
            file.createNewFile();
        }else {
            file.delete();
        }
        FileOutputStream out = new FileOutputStream(file);
        _image.compress(Bitmap.CompressFormat.JPEG,100,out);
        out.flush();
        out.close();
        return dir;
    }

    public HashMap<String,String> GetHeader(){
        HashMap<String,String> header = new HashMap<>();
        header.put("id",_id+"");
        header.put("accessKey",_accessKey);
        return header;
    }

    public class UserData{
        String userName;
        String realName;
        String accessKey;
        String imagePath;
        String careerMessage;
        String qq;
    }
}
