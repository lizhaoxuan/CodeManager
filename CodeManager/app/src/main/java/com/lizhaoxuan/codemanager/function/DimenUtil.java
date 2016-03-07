package com.lizhaoxuan.codemanager.function;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

public class DimenUtil {

    private static final float PADDING = 0.5f;

    private DimenUtil() {
    }

    /**
     * dip转px
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + PADDING);
    }

    public static int sp2px(Context context, float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    /**
     * px转dip
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + PADDING);
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenWidthMinusOf(Context context, int dpValue) {
        return getScreenWidth(context) - dip2px(context, dpValue);
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getScreenHeightMinusOf(Context context, int dpValue) {
        return getScreenHeight(context) - dip2px(context, dpValue);
    }

    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resId > 0 ? resources.getDimensionPixelSize(resId) : 0;
    }

    public static int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        return resId > 0 ? resources.getDimensionPixelSize(resId) : 0;
    }

}
