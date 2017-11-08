package com.example.mercer.bluecareer.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mercer.bluecareer.CircleImageView;
import com.example.mercer.bluecareer.Manager.SystemManager;
import com.example.mercer.bluecareer.Manager.UserManager;
import com.example.mercer.bluecareer.R;

import java.io.IOException;

public class LoginActivity extends BActivity {

    CircleImageView _image;
    EditText _username;
    EditText _key;
    Button _login;
    Button _qq;
    Button _weixin;
    Button _weibo;
    Button _regist;
    Button _forgetKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置XML布局文件
        setContentView(R.layout.activity_login);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        getView();

        setListener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    if (data!=null){
                        _username.setText(data.getStringExtra("email"));
                        _key.setText(data.getStringExtra("key"));
                    }
                }
                break;
            default:
        }
    }

    @Override
    final protected void getView(){
        _username = (EditText)findViewById(R.id.username_edit);
        _key = (EditText)findViewById(R.id.key_edit);
        _login = (Button)findViewById(R.id.button_login);
        _qq = (Button)findViewById(R.id.logo_qq);
        _weixin = (Button)findViewById(R.id.logo_weixin);
        _weibo = (Button)findViewById(R.id.logo_weibo);
        _regist = (Button)findViewById(R.id.button_regist);
        _forgetKey = (Button)findViewById(R.id.forgetKey);
        _image = (CircleImageView)findViewById(R.id.login_image);
    }

    private void setImage(Bitmap image){
        if (image == null)
            _image.setImageBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.default_image));
        else
            _image.setImageBitmap(image);
    }

    @Override
    final protected void setListener(){
        final LoginActivity activity = this;

        //输入用户名时动态改变头像
        _username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //alextest
                if (s.toString().equals("GreySky0012")) {
                    setImage(BitmapFactory.decodeResource(activity.getResources(), R.drawable.test_image));
                    return;
                }

                Bitmap image = UserManager.getInstance().getImage(s.toString());
                setImage(image);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //注册帐号
        _regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SystemManager.getInstance().toActivityForResult(activity,RegistActivity.class,1);
            }
        });

        _login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = _username.getText().toString();
                String key = _key.getText().toString();
                String checkResult = UserManager.getInstance().localCheck(email,key);
                if(!checkResult.equals("true")){
                    showToast(checkResult);
                    return;
                }
                try {
                    if(!UserManager.getInstance().login(new LoginData(email,key))){
                        showToast("邮箱或密码错误");
                        return;
                    }
                }
                catch (IOException e){
                    showToast("没有网络");
                }
                showToast("登录成功");
                SystemManager.getInstance().toActivity(activity, MainActivity.class);
            }
        });

        _forgetKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SystemManager.getInstance().toActivityWithNoFinish(activity,ForgetKeyActivity.class);
            }
        });

        _qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        _weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        _weibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public class LoginData{
        public String email;
        public String password;
        public LoginData(String e,String p){
            email = e;
            password = p;
        }
    }
}
