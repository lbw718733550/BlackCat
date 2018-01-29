package com.lbw.blackcat.activity.base.user;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.lbw.blackcat.R;
import com.lbw.blackcat.activity.MainActivity;
import com.lbw.blackcat.activity.base.BaseActivity;
import com.lbw.blackcat.widget.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @autor lbw
 * @time 2018/1/29 15:27
 * @desc 配置个人信息
 */

public class EditUserInfoActivity extends BaseActivity {


    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.et_nickname)
    EditText etNickname;
    @BindView(R.id.radio_girl)
    RadioButton radioGirl;
    @BindView(R.id.radio_boy)
    RadioButton radioBoy;

    @Override
    public int setContentViewId() {
        return R.layout.cat_activity_edituserinfo;
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }


    @OnClick({R.id.iv_head, R.id.radio_girl, R.id.radio_boy, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                break;
            case R.id.radio_girl:
                if (radioBoy.isChecked()) {
                    radioBoy.setChecked(false);
                }
                radioGirl.setChecked(true);
                break;
            case R.id.radio_boy:
                radioGirl.toggle();
                if (radioGirl.isChecked()) {
                    radioGirl.setChecked(false);
                }
                radioBoy.setChecked(true);
                break;
            case R.id.submit:
                baseStartActivity(instance, MainActivity.class);
                break;
        }
    }
}
