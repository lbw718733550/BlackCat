package com.lbw.blackcat.widget;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * @autor lbw
 * @time 2018/2/2 11:01
 * @desc 继承自带的ActionBarDrawerToggle，实现主页面随着 抽屉拉开而移动
 */

public class MyActionBarDrawerToggle extends ActionBarDrawerToggle {

    private onDrawerListener onDragListener;

    public MyActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, int openDrawerContentDescRes, int closeDrawerContentDescRes) {
        super(activity, drawerLayout, openDrawerContentDescRes, closeDrawerContentDescRes);
    }

    public MyActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int openDrawerContentDescRes, int closeDrawerContentDescRes) {
        super(activity, drawerLayout, toolbar, openDrawerContentDescRes, closeDrawerContentDescRes);
    }

    public void setOnDragListener(onDrawerListener onDragListener) {
        this.onDragListener = onDragListener;
    }


    /**
     * @desc 重写onDrawerSlide，回调滑动改变
     * @another lbw
     * @time 2018/2/2 11:19
     */
    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        super.onDrawerSlide(drawerView, slideOffset);
        if (null != onDragListener) {
            onDragListener.onDrawerSlide(drawerView, slideOffset);
        }
    }

    public interface onDrawerListener{
        void onDrawerSlide(View drawerView, float slideOffset);
    }
}
