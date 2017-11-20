package com.example.mercer.bluecareer.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mercer.bluecareer.R;

/**
 * author: Husen
 * date: 2017.11.20
 * description：职业信息显示的对话框
 */

public class OccupationDialog {
    public static void showCustomizeDialog(final Context context, View view) {
        AlertDialog.Builder customizeDialog = new AlertDialog.Builder(context);
        //customizeDialog.setTitle("我是一个自定义Dialog");
        customizeDialog.setView(view);
        customizeDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        customizeDialog.show();
    }
}