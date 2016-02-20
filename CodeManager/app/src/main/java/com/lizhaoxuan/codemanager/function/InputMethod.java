package com.lizhaoxuan.codemanager.function;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 输入法相关
 * Created by lizhaoxuan on 16/2/20.
 */
public class InputMethod {

    private int viewHeight = 0;

    /**
     * 软键盘的弹出和隐藏监听方法。（原理：通过监听布局变化判断软键盘是否弹出）
     * 通过监听键盘弹出和隐藏 从而改变布局效果
     * 纯View内部逻辑改变，且需求随时可能变化，不建议放入model层
     */
    public void inputMethodEvent(final View view) {
        //输入法是否打开的差值，不同情况需重新计算
        final int D_VALUE = 150;
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //初次情况，通常为View初次加载，或计算到logo布局的最大高度
                if (view.getHeight() > viewHeight) {
                    viewHeight = view.getHeight();
                    //View 初次加载，做View初次默认显示操作
                    //...

                } else {

                    //当logo布局的最大高度与最小高度只差超过150时，通常为软键盘弹出
                    //大分辨率手机没有问题，可能有误差的是小分辨率手机，以测公司最小测试机--HTC
                    if (viewHeight - view.getHeight() > D_VALUE) {
                        Log.v("LoginActivity", "open keyboard");

                    } else {
                        Log.v("LoginActivity", "close keyboard");

                    }
                }
            }
        });
    }

    /**
     * 动态隐藏软键盘
     */
    public static void hideSoftInput(Activity activity) {
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void hideSoftInput(Context context, EditText edit) {
        edit.clearFocus();
        InputMethodManager inputmanger = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputmanger.hideSoftInputFromWindow(edit.getWindowToken(), 0);
    }

    /**
     * 动态显示软键盘
     */
    public static void showSoftInput(Context context, EditText edit) {
        edit.setFocusable(true);
        edit.setFocusableInTouchMode(true);
        edit.requestFocus();
        InputMethodManager inputManager = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(edit, 0);
    }

    /**
     * 动态显示或者是隐藏软键盘
     */
    public static void toggleSoftInput(Context context, EditText edit) {
        edit.setFocusable(true);
        edit.setFocusableInTouchMode(true);
        edit.requestFocus();
        InputMethodManager inputManager = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }
}
