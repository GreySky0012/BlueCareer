package com.example.mercer.bluecareer.Activities;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
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

    private DrawerLayout _drawer;
    private NavigationView _navigation;
    private View _header;
    private ImageButton _setting;
    private CircleImageView _image;
    private TextView _userName;
    private TextView _title;
    private ImageButton _navigationButton;

    private Fragment[] _fragments;
    private String[] _titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getView();

        setListener();

        InitFragment();
    }

    private void InitFragment(){
        _titles = new String[]{"社区","我要了解","自我学习"};
        _fragments = new Fragment[3];
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
        _drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        _navigation = (NavigationView)findViewById(R.id.nav_view);
        _header = _navigation.getHeaderView(0);
        _setting = (ImageButton)_header.findViewById(R.id.setting);
        _image = (CircleImageView)_header.findViewById(R.id.image);
        _userName = (TextView)_header.findViewById(R.id.username);
        _title = (TextView)(findViewById(R.id.title_layout).findViewById(R.id.fragment_id));
        _navigationButton = (ImageButton)(findViewById(R.id.title_layout).findViewById(R.id.navigation_button));
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
                int id;
                switch (item.getItemId()){
                    case R.id.nav_main:
                        id = 0;
                        break;
                    case R.id.nav_brif:
                        id = 1;
                        break;
                    case R.id.nav_study:
                        id = 2;
                        break;
                    default:
                        id = 0;
                }
                SwitchFragment(id);
                _title.setText(_titles[id]);
                _drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        _navigationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _drawer.openDrawer(GravityCompat.START);
            }
        });
    };
}
