package com.lbw.blackcat.activity.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lbw.blackcat.R;
import com.lbw.blackcat.activity.base.BaseActivity;
import com.lbw.blackcat.live.IliveManage;
import com.lbw.blackcat.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @autor lbw
 * @time 2018/1/29 10:07
 * @desc 注册
 */

public class RegisterActivity extends BaseActivity {


    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_password2)
    EditText etPassword2;
    @BindView(R.id.regist)
    Button regist;

    @Override
    public int setContentViewId() {
        return R.layout.cat_activity_register;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        etUsername.setText("lbw5");
        etPassword.setText("cx15259851275");
        etPassword2.setText("cx15259851275");
    }


    @OnClick({R.id.regist, R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.regist:
                if (examine()) {
                    IliveManage.getInstance().regist(etUsername.getText().toString(), etPassword.getText().toString(), new IliveManage.CallBack() {
                        @Override
                        public void onError(String module, int errCode, String errMsg) {
                            ToastUtil.showError(instance, errMsg);
                        }
                        @Override
                        public void onSuccess(Object data) {
                            ToastUtil.showSuccess(instance,"注册成功！");
                            Login();
                        }
                    });
                }
                break;
            case R.id.back:
                baseFinish();
                break;
        }
    }

    /**
     * @desc 注册完后调用登录
     * @another lbw
     * @time 2018/1/30 11:06
     */
    private void Login() {
        IliveManage.getInstance().Login(etUsername.getText().toString(), etPassword.getText().toString(), new IliveManage.CallBack() {
            @Override
            public void onError(String module, int errCode, String errMsg) {
                baseStartActivity(instance,LoginActivity.class);
                baseFinish();
            }
            @Override
            public void onSuccess(Object data) {
                baseStartActivity(instance,EditUserInfoActivity.class);
                baseFinish();
            }
        });
    }

    /**
     * @desc 检测账号密码输入
     * @another lbw
     * @time 2018/1/29 10:32
     */
    private boolean examine() {
        if (TextUtils.isEmpty(etUsername.getText().toString()) ||
                TextUtils.isEmpty(etPassword.getText().toString()) ||
                TextUtils.isEmpty(etPassword2.getText().toString())){
            ToastUtil.showError(instance,"账号密码不能为空！");
            return false;
        }
        if(!etPassword.getText().toString().equals(etPassword2.getText().toString())) {
            ToastUtil.showError(instance,"两次输入的密码不一致");
            return false;
        }
        return true;
    }
}
