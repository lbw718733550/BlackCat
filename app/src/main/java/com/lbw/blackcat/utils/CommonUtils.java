package com.lbw.blackcat.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

/**
 * @autor lbw
 * @time 2018/1/26 16:24
 * @desc
 */

public class CommonUtils {

    /**
     * @desc 隐藏状态栏，标题栏 ，全屏
     * @another lbw
     * @time 2018/1/26 16:25
     */
    public static void hiddenState(Activity activity){
        Window window = activity.getWindow();
        //隐藏标题栏
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
    }
}
