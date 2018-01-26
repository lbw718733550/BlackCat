package com.lbw.blackcat.activity.base.user;

import android.os.Bundle;
import android.support.constraint.Guideline;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lbw.blackcat.R;
import com.lbw.blackcat.activity.MainActivity;
import com.lbw.blackcat.activity.base.BaseActivity;
import com.lbw.blackcat.utils.CommonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @autor lbw
 * @time 2018/1/26 14:01
 * @desc 登陆
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.guideline3)
    Guideline guideline3;
    @BindView(R.id.guideline4)
    Guideline guideline4;

    @Override
    public int setContentViewId() {
        CommonUtils.hiddenState(this);
        return R.layout.cat_activity_login;
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }


    @OnClick({R.id.login, R.id.regist, R.id.forget_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                if(examine()){
                    baseStartActivity(instance, MainActivity.class);
                }
                break;
            case R.id.regist:
                break;
            case R.id.forget_pwd:
                break;
        }
    }

    private boolean examine() {
        if (TextUtils.isEmpty(etUsername.getText().toString()) ||
                TextUtils.isEmpty(etPassword.getText().toString()))
            return false;
        return true;
    }
}
