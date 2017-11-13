package com.example.mercer.bluecareer.Activities;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mercer.bluecareer.DataStruct.User;
import com.example.mercer.bluecareer.Manager.UserManager;
import com.example.mercer.bluecareer.R;

import java.io.IOException;


/**
 * author: Husen
 * date: 2017.11.13
 * description：个人信息界面
 */
public class InfoActivity extends ImageLoadActivity {
    //定义字段Etxt变量，头像变量在父类中定义
    private TextView id;//账号
    private EditText userName;//昵称
    private EditText realName;
    private TextView email;
    private EditText qq;
    private EditText major;
    //更新按钮
    Button btnSubmit;

    private String FOR_LOG = "InfoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        /** 不启用多线程，网络请求设置 */
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //获取个人信息输入框操作
        getView();
        //填充个人信息
        fillPersInfo();
        //设置监听器 图片、更新
        setListener();
    }

    /**
     * 获取头像图片 imgView
     */
    @Override
    protected void getView() {
        circle_image = (ImageView) findViewById(R.id.imageChoose);

        id = (TextView) findViewById(R.id.id);
        userName = (EditText) findViewById(R.id.userName);
        realName = (EditText) findViewById(R.id.realName);
        email = (TextView) findViewById(R.id.email);
        qq = (EditText) findViewById(R.id.qq);
        major = (EditText) findViewById(R.id.major);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
    }

    /**
     * 填充个人信息
     */
    public void fillPersInfo() {
        //从服务端获取个人信息
        User user = UserManager.getInstance().getUserInsatance();
        try {
            user = UserManager.getInstance().getUserInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }

        id.setText(user._id + "");
        userName.setText(user._username);
        realName.setText(user._name);
        email.setText(user._email);
        qq.setText(user._qq);
        major.setText(user._major);
        //circle_image.setImageBitmap();
    }

    /**
     * 设置图片点击事件
     */
    @Override
    protected void setListener() {
        /** 更换头像点击 */
        circle_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoosePicDialog();
            }
        });

        /** 更新个人信息点击 */
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserInfo();
            }
        });
    }

    /**
     * 更新用户信息
     *
     * @return
     */
    public int updateUserInfo() {
        int result = -1;

        return result;
    }
}