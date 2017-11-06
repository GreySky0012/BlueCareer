package com.example.mercer.bluecareer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.net.Uri;

import java.io.File;

/**
 * Created by 53017_000 on 2017/3/18.
 */
public class ImageChooser extends CircleImageView{
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;

    public ImageChooser(Context context) {
        super(context);
        final ImageLoadActivity c = (ImageLoadActivity)context;

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                c.showChoosePicDialog();
            }
        });
    }

    ImageChooser(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        final ImageLoadActivity c = (ImageLoadActivity)context;

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                c.showChoosePicDialog();
            }
        });
    }

    ImageChooser(Context context,AttributeSet attributeSet,int defStyle){
        super(context,attributeSet,defStyle);
        final ImageLoadActivity c = (ImageLoadActivity)context;

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                c.showChoosePicDialog();
            }
        });
    }
}
