package com.lbw.blackcat;

import com.tencent.TIMFriendshipManager;

/**
 * @autor lbw
 * @time 2018/1/30 10:40
 * @desc
 */

public class Constants {

    //腾讯云
    public static final int TIM_APPID = 1400067065;
    public static final int TIM_ACCOUNTTYPE = 22353;
    //七牛云
    public static final String QN_ACCESSKEY = "V4VHpuYLwUEkwdORdH3etJsg1Mr4mgdKKKBiMTR-";
    public static final String QN_SECRETKEY = "gYhJYGsRQe0Ac-Yzv0K-dToSRmS9Devwnb0JEL38";
    public static final String QN_DOMAIN = "http://p3ebysgmg.bkt.clouddn.com/";
    public static final String QN_BUCKETNAME = "blackcat";

    //自定义字段
    private static final String PREFIX = "Tag_Profile_Custom_";
    public static final String CUSTOM_RENZHENG = PREFIX + "renzhen";
    public static final String CUSTOM_LEVEL = PREFIX + "level";
    public static final String CUSTOM_GET = PREFIX + "getNums";
    public static final String CUSTOM_SEND = PREFIX + "sendNums";

    //腾讯基础字段
    public static final long allBaseInfo =
            TIMFriendshipManager.TIM_PROFILE_FLAG_BIRTHDAY |
                    TIMFriendshipManager.TIM_PROFILE_FLAG_FACE_URL |
                    TIMFriendshipManager.TIM_PROFILE_FLAG_GENDER |
                    TIMFriendshipManager.TIM_PROFILE_FLAG_LANGUAGE |
                    TIMFriendshipManager.TIM_PROFILE_FLAG_LOCATION |
                    TIMFriendshipManager.TIM_PROFILE_FLAG_NICK |
                    TIMFriendshipManager.TIM_PROFILE_FLAG_SELF_SIGNATURE |
                    TIMFriendshipManager.TIM_PROFILE_FLAG_REMARK |
                    TIMFriendshipManager.TIM_PROFILE_FLAG_GROUP;
}
