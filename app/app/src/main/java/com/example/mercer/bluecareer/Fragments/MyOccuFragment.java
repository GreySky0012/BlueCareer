package com.example.mercer.bluecareer.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mercer.bluecareer.R;

/**
 * author: Husen
 * date: 2017.11.15
 * description：
 */
public class MyOccuFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment_my_occupation, container, false);

        LinearLayout parentLayout = (LinearLayout) view.findViewById(R.id.my_occu_layout);
        MyOccuptionTemplate childLayout = new MyOccuptionTemplate(getActivity());
        MyOccuptionTemplate childLayout1 = new MyOccuptionTemplate(getActivity());

        parentLayout.addView(DividingLine.getDividingLine(getActivity()));
        parentLayout.addView(childLayout.getLinearLayout());
        parentLayout.addView(DividingLine.getDividingLine(getActivity()));
        parentLayout.addView(childLayout1.getLinearLayout());
        parentLayout.addView(DividingLine.getDividingLine(getActivity()));

        return view;
    }
}

class MyOccuptionTemplate {
    LinearLayout linearLayout;

    public MyOccuptionTemplate(Context context) {
        linearLayout = new LinearLayout(context);
        /** layout 布局 */
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 300));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackground(context.getDrawable(R.drawable.my_occuption_background));

        /*********************************/
        /** 第一层 **/
        LinearLayout firstLayer = new LinearLayout(context);
        firstLayer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        firstLayer.setOrientation(LinearLayout.HORIZONTAL);

        //第一层的TextView
        TextView occuptionNum = new TextView(context);
        occuptionNum.setText("职业一");
        //occuptionNum.setId();
        occuptionNum.setTextSize(20);

        LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mLayoutParams.setMargins(20, 8, 0, 0);

        // 在父类布局中添加它，及布局样式
        firstLayer.addView(occuptionNum, mLayoutParams);

        /** 添加到根布局*/
        linearLayout.addView(firstLayer);

        /*******************/
        LinearLayout secondLayer = new LinearLayout(context);
        secondLayer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        secondLayer.setOrientation(LinearLayout.HORIZONTAL);

        //第二层通用布局
        LinearLayout.LayoutParams seconLayorLayout = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        TextView secondLayor_1 = new TextView(context);
        TextView secondLayor_2 = new TextView(context);
        TextView secondLayor_3 = new TextView(context);
        TextView secondLayor_4 = new TextView(context);

        seconLayorLayout.weight = 7;
        secondLayor_2.setText("白丁");
        secondLayor_2.setTextSize(20);
        secondLayer.addView(secondLayor_2);

        seconLayorLayout.weight = 9;
        secondLayor_3.setText("1");
        secondLayor_3.setTextSize(25);
        secondLayer.addView(secondLayor_3);

        seconLayorLayout.weight = 8;
        secondLayor_4.setText("/5");
        secondLayor_4.setTextSize(20);
        secondLayer.addView(secondLayor_4);

        seconLayorLayout.weight = 4;
        seconLayorLayout.setMargins(35, 0, 0, 0);
        secondLayor_1.setText("幼儿教育");
        secondLayor_1.setTextSize(25);
        secondLayer.addView(secondLayor_1);

        /** 添加到根布局*/
        linearLayout.addView(secondLayer);

        /******************************/
        //第三层
        LinearLayout thirdLayer = new LinearLayout(context);
        thirdLayer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        thirdLayer.setOrientation(LinearLayout.HORIZONTAL);

        TextView thirdLayor_1 = new TextView(context);

        LinearLayout.LayoutParams thirdLayorLayout = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        thirdLayorLayout.setMargins(70, 6, 0, 6);

        thirdLayor_1.setText("教育");
        thirdLayor_1.setTextSize(16);

        thirdLayer.addView(thirdLayor_1);

        /** 添加到根布局*/
        linearLayout.addView(thirdLayer);
    }

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }
}

class DividingLine {
    public static LinearLayout getDividingLine(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);

        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 5));
       /* ViewGroup.LayoutParams lp;
        lp= mLinearLayout.getLayoutParams();
        lp.width=400;
        lp.height=200;
        mLinearLayout.setLayoutParams(lp);*/
        linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorLine));

        return linearLayout;
    }
}
