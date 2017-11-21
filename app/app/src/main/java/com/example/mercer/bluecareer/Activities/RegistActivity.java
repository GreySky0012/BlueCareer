package com.example.mercer.bluecareer.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mercer.bluecareer.DataStruct.JsonStruct.LoginData;
import com.example.mercer.bluecareer.DataStruct.User;
import com.example.mercer.bluecareer.ImageChooser;
import com.example.mercer.bluecareer.Manager.SystemManager;
import com.example.mercer.bluecareer.Manager.UserManager;
import com.example.mercer.bluecareer.R;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Arrays;

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
    private View _job;
    private TextView _major;
    // 多选提示框
    private AlertDialog _majorChooseDialog;

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
        _back = findViewById(R.id.back);
        _regist = findViewById(R.id.regist);
        _id = findViewById(R.id.id);
        _name = findViewById(R.id.name);
        _key = findViewById(R.id.key);
        _mail = findViewById(R.id.mail);
        _qq = findViewById(R.id.qq);
        _job = findViewById(R.id.job);
        _major = findViewById(R.id.major);
    }

    @Override
    protected void setListener() {
        final RegistActivity activity = this;

        _back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SystemManager.getInstance().toActivity(activity,LoginActivity.class);
            }
        });

        _regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = activity._id.getText().toString();
                String key = activity._key.getText().toString();
                String email = activity._mail.getText().toString();
                String major = activity._major.getText().toString();
                Bitmap image = null;
                if (selected){
                    circle_image.setDrawingCacheEnabled(true);
                    image = activity.circle_image.getDrawingCache();
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

                User user = new User(email,id);
                user._password = key;
                user._qq = qq;
                user._name = name;
                user._image = image;
                user._major = major;

                try {
                    if(!UserManager.getInstance().tryRegist(email)){
                        showToast("该邮箱已被使用");
                        return;
                    }
                    if (UserManager.getInstance().regist(new RegistUserData(id,email,key,qq,name,major))) {
                        UserManager.getInstance().login(activity,new LoginData(email,key));
                        UserManager.getInstance()._currentUser._image = image;
                        UserManager.getInstance().SetImage(activity);
                    }else{
                        showToast("未知错误");
                        return;
                    }
                } catch (IOException e) {
                    SystemManager.getInstance().PrintLog(e.getMessage());
                    showToast("网络异常");
                    return;
                }

                SystemManager.getInstance().toActivity(activity,MainActivity.class);
            }
        });

        _job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMutilAlertDialog();
            }
        });
    }

    /**
     * 职业选择多选框
     */
    public void showMutilAlertDialog() {
        //所有职业
        final String majorArray[] = getResources().getStringArray(R.array.major_array);
        //当前已选职业bool数组，根据Strings.xml的顺序严格排列
        final boolean currChoosed[] = new boolean[majorArray.length];
        Arrays.fill(currChoosed, false);

        // 创建一个AlertDialog建造者
        AlertDialog.Builder majorChooseDialogBuilder = new AlertDialog.Builder(this);
        // 设置标题
        majorChooseDialogBuilder.setTitle("请选择您的职业（多选）");
        /* 参数介绍
         * 第一个参数：弹出框的信息集合，一般为字符串集合
         * 第二个参数：被默认选中的，一个布尔类型的数组
         * 第三个参数：勾选事件监听
         */
        majorChooseDialogBuilder.setMultiChoiceItems(majorArray, currChoosed, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                // dialog：不常使用，弹出框接口
                // which：勾选或取消的是第几个
                // isChecked：是否勾选
                if (isChecked) {
                    // 选中
                    currChoosed[which] = true;
                    //Toast.makeText(InfoActivity.this, "选中" + majorArray[which], Toast.LENGTH_SHORT).show();
                } else {
                    // 取消选中
                    currChoosed[which] = false;
                    //Toast.makeText(InfoActivity.this, "取消选中" + majorArray[which], Toast.LENGTH_SHORT).show();
                }
            }
        });
        //确定按钮点击事件
        majorChooseDialogBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                String newMajor = "";
                for (int i = 0; i < majorArray.length; i++) {
                    if (currChoosed[i]) {
                        newMajor += majorArray[i] + "|";
                    }
                }
                //清除最后的逗号
                newMajor = newMajor.substring(0, newMajor.length() - 1);
                //将选择填充到界面
                _major.setText(newMajor);

                // 关闭提示框
                _majorChooseDialog.dismiss();
            }
        });
        //关闭按钮点击事件
        majorChooseDialogBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO 业务逻辑代码
                // 关闭提示框
                _majorChooseDialog.dismiss();
            }
        });
        _majorChooseDialog = majorChooseDialogBuilder.create();
        _majorChooseDialog.show();
    }

    public class RegistUserData{
        public String userName;
        public String realName;
        public String password;
        public String email;
        public String qq;
        public String careerMessage;

        public RegistUserData(String u,String e,String p,String q,String n,String c){
            userName = u;
            email = e;
            password = p;
            qq = q;
            realName = n;
            careerMessage = c;
        }
    }
}
