package com.lbw.blackcat.network;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.lbw.blackcat.GlideApp;
import com.lbw.blackcat.R;

/**
 * @autor lbw
 * @time 2018/1/30 11:38
 * @desc 图片加载
 */
public class ImageLoader {

    public static void imgLoader(Context context, ImageView view, String url)
    {
        GlideApp.with(context).
                load(url)
                .placeholder(R.mipmap.ic_launcher)//占位图
                .error(R.mipmap.ic_launcher)//加载失败的图片
                .into(view);
    }
}
