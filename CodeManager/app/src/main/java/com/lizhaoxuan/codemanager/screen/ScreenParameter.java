package com.lizhaoxuan.codemanager.screen;

import android.app.Activity;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

/**
 * 屏幕各参数计算
 * Created by lizhaoxuan on 16/2/20.
 */
public class ScreenParameter {
    //屏幕宽度
    int screenWidth;
    //屏幕高度
    int screenHeight;
    //状态栏高度
    int stateHeight;
    //标题栏高度
    int titleHeight;

    public void screenParameter(Activity activity){
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        screenWidth = metric.widthPixels;
        screenHeight = metric.heightPixels;

        Rect outRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
        stateHeight = outRect.top;

        TypedValue tv = new TypedValue();
        if (activity.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
            titleHeight =outRect.top + TypedValue.complexToDimensionPixelSize(tv.data, activity.getResources().getDisplayMetrics());
        }
    }

    public void viewParameter(View view){
        
    }

}
