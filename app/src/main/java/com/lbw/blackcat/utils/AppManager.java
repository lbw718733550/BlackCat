package com.lbw.blackcat.utils;


import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity管理类，用于管理Activity
 */
public class AppManager {

    private static List<Activity> activityStack;
    private static AppManager instance;

    private AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            synchronized (AppManager.class) {
                instance = new AppManager();
            }
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new ArrayList<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity
     */
    public Activity currentActivity() {
        Activity activity = activityStack.get(activityStack.size() - 1);
        return activity;
    }

    /**
     * 结束当前Activity
     */
    public void finishActivity() {
        Activity activity = activityStack.get(activityStack.size() - 1);
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        List<Activity> activityStacks = activityStack;
        for (Activity activity : activityStacks) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }

    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        if (activityStack == null)// 防止为空的情况
            return;
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();

            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            // restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }
}