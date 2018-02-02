package com.lbw.blackcat.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

/**
 * Created by linbowen on 2017/10/11.
 * toast工具类
 */

public class ToastUtil {

    private static Toast mToast;
    private static Handler mHandler = new Handler();
    public static int time = 3000;
    private static Runnable r = new Runnable() {
        public void run() {
            mToast.cancel();
        }
    };

    public static void showToast(Context mContext, String text, int duration) {

        mHandler.removeCallbacks(r);
        if (mToast != null)
            mToast.setText(text);
        else
            mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
        mHandler.postDelayed(r, duration);

        mToast.show();
    }
    public static void showError(Context mContext, String text) {
       Toasty.error(mContext, text, time, true).show();
    }

    public static void showSuccess(Context mContext, String text)
    {
        Toasty.success(mContext, text, time, true).show();
    }


    public static void showToast(Context mContext, int resId, int duration) {
        showToast(mContext, mContext.getResources().getString(resId), duration);
    }

}
