package com.example.mercer.bluecareer.Activities;

import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mercer.bluecareer.R;

public class MainActivity extends BActivity {

    private NavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getView();

        setListener();
    }

    //取得各个组件的引用
    protected void getView(){
        navigation = (NavigationView)findViewById(R.id.nav_view);
    };

    protected void setListener(){};
}
