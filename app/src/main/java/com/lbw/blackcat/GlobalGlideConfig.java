package com.lbw.blackcat;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * @autor lbw
 * @time 2018/1/30 14:16
 * @desc glide V4 要使用原本的链式调用某些方法 的GlideApp 需要在包名下创建一个类继承AppGlideModule ，再加上注解@GlideModule
 */
@GlideModule
public class GlobalGlideConfig extends AppGlideModule {

}
