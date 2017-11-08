package com.example.mercer.bluecareer.DataStruct;

import android.graphics.Bitmap;

/**
 * Created by GreySky on 2017/9/9.
 */
public class AppUser {
    public String _username;
    public String _key;
    public String _email;
    public String _name;
    public Bitmap _imgae;
    public String _qq;

    public AppUser(String email){
        _email = email;
    }
}
