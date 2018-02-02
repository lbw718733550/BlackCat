package com.lbw.blackcat.activity.user;

import android.os.Bundle;
import android.support.constraint.Guideline;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lbw.blackcat.R;
import com.lbw.blackcat.activity.MainActivity;
import com.lbw.blackcat.activity.base.BaseActivity;
import com.lbw.blackcat.live.IliveManage;
import com.lbw.blackcat.utils.CommonUtils;
import com.lbw.blackcat.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

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
        etUsername.setText("lbw6");
        etPassword.setText("cx15259851275");
    }


    @OnClick({R.id.login, R.id.regist, R.id.forget_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                if(examine()){
                    IliveManage.getInstance().Login(etUsername.getText().toString(), etPassword.getText().toString(), new IliveManage.CallBack() {
                        @Override
                        public void onError(String module, int errCode, String errMsg) {
                            ToastUtil.showError(LoginActivity.this, "登录失败！");
                        }
                        @Override
                        public void onSuccess(Object data) {
                            ToastUtil.showSuccess(LoginActivity.this, "登录成功！");
                            baseStartActivity(instance, MainActivity.class);
                            baseFinish();
                        }
                    });
                }
                break;
            case R.id.regist:
                baseStartActivity(instance, RegisterActivity.class);
                break;
            case R.id.forget_pwd:
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
                TextUtils.isEmpty(etPassword.getText().toString()))
            return false;
        return true;
    }
}
