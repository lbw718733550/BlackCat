package com.lbw.blackcat.activity.base;

import java.io.Serializable;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.lbw.blackcat.utils.AppManager;

import butterknife.ButterKnife;


public abstract class BaseFragment extends Fragment{

	public Context instance = null;
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return onCreateInit(inflater,container,savedInstanceState);
	}
	
	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

	public View onCreateInit(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState)
	{
		view = inflater.inflate(setContentViewId(), container, false);
		instance = getActivity();
		//绑定butterKnife
		ButterKnife.bind(this, view);
		init(inflater,container,savedInstanceState);
		return view;
	}


	public abstract int setContentViewId();
	public abstract void init(LayoutInflater inflater, ViewGroup container,
							  Bundle savedInstanceState);


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
		return (T) (view.findViewById(resId));
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


}
