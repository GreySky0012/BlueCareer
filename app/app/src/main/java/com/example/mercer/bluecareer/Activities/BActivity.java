package com.example.mercer.bluecareer.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mercer.bluecareer.Manager.SystemManager;
import com.example.mercer.bluecareer.R;

/**
 * Created by GreySky on 2017/9/9.
 */
public abstract class BActivity extends Activity {

    protected void showToast(String message){
        Toast.makeText(BActivity.this,message,Toast.LENGTH_SHORT).show();
    }

    protected abstract void getView();
    protected abstract void setListener();
}
