package com.lbw.blackcat.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.lbw.blackcat.R;
import com.lbw.blackcat.activity.MainActivity;
import com.lbw.blackcat.activity.base.BaseActivity;
import com.lbw.blackcat.live.IliveManage;
import com.lbw.blackcat.network.ImageLoader;
import com.lbw.blackcat.qiniu.QnUploadHelper;
import com.lbw.blackcat.utils.ToastUtil;
import com.lbw.blackcat.widget.CircleImageView;
import com.qiniu.android.http.ResponseInfo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.iwf.photopicker.PhotoPicker;

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

    private String headUrl = "";

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
                //选择头像
                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .setShowCamera(true)
                        .setShowGif(true)
                        .setPreviewEnabled(false)
                        .start(this, PhotoPicker.REQUEST_CODE);
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
                if (examine()) {
                    QnUploadHelper.getInstance().uploadPic(headUrl, System.currentTimeMillis() + "head", new QnUploadHelper.UploadCallBack() {
                        @Override
                        public void success(String url) {
                            submitHead(url);
                        }
                        @Override
                        public void fail(String key, ResponseInfo info) {
                            ToastUtil.showError(instance, "提交失败！");
                        }
                    });
                }
                break;
        }
    }

    private void submitHead(String url) {
        IliveManage.getInstance().updateHeadUrl(url, new IliveManage.CallBack() {
            @Override
            public void onError(String module, int errCode, String errMsg) {
                ToastUtil.showError(instance,"更改头像失败!");
            }
            @Override
            public void onSuccess(Object data) {
                submitNickName();
            }
        });
    }

    private void submitNickName() {
        IliveManage.getInstance().updateNickName(etNickname.getText().toString(), new IliveManage.CallBack() {
            @Override
            public void onError(String module, int errCode, String errMsg) {
                ToastUtil.showError(instance,"更改昵称失败!");
            }
            @Override
            public void onSuccess(Object data) {
                submitSex();
                baseStartActivity(instance, MainActivity.class);
                baseFinish();
            }
        });
    }

    private void submitSex() {
        IliveManage.getInstance().updateNickName(etNickname.getText().toString(), new IliveManage.CallBack() {
            @Override
            public void onError(String module, int errCode, String errMsg) {
                ToastUtil.showError(instance,"更改性别失败!");
            }
            @Override
            public void onSuccess(Object data) {
            }
        });
    }

    private boolean examine() {
        if (TextUtils.isEmpty(etNickname.getText().toString()) ) {
            ToastUtil.showError(instance,"请填写昵称！");
            return false;
        }
        if(!radioBoy.isChecked() && !radioGirl.isChecked()){
            ToastUtil.showError(instance,"请选择性别！");
            return false;
        }
        if(TextUtils.isEmpty(headUrl)){
            ToastUtil.showError(instance,"请设置头像！");
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //选择头像回调
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                ArrayList<String> photos =
                        data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                headUrl = photos.get(0);
                ImageLoader.imgLoader(instance,ivHead,photos.get(0));
            }
        }
    }
}
