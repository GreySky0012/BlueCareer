package com.example.mercer.bluecareer.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mercer.bluecareer.CircleImageView;
import com.example.mercer.bluecareer.Fragments.IntroduceFragment;
import com.example.mercer.bluecareer.Fragments.LearnFragment;
import com.example.mercer.bluecareer.Fragments.MainFragment;
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

    /**
     * 1.社区 2.我要了解 3.我要学习
     */
    private Fragment[] _fragments;
    private String[] _titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** 不启用多线程，网络请求设置 */
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        getView();

        setListener();

        InitFragment();
    }

    /**
     * 初始化碎片
     */
    private void InitFragment() {
        _titles = new String[]{"社区", "我要了解", "自我学习"};
        _fragments = new Fragment[3];

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        _fragments[0] = new MainFragment();
        _fragments[1] = new IntroduceFragment();
        _fragments[2] = new LearnFragment();
        transaction.replace(R.id.fragment_content, _fragments[0]);
        transaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bitmap image = UserManager.getInstance()._currentUser._image;
        if (image == null) {
            image = BitmapFactory.decodeResource(this.getResources(), R.drawable.image_defult_navigation);
        }
        //加载头像
        _image.setImageBitmap(image);
        //加载用户名
        _userName.setText(UserManager.getInstance()._currentUser._username);
    }

    /**
     * 切换碎片
     *
     * @param index
     */
    private void SwitchFragment(int index) {
        FragmentManager fm = getFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();

        transaction.replace(R.id.fragment_content, _fragments[index]);
        // transaction.addToBackStack();
        // 事务提交
        transaction.commit();
    }

    //取得各个组件的引用
    protected void getView() {
        _drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        _navigation = (NavigationView) findViewById(R.id.nav_view);
        _header = _navigation.getHeaderView(0);
        _setting = (ImageButton) _header.findViewById(R.id.setting);
        _image = (CircleImageView) _header.findViewById(R.id.image);
        _userName = (TextView) _header.findViewById(R.id.username);
        _title = (TextView) (findViewById(R.id.title_layout).findViewById(R.id.fragment_id));
        _navigationButton = (ImageButton) (findViewById(R.id.title_layout).findViewById(R.id.navigation_button));
    }

    /**
     * 设置监听器
     */
    protected void setListener() {
        final MainActivity activity = this;
        /** 点击设置跳转到个人信息 */
        _setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SystemManager.getInstance().toActivityWithNoFinish(activity, InfoActivity.class);
            }
        });

        /** 点击导航切换碎片 */
        _navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = 0;
                switch (item.getItemId()) {
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
    }

    ;
}
