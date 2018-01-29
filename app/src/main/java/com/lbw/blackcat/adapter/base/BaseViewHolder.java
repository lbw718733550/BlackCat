/**
 * Copyright 2013 Joan Zapata
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lbw.blackcat.adapter.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    View convertView;
    Context context;
    private SparseArray<View> views;

    public BaseViewHolder(View itemView, Context context) {
        super(itemView);
        this.convertView = itemView;
        this.context = context;
        this.views = new SparseArray<View>();
    }


    public View getConvertView() {

        return convertView;
    }


    public <T extends View> T setViewBind(int viewId) {
        T view = (T) convertView.findViewById(viewId);
        return  view;
    }

    /**
     * textview
     * @param id
     * @param text
     */
    public TextView setText(int id, String text) {
        TextView tx = (TextView) convertView.findViewById(id);
        tx.setText(text);

        return tx;
    }

    public TextView setText(int id, String showTxt, String searchTxt) {
        TextView tx = (TextView) convertView.findViewById(id);
        if (searchTxt != null && !"".equals(searchTxt)) {
            SpannableStringBuilder builder = new SpannableStringBuilder(showTxt);
            int curIndex = 0;
            for (int i = 0; i < searchTxt.length(); i++) {
                String s = searchTxt.substring(i, i + 1);
                if (showTxt.contains(s)) {
                    curIndex = showTxt.indexOf(s, curIndex);
                    ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.argb(255, 55, 200, 55));
                    builder.setSpan(redSpan, curIndex, curIndex + 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                }
            }
            tx.setText(builder);
            return tx;
        } else {
            //默认
            tx = setText(id, showTxt);
            return tx;
        }
    }

    public TextView setTextVisibility(int id, int state) {
        TextView tx = (TextView) convertView.findViewById(id);
        if(state == 0)
        {
            tx.setVisibility(View.GONE);
        }else {
            tx.setVisibility(View.VISIBLE);
        }
        return tx;
    }

    /**
     * imageview  src
     * @param id
     * @param resouceId
     */
    public ImageView setImageResource(int id, int resouceId) {
        ImageView img= (ImageView) convertView.findViewById(id);
        img.setImageResource(resouceId);
        return img;
    }

    /**
     * imageview  src
     * @param id
     * @param resouceId
     */
    public <T extends View> T setbackgrounpResource(int id, int resouceId) {
        T view= (T) convertView.findViewById(id);
        view.setBackgroundResource(resouceId);
        return view;
    }


    /**
     * imageview  image
     */
    public ImageView setImageLoader(int id, String imgPath)
    {
        ImageView img= (ImageView) convertView.findViewById(id);
//        ImageLoader.getInstance().displayImage(imgPath,img, MyApplication.defaultOptions);
        return img;
    }
//    /**
//     * imageview  image
//     */
//    public ImageView setImageLoaderShearSize(int id, String imgPath,int width,int height)
//    {
//        ImageView img= (ImageView) convertView.findViewById(id);
//        ImageLoader.getInstance().displayImage(imgPath,img, MyApplication.defaultOptions);
////        ImageLoadUtil.ImageLoad(context,imgPath,img);
//        return img;
//
//    }
    /**
     * imageview  image
     */
    public ImageView setImageLoader(int id, Bitmap bitmap)
    {
        ImageView img= (ImageView) convertView.findViewById(id);
        img.setImageBitmap(bitmap);
        return img;

    }



    public <T extends View> T setViewVisibility(int viewId, int state) {
        T view = (T) convertView.findViewById(viewId);
        view.setVisibility(state);
        return  view;
    }

    public <T extends View> T setBottonOnClickListener(int viewId, View.OnClickListener onClickListener)
    {
        T view = (T) convertView.findViewById(viewId);
        view.setOnClickListener(onClickListener);
        return view;
    }




}
