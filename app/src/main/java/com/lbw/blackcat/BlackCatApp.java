package com.lbw.blackcat;

import android.app.Application;

import com.lbw.blackcat.live.IliveManage;
import com.lbw.blackcat.qiniu.QnUploadHelper;
import com.tencent.TIMUserProfile;

/**
 * @autor lbw
 * @time 2018/1/30 9:52
 * @desc
 */

public class BlackCatApp extends Application{

    public static BlackCatApp blackCatApp;
    private TIMUserProfile mSelfProfile;

    @Override
    public void onCreate() {
        super.onCreate();
        blackCatApp = this;
        //腾讯云
        IliveManage.getInstance().init(blackCatApp);
        //七牛
        QnUploadHelper.getInstance().init(Constants.QN_ACCESSKEY,
                Constants.QN_SECRETKEY,
                Constants.QN_DOMAIN,
                Constants.QN_BUCKETNAME);

    }


    public static BlackCatApp getInstance()
    {
        return blackCatApp;
    }

    public void setSelfProfile(TIMUserProfile userProfile) {
        mSelfProfile = userProfile;
    }

    public TIMUserProfile getSelfProfile() {
        return mSelfProfile;
    }
}
