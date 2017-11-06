package com.example.mercer.bluecareer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mercer.bluecareer.Activities.LoginActivity;

/**
 * Created by 53017_000 on 2017/3/7.
 */
public class RegistActivity extends ImageLoadActivity{
    private Button back;
    private Button regist;
    private EditText id;
    private EditText name;
    private EditText key;
    private EditText mail;
    private EditText qq;
    private Button job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        getView();

        final RegistActivity activity = this;

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(activity,LoginActivity.class);
                startActivity(intent);
                activity.finish();
            }
        });

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(activity,LoginActivity.class);
                startActivity(intent);
                activity.finish();
            }
        });
    }

    private boolean checkEdit(){
        String _id = id.getText().toString();
        if(_id.length()<6||_id.length()>16){
            showToast("用户名长度不能小于6或者大于16");
            return false;
        }
        if(!(_id.charAt(0) <= 'z'&&_id.charAt(0) >= 'a')&&!(_id.charAt(0) <= 'Z'&&_id.charAt(0) >= 'A')){
            showToast("用户名必须以数字开头");
            return false;
        }
        String _name = name.getText().toString();
        if(_name.isEmpty()){
            showToast("真实姓名不能为空");
            return false;
        }
        String _key = key.getText().toString();
        if(_key.length()<6||_key.length()>16){
            showToast("密码长度不能小于6或者大于16");
            return false;
        }
        String _mail = mail.getText().toString();
        return true;
    }

    private void showToast(String message){
        Toast.makeText(RegistActivity.this,message,Toast.LENGTH_SHORT).show();
    }

    private void getView(){
        circle_image = (ImageChooser)findViewById(R.id.image);
        back = (Button)findViewById(R.id.back);
        regist = (Button)findViewById(R.id.regist);
        id = (EditText)findViewById(R.id.id);
        name = (EditText)findViewById(R.id.name);
        key = (EditText)findViewById(R.id.key);
        mail = (EditText)findViewById(R.id.mail);
        qq = (EditText)findViewById(R.id.qq);
        job = (Button)findViewById(R.id.job);
    }
}
