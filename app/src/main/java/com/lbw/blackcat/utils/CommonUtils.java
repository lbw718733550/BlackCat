package com.lbw.blackcat.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.Window;
import android.view.WindowManager;

import com.lbw.blackcat.BlackCatApp;

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

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取屏幕宽高
     *
     * @return
     */
    public static Point getScreenMetrics() {
        WindowManager wm = (WindowManager) BlackCatApp.getInstance().getSystemService(Context.WINDOW_SERVICE);
        int screenWidth = wm.getDefaultDisplay().getWidth();
        int screenHeight = wm.getDefaultDisplay().getHeight();
        return new Point(screenWidth, screenHeight);
    }
}
