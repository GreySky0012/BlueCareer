package com.example.mercer.bluecareer.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

/**
 * Created by GreySky on 2017/11/17.
 */
public class TopicDialog {

    NiftyDialogBuilder _builder;

    public TopicDialog(Context cnt,String title,String content)
    {
        _builder = NiftyDialogBuilder.getInstance(cnt);
        _builder
                .withTitle(title)                                  //.withTitle(null)  no title
                .withTitleColor("#FFFFFF")                                  //def
                .withDividerColor("#11000000")                              //def
                .withMessage(content)                     //.withMessage(null)  no Msg
                .withMessageColor("#FFFFFF")                              //def  | withMessageColor(int resid)
                .withDialogColor("#D3D3D3")                               //def  | withDialogColor(int resid)                               //def
                //.withIcon(getResources().getDrawable(R.drawable.icon))
                .isCancelableOnTouchOutside(false)                           //def    | isCancelable(true)
                .withDuration(700)                                          //def
                .withEffect(Effectstype.SlideBottom)                                         //def Effectstype.Slidetop
                .withButton1Text("关闭")
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        _builder.dismiss();
                    }
                }).show();
    }
}