package com.example.mercer.bluecareer.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mercer.bluecareer.CommonUtils.ConversionTool;
import com.example.mercer.bluecareer.DataStruct.MyOccupation;
import com.example.mercer.bluecareer.MyApplication;
import com.example.mercer.bluecareer.R;

/**
 * author: Husen
 * date: 2017.11.15
 * description："我要了解"界面，"我的职业"碎片
 */
public class MyOccuFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** 创建View */
        View view = inflater.inflate(R.layout.second_fragment_my_occupation, container, false);
        //获取View中的根节点
        LinearLayout parentLayout = (LinearLayout) view.findViewById(R.id.my_occu_layout);

       //从服务器获取职业数据，并加载
        loadOccuptionFromRemote(parentLayout);

        return view;
    }

    /**
     * 从服务器获取职业数据，并加载
     * @param parentLayout
     */
    public void loadOccuptionFromRemote(LinearLayout parentLayout){
        MyOccupation myOccupation = new MyOccupation("职业一", "幼儿教育","白丁",1,5,"教育");

        parentLayout.addView(DividingLine.getDividingLine(getActivity()));
        parentLayout.addView(MyOccuptionTemplate.getOneMyOccuption(getActivity(), myOccupation));
        parentLayout.addView(DividingLine.getDividingLine(getActivity()));
        parentLayout.addView(MyOccuptionTemplate.getOneMyOccuption(getActivity(), myOccupation));
        parentLayout.addView(DividingLine.getDividingLine(getActivity()));
    }
}

/**
 * 根据模板生成一个职业
 */
class MyOccuptionTemplate {
    public static LinearLayout getOneMyOccuption(Context context, MyOccupation myOccupation) {
        /** 职业模板布局 */
        LinearLayout occuTmplLinearLayout = new LinearLayout(context);

        //职业模板线性布局 layout
        occuTmplLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ConversionTool.getPxFromDpValue(100)));
        occuTmplLinearLayout.setOrientation(LinearLayout.VERTICAL);
        occuTmplLinearLayout.setBackground(context.getDrawable(R.drawable.my_occuption_background));

        /**-------------------------我是分割线------------------------------**/

        /** 通用参数 **/
        LinearLayout.LayoutParams matchAndWrapParent = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        /**-------------------------我是分割线------------------------------**/

        /** 第一行 **/
        LinearLayout firstLayer = new LinearLayout(context);
        firstLayer.setLayoutParams(matchAndWrapParent);
        firstLayer.setOrientation(LinearLayout.HORIZONTAL);

        //第一行的TextView
        TextView occuptionNum = new TextView(context);
        occuptionNum.setText(myOccupation.get_occupationNum());
        //occuptionNum.setId();
        occuptionNum.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mLayoutParams.setMargins(ConversionTool.dp2px(20),ConversionTool.dp2px(8), 0, 0);

        // 在父类布局中添加它，及布局样式
        firstLayer.addView(occuptionNum, mLayoutParams);

        /**-------------------------我是分割线------------------------------**/

        /** 第二行 **/
        LinearLayout secondLayer = new LinearLayout(context);
        secondLayer.setLayoutParams(matchAndWrapParent);
        secondLayer.setOrientation(LinearLayout.HORIZONTAL);

        //第二层通用布局
        LinearLayout.LayoutParams seconLayorLayout;

        TextView secondLayor_1 = new TextView(context);
        TextView secondLayor_2 = new TextView(context);
        TextView secondLayor_3 = new TextView(context);
        TextView secondLayor_4 = new TextView(context);

        // 第二行第1列
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 4.0f);
        layoutParams.setMargins(ConversionTool.dp2px(35), 0, 0, 0);
        secondLayor_1.setText(myOccupation.get_occupationName());
        secondLayor_1.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
        secondLayer.addView(secondLayor_1, layoutParams);
        // 第二行第2列
        seconLayorLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 7.0f);
        secondLayor_2.setText(myOccupation.get_occupationLevel());
        secondLayor_2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        secondLayer.addView(secondLayor_2, seconLayorLayout);
        // 第二行第3列
        seconLayorLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 9.0f);
//        seconLayorLayout.weight = 3;
        secondLayor_3.setText(String.valueOf(myOccupation.get_occupation1()));
        secondLayor_3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        secondLayer.addView(secondLayor_3, seconLayorLayout);
        // 第二行第4列
        seconLayorLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 8.0f);
//        seconLayorLayout.weight = 8;
        secondLayor_4.setText("/"+String.valueOf(myOccupation.get_occupation2()));
        secondLayor_4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        secondLayer.addView(secondLayor_4, seconLayorLayout);

        /**-------------------------我是分割线------------------------------**/

        //第三层
        LinearLayout thirdLayer = new LinearLayout(context);
        thirdLayer.setLayoutParams(matchAndWrapParent);
        thirdLayer.setOrientation(LinearLayout.HORIZONTAL);

        TextView thirdLayor_1 = new TextView(context);

        LinearLayout.LayoutParams thirdLayorLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        thirdLayorLayout.setMargins(ConversionTool.dp2px(70), ConversionTool.dp2px(6), 0, ConversionTool.dp2px(6));

        thirdLayor_1.setText(myOccupation.get_occupationType());
        thirdLayor_1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);

        thirdLayer.addView(thirdLayor_1, thirdLayorLayout);

        /**-------------------------我是分割线------------------------------**/

        /** 添加到根布局*/
        occuTmplLinearLayout.addView(firstLayer);
        occuTmplLinearLayout.addView(secondLayer);
        occuTmplLinearLayout.addView(thirdLayer);

        return occuTmplLinearLayout;
    }
}

/**
 * 获得一条分割线
 */
class DividingLine {
    public static LinearLayout getDividingLine(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);

        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ConversionTool.dp2px(5)));
       /* ViewGroup.LayoutParams lp;
        lp= mLinearLayout.getLayoutParams();
        lp.width=400;
        lp.height=200;
        mLinearLayout.setLayoutParams(lp);*/
        linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorLine));

        return linearLayout;
    }
}