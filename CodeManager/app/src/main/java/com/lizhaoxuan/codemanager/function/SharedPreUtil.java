package com.lizhaoxuan.codemanager.function;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * 系统临时保存辅助
 */
public class SharedPreUtil {
    private static final String KEY = "SharedPreUtil";
    private static SharedPreferences sharedPreferences;

    private static void getSharedPreferences(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(KEY, Activity.MODE_PRIVATE);
        }
    }

    public static boolean set(String name, boolean flag, Context context) {
        getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(name, flag);
        return editor.commit();
    }

    public static boolean getBoolean(String name, Context context) {
        getSharedPreferences(context);
        return sharedPreferences.getBoolean(name, false);
    }

    public static boolean set(String name, String flag, Context context) {
        getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(name, flag);
        return editor.commit();
    }

    public static String getString(String name, Context context) {
        getSharedPreferences(context);
        return sharedPreferences.getString(name, "");
    }

    public static boolean set(String name, int value, Context context) {
        getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(name, value);
        return editor.commit();
    }

    public static int getInt(String name, Context context, int defaultValue) {
        getSharedPreferences(context);
        return sharedPreferences.getInt(name, defaultValue);
    }

    public static boolean set(String name, long value, Context context) {
        getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(name, value);
        return editor.commit();
    }

    public static long getLong(String name, Context context, long defaultValue) {
        getSharedPreferences(context);
        return sharedPreferences.getLong(name, defaultValue);
    }
}
