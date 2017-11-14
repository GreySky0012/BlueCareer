package com.example.mercer.bluecareer.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.mercer.bluecareer.Manager.SystemManager;
import com.example.mercer.bluecareer.R;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends BActivity {

    private final int time = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置布局XML文件
        setContentView(R.layout.activity_start);
        //改变开始页面背景
        start();
    }

    @Override
    protected void getView() {}

    @Override
    protected void setListener() {}

    private void start(){
        final BActivity activity = this;
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                SystemManager.getInstance().toActivity(activity,LoginActivity.class);
            }
        };

        timer.schedule(timerTask, 1000 * time);
    }
}