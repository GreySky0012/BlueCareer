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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置XML布局文件
        setContentView(R.layout.activity_login);
        //取得各个组件的引用
        getView();

        setListener();

        SystemManager.getInstance().nowActivity = this;
    }

    protected void showToast(String message){
        Toast.makeText(BActivity.this,message,Toast.LENGTH_SHORT).show();
    }

    protected abstract void getView();
    protected abstract void setListener();
}
