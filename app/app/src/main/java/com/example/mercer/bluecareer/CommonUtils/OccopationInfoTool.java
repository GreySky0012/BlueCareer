package com.example.mercer.bluecareer.CommonUtils;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.mercer.bluecareer.R;

/**
 * author: Husen
 * date: 2017.11.20
 * description：
 */

public class OccopationInfoTool {
    /**
     * 获取一个 工作点 TextView
     * @param context
     * @return
     */
    public static TextView tableHeadTextView(Context context){
        TextView textView = new TextView(context);

        textView.setBackgroundColor(context.getColor(R.color.occupationShow));
        int dp3 = ConversionTool.dp2px(3);
        textView.setPadding(dp3, dp3, dp3, dp3);
        textView.setGravity(Gravity.CENTER);

        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams();
        layoutParams.setMargins(ConversionTool.dp2px(40), 0, 0, 0);
        textView.setLayoutParams(layoutParams); //别忘了把参数设置回去

        return textView;
    }

    /**
     * 获取一个 薪水TextView
     * @param context
     * @return
     */
    public static TextView tableNameTextView(Context context){
        TextView textView = new TextView(context);

        textView.setBackgroundColor(context.getColor(R.color.occupationShow));
        int dp3 = ConversionTool.dp2px(3);
        textView.setPadding(dp3, dp3, dp3, dp3);
        textView.setGravity(Gravity.CENTER);

        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams();
        layoutParams.setMargins(0, 0, ConversionTool.dp2px(40),0);
        layoutParams.span = 2;//TableRow.
        textView.setLayoutParams(layoutParams); //别忘了把参数设置回去

        return textView;
    }
}
