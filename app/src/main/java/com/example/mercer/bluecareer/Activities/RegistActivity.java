package com.example.mercer.bluecareer.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mercer.bluecareer.ImageChooser;
import com.example.mercer.bluecareer.ImageLoadActivity;
import com.example.mercer.bluecareer.Manager.SystemManager;
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

    private boolean checkEdit(){
        String _id = this._id.getText().toString();
        if(_id.length()<6||_id.length()>16){
            showToast("用户名长度不能小于6或者大于16");
            return false;
        }
        if(!(_id.charAt(0) <= 'z'&&_id.charAt(0) >= 'a')&&!(_id.charAt(0) <= 'Z'&&_id.charAt(0) >= 'A')){
            showToast("用户名必须以数字开头");
            return false;
        }
        String _name = this._name.getText().toString();
        if(_name.isEmpty()){
            showToast("真实姓名不能为空");
            return false;
        }
        String _key = this._key.getText().toString();
        if(_key.length()<6||_key.length()>16){
            showToast("密码长度不能小于6或者大于16");
            return false;
        }
        String _mail = this._mail.getText().toString();
        return true;
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
                SystemManager.getInstance().PrintLog(activity._id.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("id",activity._id.getText().toString());
                intent.putExtra("key",activity._key.getText().toString());
                SystemManager.getInstance().returnActivity(activity,intent);
            }
        });
    }
}
