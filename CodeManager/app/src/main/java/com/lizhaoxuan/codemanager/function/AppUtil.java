package com.lizhaoxuan.codemanager.function;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.KeyguardManager;
import android.app.Service;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

import java.lang.reflect.Field;
import java.util.List;

public class AppUtil {

    public static final String SP_KEY_APPSTARTTIME = "SP_KEY_APPSTARTTIME";
    public static final String SP_KEY_APPINSTALLTIME = "SP_KEY_APPINSTALLTIME";
    public static final String SP_KEY_APPVERSIONSTARTTIME = "SP_KEY_APPVERSIONSTARTTIME";
    public static final String SP_KEY_APPVERSIONCODE = "SP_KEY_APPVERSIONCODE";

    private AppUtil() {
    }

    /**
     * 获得软件版本
     *
     * @param con
     * @return
     */
    public static String getVersionName(final Context con) {
        String version = "0.0.0";
        PackageManager packageManager = con.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(con.getPackageName(), 0);
            version = packageInfo.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    public static Bundle getMetaData(final Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            ApplicationInfo packageInfo = packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            return packageInfo.metaData;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得软件版本
     *
     * @param con
     * @return
     */
    public static int getVersionCode(final Context con) {
        int version = 1;

        PackageManager packageManager = con.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(con.getPackageName(), 0);
            version = packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 手机系统信息
     *
     * @return
     */
    public static String getSystemInfo() {
        return android.os.Build.MODEL + " " + android.os.Build.VERSION.RELEASE;
    }

    @SuppressWarnings("unchecked")
    public synchronized static Class<? extends Activity> getCurrentTopActivityClass(Context con) {
        ActivityManager mActivityManager = (ActivityManager) con.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> RunningTask = mActivityManager.getRunningTasks(1);
        ActivityManager.RunningTaskInfo ar = RunningTask.get(0);
        String className = ar.topActivity.getClassName();
        Class<? extends Activity> clazz = null;
        try {
            clazz = (Class<? extends Activity>) Class.forName(className);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clazz;
    }

    public static synchronized boolean isBackgroundRunning(Context con) {

        ActivityManager activityManager = (ActivityManager) con.getSystemService(Service.ACTIVITY_SERVICE);
        KeyguardManager keyguardManager = (KeyguardManager) con.getSystemService(Service.KEYGUARD_SERVICE);

        if (activityManager == null)
            return false;
        List<RunningAppProcessInfo> processList = activityManager.getRunningAppProcesses();
        for (RunningAppProcessInfo process : processList) {

            if (process.processName.equals(con.getPackageName())) {
                boolean isBackground = process.importance != RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                        && process.importance != RunningAppProcessInfo.IMPORTANCE_VISIBLE;
                boolean isLockedState = keyguardManager.inKeyguardRestrictedInputMode();
                if (isBackground || isLockedState) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public static synchronized boolean isForegroundRunning(Context con) {
        return !isBackgroundRunning(con);
    }

    /**
     * 记录App启动时间 和 安装时间
     * 请在App启动Activity时，执行此方法
     *
     * @return
     */
    public static void appStart(Context context) {
        long stamp = System.currentTimeMillis();
        SharedPreUtil.set(SP_KEY_APPSTARTTIME, stamp, context);
        if (getAppInstalledTime(context) == -1) {
            SharedPreUtil.set(SP_KEY_APPINSTALLTIME, stamp, context);
        }
        if ((getCurrentVersonStartedTime(context) == -1)
                || (getVersionCode(context) != SharedPreUtil.getInt(SP_KEY_APPVERSIONCODE, context, -1))) {
            SharedPreUtil.set(SP_KEY_APPVERSIONSTARTTIME, stamp, context);
            SharedPreUtil.set(SP_KEY_APPVERSIONCODE, getVersionCode(context), context);
        }
    }

    /**
     * 获取App启动时间（上一次打开时间）
     *
     * @return 时间戳
     */
    public static long getLastStartedTime(Context context) {
        return SharedPreUtil.getLong(SP_KEY_APPSTARTTIME, context, -1);
    }

    /**
     * 获取App安装时间（第一次打开时间）
     *
     * @return 时间戳
     */
    public static long getAppInstalledTime(Context context) {
        return SharedPreUtil.getLong(SP_KEY_APPINSTALLTIME, context, -1);
    }

    /**
     * 获取当前版本第一次启动时间
     *
     * @param context
     * @return 时间戳
     */
    public static long getCurrentVersonStartedTime(Context context) {
        return SharedPreUtil.getLong(SP_KEY_APPVERSIONSTARTTIME, context, -1);
    }

    /**
     * 检测目前版本是否可debug
     *
     * @param context
     * @return
     */
    public static boolean isDebug(Context context) {
        Object obj = getBuildConfigValue(context, "DEBUG");
        return obj != null && (boolean) obj;
    }

    /**
     * Gets a field from the project's BuildConfig. This is useful when, for example, flavors
     * are used at the project level to set custom fields.
     *
     * @param context   Used to find the correct file
     * @param fieldName The name of the field-to-access
     * @return The value of the field, or {@code null} if the field is not found.
     */
    public static Object getBuildConfigValue(Context context, String fieldName) {
        try {
            Class<?> clazz = Class.forName(context.getPackageName() + ".BuildConfig");
            Field field = clazz.getField(fieldName);
            return field.get(null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
