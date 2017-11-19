package com.example.mercer.bluecareer.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mercer.bluecareer.DataStruct.JsonStruct.UserUpdateData;
import com.example.mercer.bluecareer.DataStruct.User;
import com.example.mercer.bluecareer.Manager.SystemManager;
import com.example.mercer.bluecareer.Manager.UserManager;
import com.example.mercer.bluecareer.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;


/**
 * author: Husen
 * date: 2017.11.13
 * description：个人信息界面
 */
public class InfoActivity extends ImageLoadActivity {
    //定义字段Etxt变量，头像变量在父类中定义
    private EditText userName;//昵称
    private EditText realName;
    private TextView email;
    private EditText qq;
    private TextView major;
    private ImageButton _back;
    //更新按钮
    Button btnSubmit;
    // 多选提示框
    private AlertDialog _majorChooseDialog;

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

        userName = (EditText) findViewById(R.id.userName);
        realName = (EditText) findViewById(R.id.realName);
        email = (TextView) findViewById(R.id.email);
        qq = (EditText) findViewById(R.id.qq);
        major = (TextView) findViewById(R.id.major);
        _back = findViewById(R.id.back);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
    }

    /**
     * 填充个人信息
     */
    public void fillPersInfo() {
        //从服务端获取个人信息
        User user = UserManager.getInstance().getUserInsatance();

        userName.setText(user._username);
        realName.setText(user._name);
        email.setText(user._email);
        qq.setText(user._qq);
        //设置职业选择
        major.setText(user._major == null ? "算法工程师" : user._major);
        if (user._image!=null){
            circle_image.setImageBitmap(user._image);
        }
    }

    /**
     * 设置点击事件
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

        /** 选择职业点击 */
        major.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMutilAlertDialog();
            }
        });

        _back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * 更新用户信息
     *
     * @return
     */
    public void updateUserInfo() {
        int result = -1;
        String username = userName.getText().toString();
        String realname = realName.getText().toString();
        String jobs = major.getText().toString();
        String q = qq.getText().toString();
        try {

            UserUpdateData data = new UserUpdateData(username,realname,jobs,q);

            result = UserManager.getInstance().updateUserinfo2Server(new Gson().toJson(data));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bitmap image = null;
        if (selected){
            circle_image.setDrawingCacheEnabled(true);
            image = this.circle_image.getDrawingCache();
            UserManager.getInstance()._currentUser._image = image;
        }
        try {
            UserManager.getInstance().SetImage(this);
        } catch (IOException e) {
            showToast("提交头像失败");
        }

        if(result == 0){
            showToast("更新用户信息成功");
            UserManager.getInstance()._currentUser._username = username;
            UserManager.getInstance()._currentUser._name = realname;
            UserManager.getInstance()._currentUser._qq = q;
            UserManager.getInstance()._currentUser._major = jobs;
            if (image!=null)
                UserManager.getInstance()._currentUser._image = image;
            this.finish();
        }
    }

    /**
     * 职业选择多选框
     */
    public void showMutilAlertDialog() {
        //获取当前已选职业
        String currMajor = (String) major.getText();
        //所有职业
        final String majorArray[] = getResources().getStringArray(R.array.major_array);
        //当前已选职业bool数组，根据Strings.xml的顺序严格排列
        final boolean currChoosed[] = new boolean[majorArray.length];
        Arrays.fill(currChoosed, false);
        //按照逗号截取
        if (currMajor != null && currMajor.trim() != "") {
            String[] currMajorArray = currMajor.split("\\|");

            for (int i = 0, j = 0; i < currMajorArray.length; i++, j++) {
                if (currMajorArray[i].trim().equals(majorArray[j])) {
                    currChoosed[j] = true;
                } else {
                    i--;
                }
            }
        }

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
                major.setText(newMajor);

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
}