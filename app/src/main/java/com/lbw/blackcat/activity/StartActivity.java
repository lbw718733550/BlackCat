package com.lbw.blackcat.activity;

import android.os.Bundle;
import android.os.Handler;

import com.lbw.blackcat.R;
import com.lbw.blackcat.activity.base.BaseActivity;
import com.lbw.blackcat.activity.user.LoginActivity;

/**
 * @autor lbw
 * @time 2018/1/25 16:22
 * @desc 启动页
 */

public class StartActivity extends BaseActivity{

    @Override
    public int setContentViewId() {
        return R.layout.cat_activity_start;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                baseStartActivity(instance,LoginActivity.class);
                baseFinish();
            }
        },2000);
    }
}
