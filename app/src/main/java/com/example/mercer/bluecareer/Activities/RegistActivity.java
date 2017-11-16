package com.example.mercer.bluecareer.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mercer.bluecareer.DataStruct.User;
import com.example.mercer.bluecareer.ImageChooser;
import com.example.mercer.bluecareer.Manager.SystemManager;
import com.example.mercer.bluecareer.Manager.UserManager;
import com.example.mercer.bluecareer.R;

import java.io.IOException;

/**
 * Created by 53017_000 on 2017/3/7.
 */
public class RegistActivity extends ImageLoadActivity {
    private Button _back;
    private Button _regist;
    private EditText _id;
    private EditText _name;
    private EditText _key;
    private EditText _mail;
    private EditText _qq;
    private Button _job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        getView();

        setListener();
    }

    @Override
    protected void getView(){
        circle_image = (ImageChooser)findViewById(R.id.image);
        _back = (Button)findViewById(R.id.back);
        _regist = (Button)findViewById(R.id.regist);
        _id = (EditText)findViewById(R.id.id);
        _name = (EditText)findViewById(R.id.name);
        _key = (EditText)findViewById(R.id.key);
        _mail = (EditText)findViewById(R.id.mail);
        _qq = (EditText)findViewById(R.id.qq);
        _job = (Button)findViewById(R.id.job);
    }

    @Override
    protected void setListener() {
        final RegistActivity activity = this;

        _back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SystemManager.getInstance().returnActivity(activity,null);
            }
        });

        _regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = activity._id.getText().toString();
                String key = activity._key.getText().toString();
                String email = activity._mail.getText().toString();
                circle_image.setDrawingCacheEnabled(true);
                Bitmap image = activity.circle_image.getDrawingCache();
                circle_image.setDrawingCacheEnabled(false);
                if (image == null){
                    image = BitmapFactory.decodeResource(getResources(),R.drawable.default_image);
                }
                String qq = activity._qq.getText().toString();
                String name = activity._name.getText().toString();

                if (id.equals("")){
                    showToast("用户名不能为空");
                    return;
                }
                if (name.equals("")){
                    showToast("真实姓名不能为空");
                    return;
                }
                if (key.equals("")){
                    showToast("密码不能为空");
                    return;
                }
                if (email.equals("")) {
                    showToast("邮箱不能为空");
                    return;
                }

                String checkResult = UserManager.getInstance().localCheck(email,key);
                if (!checkResult.equals("true")){
                    showToast(checkResult);
                    return;
                }

                User user = new User(email,id,image);
                user._key = key;
                user._qq = qq;
                user._name = name;

                try {
                    if(!UserManager.getInstance().tryRegist(email)){
                        showToast("该邮箱已被使用");
                        return;
                    }
                    if (!UserManager.getInstance().regist(new RegistUserData(id,email,key,qq,name))){
                        showToast("未知错误");
                        return;
                    }
                } catch (IOException e) {
                    SystemManager.getInstance().PrintLog(e.getMessage());
                    showToast("网络异常");
                    return;
                }

                try {
                    user.SaveImage(activity);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent();
                intent.putExtra("email",user._email);
                intent.putExtra("key",user._key);
                SystemManager.getInstance().returnActivity(activity,intent);
            }
        });
    }

    public class RegistUserData{
        public String userName;
        public String realName;
        public String password;
        public String email;
        public String qq;

        public RegistUserData(String u,String e,String p,String q,String n){
            userName = u;
            email = e;
            password = p;
            qq = q;
            realName = n;
        }
    }
}
