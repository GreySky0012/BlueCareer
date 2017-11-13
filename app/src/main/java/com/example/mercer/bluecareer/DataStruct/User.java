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

/**
 * Created by GreySky on 2017/9/9.
 */
public class User {
    public int _id;//id、账号
    public String _username;//昵称
    public String _name;//真实姓名
    public String _password;
    public String _key;
    public String _email;
    public String _major;
    public Bitmap _image;
    public String _qq;

    public User(){};

    public User(String email, String username, Bitmap image){
        _email = email;
        _username = username;
        _image = image;
    }

    public String SaveImage(BActivity context) throws IOException {
        if (_image == null){
            return null;
        }
        String dir = UserManager.getInstance().GetImagePath(context,_email);
        File file = new File(dir);
        FileOutputStream out = new FileOutputStream(file);
        _image.compress(Bitmap.CompressFormat.JPEG,100,out);
        out.flush();
        out.close();
        return dir;
    }
}
