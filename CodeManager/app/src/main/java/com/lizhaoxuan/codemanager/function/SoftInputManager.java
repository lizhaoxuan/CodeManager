package com.lizhaoxuan.codemanager.function;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

public class SoftInputManager implements OnGlobalLayoutListener {
    private static final int SHOW_DALAY = 200;
    private static final int MIN_DELTA = 100;
    private InputMethodManager imm;
    private SoftInputManager.SoftInputListener softInputListener;
    private Window window;

    private SoftInputManager(Context context) {
        this.imm = (InputMethodManager) context.getSystemService("input_method");
    }

    public static SoftInputManager getInstance(Context context) {
        return new SoftInputManager(context);
    }

    public static void showSoftInput(Context context, View focusedView) {
        if (context != null) {
            getInstance(context).showSoftInput(focusedView);
        }
    }

    public static void hideSoftInput(Context context, View focusedView) {
        if (context != null) {
            getInstance(context).hideSoftInput(focusedView);
        }
    }

    public static void hideSoftInput(Context context, IBinder viewWindowToken) {
        if (context != null) {
            getInstance(context).hideSoftInput(viewWindowToken);
        }
    }

    public static void hideSoftInput(Activity activity) {
        if (activity != null) {
            getInstance(activity).hideSoftInput(activity.getWindow().getDecorView().getWindowToken());
        }
    }

    public static void hideSoftInput(Window window) {
        if (window != null) {
            getInstance(window.getContext()).hideSoftInput(window.getDecorView().getWindowToken());
        }
    }

    public void setSoftInputListener(Window window, SoftInputManager.SoftInputListener softInputListener) {
        if (window != null && softInputListener != null) {
            this.window = window;
            this.softInputListener = softInputListener;
            window.getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(this);
        }

    }

    public void onGlobalLayout() {
        if (this.window != null && this.softInputListener != null) {
            Rect rectgle = new Rect();
            View decorView = this.window.getDecorView();
            decorView.getWindowVisibleDisplayFrame(rectgle);
            int heightDiff = decorView.getRootView().getHeight() - rectgle.bottom;
            if (heightDiff > 100) {
                this.softInputListener.onShow();
            }

            if (heightDiff == 0) {
                this.softInputListener.onHide();
            }
        }

    }

    public void removeSoftInputListener() {
        if (this.window != null) {
            View decorView = this.window.getDecorView();
            if (VERSION.SDK_INT < 16) {
                decorView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            } else {
                decorView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        }

    }

    public void showSoftInput(final View focusedView) {
        if (focusedView != null) {
            focusedView.postDelayed(new Runnable() {
                public void run() {
                    focusedView.requestFocus();
                    SoftInputManager.this.imm.showSoftInput(focusedView, 1);
                }
            }, 200L);
        }
    }

    public void hideSoftInput(View focusedView) {
        if (focusedView != null) {
            focusedView.clearFocus();
            this.hideSoftInput(focusedView.getWindowToken());
        }
    }

    public void hideSoftInput(IBinder viewWindowToken) {
        this.imm.hideSoftInputFromWindow(viewWindowToken, 2);
    }

    public interface SoftInputListener {
        void onShow();

        void onHide();
    }
}
