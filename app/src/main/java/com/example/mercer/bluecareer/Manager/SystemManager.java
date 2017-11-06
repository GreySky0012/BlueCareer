package com.example.mercer.bluecareer.Manager;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.example.mercer.bluecareer.Activities.BActivity;
import com.example.mercer.bluecareer.Activities.LoginActivity;
import com.example.mercer.bluecareer.DataStruct.User;

/**
 * Created by GreySky on 2017/9/9.
 */
public class SystemManager {

    public BActivity nowActivity;

    private static SystemManager _instance;
    private SystemManager(){}
    public static  SystemManager getInstance() {
        if (_instance == null){
            _instance = new SystemManager();
        }
        return  _instance;
    }

    public void toActivity(BActivity activity,Class className){
        final Intent intent = new Intent(activity,className.getClass());
        activity.startActivity(intent);
        activity.finish();
    }

    public void PrintLog(String message){
        Log.d("BlueCareer",message);
    }
}
