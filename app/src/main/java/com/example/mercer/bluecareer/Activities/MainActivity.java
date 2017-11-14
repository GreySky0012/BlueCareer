package com.example.mercer.bluecareer.Activities;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mercer.bluecareer.CircleImageView;
import com.example.mercer.bluecareer.Manager.SystemManager;
import com.example.mercer.bluecareer.Manager.UserManager;
import com.example.mercer.bluecareer.R;

import java.io.IOException;

public class MainActivity extends BActivity {

    private NavigationView _navigation;
    private View _header;
    private ImageButton _setting;
    private CircleImageView _image;
    private TextView _userName;

    private Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getView();

        setListener();

        InitFragment();
    }

    private void InitFragment(){
        fragments = new Fragment[4];
    }

    @Override
    protected void onResume(){
        super.onResume();
        Bitmap image = UserManager.getInstance().GetImage(this,UserManager.getInstance()._currentUser._email);
        if (image == null)
            image = BitmapFactory.decodeResource(this.getResources(), R.drawable.image_defult_navigation);
        _image.setImageBitmap(image);
        _userName.setText(UserManager.getInstance()._currentUser._username);
    }

    private void SwitchFragment(int index){

    }

    //取得各个组件的引用
    protected void getView(){
        _navigation = (NavigationView)findViewById(R.id.nav_view);
        _header = _navigation.getHeaderView(0);
        _setting = (ImageButton)_header.findViewById(R.id.setting);
        _image = (CircleImageView)_header.findViewById(R.id.image);
        _userName = (TextView)_header.findViewById(R.id.username);
    };

    protected void setListener(){
        final MainActivity activity = this;
        _setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SystemManager.getInstance().toActivityWithNoFinish(activity,InfoActivity.class);
            }
        });

        _navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                SwitchFragment(item.getItemId());
                return false;
            }
        });
    };
}
