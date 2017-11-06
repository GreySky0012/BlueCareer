package com.example.mercer.bluecareer.Activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mercer.bluecareer.CircleImageView;
import com.example.mercer.bluecareer.DataStruct.User;
import com.example.mercer.bluecareer.Manager.SystemManager;
import com.example.mercer.bluecareer.Manager.UserManager;
import com.example.mercer.bluecareer.R;
import com.example.mercer.bluecareer.RegistActivity;

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
        //取得各个组件的引用
        getView();

        setListener();
    }

    final protected void getView(){
        _username = (EditText)findViewById(R.id.username_edit);
        _key = (EditText)findViewById(R.id.key_edit);
        _login = (Button)findViewById(R.id.button_login);
        _qq = (Button)findViewById(R.id.logo_qq);
        _weixin = (Button)findViewById(R.id.logo_weixin);
        _weibo = (Button)findViewById(R.id.logo_weibo);
        _regist = (Button)findViewById(R.id.button_regist);
        _forgetKey = (Button)findViewById(R.id.forgetKey);
    }

    private void setImage(Bitmap image){
        if (image == null)
            _image.setImageBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.default_image));
        else
            _image.setImageBitmap(image);
    }

    public void setUsername(String id){
        _username.setText(id);
    }

    //检查输入用户名的合法性
    private boolean checkId(String id){
        if(id.length()<6||id.length()>16){
            showToast("用户名长度不能小于6或者大于16");
            return false;
        }
        if(!(id.charAt(0) <= 'z'&&id.charAt(0) >= 'a')&&!(id.charAt(0) <= 'Z'&&id.charAt(0) >= 'A')){
            showToast("用户名必须以数字开头");
            return false;
        }
        return true;
    }

    //检查输入密码的合法性
    private boolean checkKey(String key){
        if(key.length()<6||key.length()>16){
            showToast("密码长度不能小于6或者大于16");
            return false;
        }
        return true;
    }

    final protected void setListener(){
        final LoginActivity activity = this;

        //输入用户名时动态改变头像
        _username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
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
                SystemManager.getInstance().toActivity(activity,RegistActivity.class);
            }
        });

        _login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = _username.getText().toString();
                String key = _key.getText().toString();
                if((!checkId(id))||(!checkKey(key))){
                    return;
                }
                if(!UserManager.getInstance().login(id,key)){
                    showToast("用户名或密码错误");
                    return;
                }
                SystemManager.getInstance().toActivity(activity, MainActivity.class);
            }
        });

        _forgetKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
}
