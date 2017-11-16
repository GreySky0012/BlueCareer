package com.example.mercer.bluecareer.CommonUtils;

import android.util.TypedValue;

import com.example.mercer.bluecareer.MyApplication;

/**
 * author: Husen
 * date: 2017.11.16
 * description：通用专用工具类
 */

public class ConversionTool {
    private static final float _dpScale = MyApplication.getContext().getResources().getDisplayMetrics().density;
    private static final float _scaledDensity = MyApplication.getContext().getResources().getDisplayMetrics().scaledDensity;

    public static float getPxFromSpValue(int spWantValue){
        float spValue = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spWantValue,MyApplication.getContext().getResources().getDisplayMetrics());

        return spValue;
    }

    public static int getPxFromDpValue(int dpWantValue){
        float dpValue = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpWantValue,MyApplication.getContext().getResources().getDisplayMetrics());

        return (int) dpValue;
    }

    public enum TextTypeEnum{
        CHINESE, NUMBER_OR_CHARACTER, OTHER
    }

    /**
     * dp转成px
     * @param dpValue
     * @return
     */
    public static int dp2px(float dpValue) {
        return (int) (dpValue * _dpScale + 0.5f);
    }

    /**
     * px转成dp
     * @param pxValue
     * @return
     */
    public static int px2dp(float pxValue) {
        return (int) (pxValue / _dpScale + 0.5f);
    }

    /**
     * sp转成px
     * @param spValue
     * @param textType
     * @return
     */
    public static float sp2px(float spValue, TextTypeEnum textType) {
        switch (textType) {
            case CHINESE:
                return spValue * _scaledDensity;
            case NUMBER_OR_CHARACTER:
                return spValue * _scaledDensity * 10.0f / 18.0f;
            default:
                return spValue * _scaledDensity;
        }
    }

    /**
     * px转成sp
     * @param pxValue
     * @param textType
     * @return
     */
    public static float px2sp(float pxValue, TextTypeEnum textType) {
        switch (textType) {
            case CHINESE:
                return pxValue / _scaledDensity;
            case NUMBER_OR_CHARACTER:
                return pxValue / _scaledDensity / 10.0f * 18.0f;
            default:
                return pxValue / _scaledDensity;
        }
    }
}