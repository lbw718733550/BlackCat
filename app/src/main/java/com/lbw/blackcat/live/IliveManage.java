package com.lbw.blackcat.live;

import android.content.Context;
import android.widget.Toast;

import com.lbw.blackcat.BlackCatApp;
import com.lbw.blackcat.Constants;
import com.tencent.TIMCallBack;
import com.tencent.TIMFriendGenderType;
import com.tencent.TIMFriendshipManager;
import com.tencent.TIMManager;
import com.tencent.TIMUserProfile;
import com.tencent.TIMValueCallBack;
import com.tencent.ilivesdk.ILiveCallBack;
import com.tencent.ilivesdk.ILiveSDK;
import com.tencent.ilivesdk.core.ILiveLog;
import com.tencent.ilivesdk.core.ILiveLoginManager;
import com.tencent.livesdk.ILVLiveConfig;
import com.tencent.livesdk.ILVLiveManager;
import com.tencent.qalsdk.sdk.MsfSdkUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @autor lbw
 * @time 2018/1/30 9:20
 * @desc 直播manage
 */

public class IliveManage {

    private static IliveManage iliveManage;

    public static IliveManage getInstance()
    {
        if (iliveManage == null) {
            synchronized (IliveManage.class){
                if (iliveManage == null) {
                    iliveManage = new IliveManage();
                    return iliveManage;
                }
            }
        }
        return iliveManage;
    }

    /**
     * @desc 初始化
     * @another lbw
     * @time 2018/1/30 9:51
     */
    public void init(Context context){
        if(MsfSdkUtils.isMainProcess(context)){    // 仅在主线程初始化
            // 初始化LiveSDK
            ILiveLog.setLogLevel(ILiveLog.TILVBLogLevel.DEBUG);
            ILiveSDK.getInstance().initSdk(context, Constants.TIM_APPID, Constants.TIM_ACCOUNTTYPE);
            List<String> customInfos = new ArrayList<String>();
            customInfos.add(Constants.CUSTOM_GET);
            customInfos.add(Constants.CUSTOM_SEND);
            customInfos.add(Constants.CUSTOM_LEVEL);
            customInfos.add(Constants.CUSTOM_RENZHENG);
            TIMManager.getInstance().initFriendshipSettings(Constants.allBaseInfo, null);
            ILVLiveManager.getInstance().init(new ILVLiveConfig()
                    .setLiveMsgListener(MessageObservable.getInstance()));
        }
    }

    /**
     * @desc 调用腾讯IM登录
     * @another lbw
     * @time 2018/1/30 10:09
     */
    public void Login( final String accountStr, String passwordStr, final CallBack callBack)
    {
        ILiveLoginManager.getInstance().tlsLogin(accountStr, passwordStr, new ILiveCallBack<String>() {
            @Override
            public void onSuccess(String data) {
                //登陆成功。
                loginLive(accountStr, data, callBack);
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                callBack.onError(module,errCode,errMsg);
            }
        });
    }

    /**
     * @desc 用返回的data 登录
     * @another lbw
     * @time 2018/1/30 10:55
     */
    private void loginLive( String accountStr, String data, final CallBack callBack ) {
        ILiveLoginManager.getInstance().iLiveLogin(accountStr, data, new ILiveCallBack() {

            @Override
            public void onSuccess(Object data) {
               callBack.onSuccess(data);
               getSelfInfo();
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                callBack.onError(module,errCode,errMsg);
            }
        });
    }

    /**
     * @desc 获取账号信息
     * @another lbw
     * @time 2018/1/30 10:37
     */
    public void getSelfInfo() {
        TIMFriendshipManager.getInstance().getSelfProfile(new TIMValueCallBack<TIMUserProfile>() {
            @Override
            public void onError(int i, String s) {
                Toast.makeText(BlackCatApp.getInstance(), "获取信息失败：" + i + ";str = " + s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(TIMUserProfile timUserProfile) {
                //获取自己信息成功
                BlackCatApp.getInstance().setSelfProfile(timUserProfile);
            }
        });
    }

    /**
     * @desc 注册
     * @another lbw
     * @time 2018/1/30 10:57
     */
    public void regist(String accountStr, String passwordStr, final CallBack callBack)
    {
        ILiveLoginManager.getInstance().tlsRegister(accountStr, passwordStr, new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {
                callBack.onSuccess(data);
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                callBack.onError(module,errCode,errMsg);
            }
        });
    }

    /**
     * @desc 修改昵称
     * @another lbw
     * @time 2018/1/30 15:42
     */
    public void updateNickName(String nickName, final CallBack callBack)
    {
        TIMFriendshipManager.getInstance().setNickName(nickName, new TIMCallBack() {
            @Override
            public void onError(int i, String s) {
                callBack.onError("",i,s);
            }

            @Override
            public void onSuccess() {
                //更新成功
                callBack.onSuccess("");
            }
        });
    }
    /**
     * @desc 修改头像
     * @another lbw
     * @time 2018/1/30 15:42
     */
    public void updateHeadUrl(String url, final CallBack callBack)
    {
        TIMFriendshipManager.getInstance().setFaceUrl(url, new TIMCallBack() {
            @Override
            public void onError(int i, String s) {
                callBack.onError("",i,s);
            }
            @Override
            public void onSuccess() {
                //更新头像成功
                callBack.onSuccess("");
            }
        });
    }

    /**
     * @desc 修改性别
     * @another lbw
     * @time 2018/1/30 15:48
     * @param sex true = 男 false = 女
     */
    public void updateSex(boolean sex, final CallBack callBack)
    {
        TIMFriendGenderType gender = sex ? TIMFriendGenderType.Male : TIMFriendGenderType.Female;
        TIMFriendshipManager.getInstance().setGender(gender, new TIMCallBack() {

            @Override
            public void onError(int i, String s) {
                callBack.onError("",i,s);
            }
            @Override
            public void onSuccess() {
                //更新头像成功
                callBack.onSuccess("");
            }
        });
    }

//    public void updateUserInfo(TIMFriendshipManager.ModifyUserProfileParam param)
//    {
//        TIMFriendshipManager.getInstance().modifyProfile(param, new TIMCallBack() {
//            @Override
//            public void onError(int code, String desc) {
//                //错误码code和错误描述desc，可用于定位请求失败原因
//                //错误码code列表请参见错误码表
//            }
//
//            @Override
//            public void onSuccess() {
//            }
//        });
//    }


    public interface CallBack
    {
        void onError(String module, int errCode, String errMsg);
        void onSuccess(Object data);
    }

}
