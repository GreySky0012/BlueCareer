package com.example.mercer.bluecareer.Manager;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.example.mercer.bluecareer.Activities.BActivity;

/**
 * Created by GreySky on 2017/9/9.
 */
public class SystemManager {

    private static SystemManager _instance;
    public String AccessKey;

    private SystemManager(){}
    public static  SystemManager getInstance() {
        if (_instance == null){
            _instance = new SystemManager();
        }
        return  _instance;
    }

    public void toActivity(BActivity activity,Class className){
        final Intent intent = new Intent(activity,className);
        activity.startActivity(intent);
        activity.finish();
    }

    public void toActivityWithNoFinish(BActivity activity,Class className){
        final Intent intent = new Intent(activity,className);
        activity.startActivity(intent);
    }

    public void returnActivity(BActivity activity,Intent intent){
        if (intent!=null)
            SystemManager.getInstance().PrintLog("111");
        else
            SystemManager.getInstance().PrintLog("000");
        activity.setResult(Activity.RESULT_OK,intent);
        activity.finish();
    }

    public void toActivityForResult(BActivity activity,Class className,int code){
        final Intent intent = new Intent(activity,className);
        activity.startActivityForResult(intent,code);
    }

    public String SystemPath(BActivity context){
        return context.getCacheDir().getPath();
    }

    public void PrintLog(String message){
        Log.d("BlueCareer",message);
    }
}
