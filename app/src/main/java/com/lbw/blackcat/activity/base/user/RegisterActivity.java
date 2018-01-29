package com.lbw.blackcat.activity.base.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lbw.blackcat.R;
import com.lbw.blackcat.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    }


    @OnClick({R.id.regist, R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.regist:
                if (examine()) {
                    baseStartActivity(instance,EditUserInfoActivity.class);
                    baseFinish();
                }
                break;
            case R.id.back:
                baseFinish();
                break;
        }
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
            return true;
        }
        if(!etPassword.getText().toString().equals(etPassword2.getText().toString())) {
            return true;
        }
        return true;
    }
}
