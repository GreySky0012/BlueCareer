package com.example.mercer.bluecareer.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mercer.bluecareer.CircleImageView;
import com.example.mercer.bluecareer.DataStruct.User;
import com.example.mercer.bluecareer.ImageChooser;
import com.example.mercer.bluecareer.ImageLoadActivity;
import com.example.mercer.bluecareer.Manager.SystemManager;
import com.example.mercer.bluecareer.Manager.UserManager;
import com.example.mercer.bluecareer.R;

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

    //邮箱验证
    public boolean isEmail(String strEmail) {
        String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        if (TextUtils.isEmpty(strPattern)) {
            return false;
        } else {
            return strEmail.matches(strPattern);
        }
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
                if (!isEmail(email)){
                    showToast("请输入正确的邮箱");
                    return;
                }

                String checkResult = UserManager.getInstance().localCheck(id,key);
                if (!checkResult.equals("true")){
                    showToast(checkResult);
                    return;
                }

                if (!UserManager.getInstance().tryRegist(id,email)){
                    showToast("帐号或者邮箱已存在");
                    return;
                }

                User user = new User(id);
                user._key = key;
                user._email = email;
                user._imgae = image;
                user._qq = qq;
                user._name = name;
                user._imgae = image;

                UserManager.getInstance().regist(user);

                SystemManager.getInstance().PrintLog(activity._id.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("id",user._username);
                intent.putExtra("key",user._key);
                SystemManager.getInstance().returnActivity(activity,intent);
            }
        });
    }
}
