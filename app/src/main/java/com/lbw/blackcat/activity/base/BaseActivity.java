package com.lbw.blackcat.activity.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;


import com.lbw.blackcat.utils.AppManager;

import java.io.Serializable;

import butterknife.ButterKnife;

/**
 * Created by XMturui-js03 on 2017/9/18.
 * activity 基类
 */

public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();
    public Context instance = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateInit(savedInstanceState);
    }

    public void onCreateInit(@Nullable Bundle savedInstanceState)
    {
        setContentView(setContentViewId());
        // 添加到Activity栈中
        AppManager.getAppManager().addActivity(this);
        instance = this;
        //绑定butterKnife
        ButterKnife.bind(this);
        init(savedInstanceState);
    }


    public abstract int setContentViewId();
    public abstract void init(Bundle savedInstanceState);





    /**
     * 自定义返回键的效
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) { // 返回键
            baseFinish();
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            return false;
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            return false;
        }

        return false;
    }

    /**
     * 销毁本类
     */
    public void baseFinish() {
        AppManager.getAppManager().finishActivity();
        // 左进 右退
        // overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }
    /**
     * 销毁指定类
     */
    public void baseFinishofActivity(Class<?> cls) {
        AppManager.getAppManager().finishActivity(cls);
    }

    /**
     * 销毁所有activity
     */
    public void finishAllActivity() {
        AppManager.getAppManager().finishAllActivity();
    }

    /**
     * 启动意图跳转
     */
    public void baseStartActivity(Context context, Class cls) {
        Intent intent = new Intent(context, cls);
        this.startActivity(intent);
    }



    /**
     * 是否兼容
     * @param apiLevel 兼容的apk
     * @return true:是 false:否
     */
    protected boolean isCompatible(int apiLevel) {
        return Build.VERSION.SDK_INT >= apiLevel;
    }

    /**
     * 简化findViewByID
     * @param resId
     * @param <T>
     * @return
     */
    protected <T extends View> T findView(int resId) {
        return (T) (findViewById(resId));
    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lbw", "onDestroy");
    }




    /**
     * 启动意图跳转并传值
     *
     */
    public void baseStartActivity(Context context, Class cls, String[] key,
                                  String[] value) {
        Intent intent = new Intent(context, cls);
        for (int j = 0; j < key.length; j++) {
            intent.putExtra(key[j], value[j]);
        }
        this.startActivity(intent);
        // 右进 左退
        // overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    /**
     * 启动意图跳转并传对象
     *
     */
    private final static String KEY = "OBJ";

    public void baseStartActivity(Context context, Class cls, Serializable value) {
        Intent intent = new Intent(context, cls);
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY, (Serializable) value);
        intent.putExtra(KEY, bundle);

        this.startActivity(intent);
        // 右进 左退
        // overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    /**
     * 获取intent传递过来的参数
     */
    public String baseGetIntent(String key) {
        Intent intent = getIntent();
        return intent.getStringExtra(key);
    }

    /**
     * 获取intent传递过来的对象
     */
    public Serializable baseGetIntent() {
        Intent intent = getIntent();
        if (null == intent.getBundleExtra(KEY)) {
            return null;
        }
        return (Serializable) intent.getBundleExtra(KEY).getSerializable(KEY);
    }

}
