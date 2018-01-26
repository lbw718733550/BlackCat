package com.lbw.blackcat.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by del on 16/12/30.
 */
public class FragmentUtil {

    private List<Fragment> fragmentList = null;
    private int[] tabIcons = null;   //tab子标签未按下的图
    private int[] tabIcons_on = null;   //tab子标签按下的图
    private int tabtext_style;   //tab子标签按下的图
    private int tabtext_style_off;   //tab子标签mei按下的图
    private List<View> textViewlist = null;  //tab对应的文字view
    private List<View> imageViewlist = null;  //tab对应的图片view
    private List<View> buttonViewlist = null;  //tab对应的按钮
    private String[] tabtitle;//tab子标签标题
    private Context context = null;
    private int contextId; //布局ID
    private List<Integer> isAddList = null;
    // 创建fragmentmanager
    private FragmentManager mfragmentManager;
    private int positionSelect = 0;
    //返回当前是第几个页面
    public int getPositionSelect() {
        return positionSelect;
    }

    private String BUTTON = "BUTTON";
    private String IMAGE = "IMAGE";
    private String TabTYPE = IMAGE;//默认是图片加文字的tab布局    button的时候为“button”

    public FragmentUtil(Context context,int contextId,List<Fragment> fragmentList,List<View> textViewlist,List<View> imageViewlist, int[] tabIcons, int[] tabIcons_on,
                        int tabtext_style,int tabtext_style_off,String[] tabtitle, FragmentManager mfragmentManager) {
        this.fragmentList = fragmentList;
        this.tabIcons = tabIcons;
        this.tabIcons_on = tabIcons_on;
        this.tabtitle = tabtitle;
        this.tabtext_style = tabtext_style;
        this.tabtext_style_off = tabtext_style_off;
        this.mfragmentManager = mfragmentManager;
        this.textViewlist = textViewlist;
        this.imageViewlist = imageViewlist;
        this.context = context;
        this.contextId = contextId;
        isAddList = new ArrayList<>();
        init();
        TabTYPE = IMAGE;
    }

    public FragmentUtil(Context context,int contextId,List<Fragment> fragmentList,List<View> buttonViewlist, int[] tabIcons, int[] tabIcons_on,
                        int tabtext_style,int tabtext_style_off,String[] tabtitle, FragmentManager mfragmentManager) {
        this.fragmentList = fragmentList;
        this.tabIcons = tabIcons;
        this.tabIcons_on = tabIcons_on;
        this.tabtitle = tabtitle;
        this.tabtext_style = tabtext_style;
        this.tabtext_style_off = tabtext_style_off;
        this.mfragmentManager = mfragmentManager;
        this.buttonViewlist = buttonViewlist;
        this.context = context;
        this.contextId = contextId;
        isAddList = new ArrayList<>();
        init();
        TabTYPE =BUTTON;
    }


    /**
     * 监听3个页面按钮点击后切换
     *
     */
    public void showDetail(int position) {
        positionSelect  = position;
        // 重置
        resetBtn();
        // 开启事件
        FragmentTransaction transaction = mfragmentManager.beginTransaction();
        // 隐藏
        hideFragments(transaction);
        if(IMAGE.equals(TabTYPE)) {
            ((TextView) textViewlist.get(position)).setTextAppearance(context, tabtext_style);
            ((ImageView) imageViewlist.get(position)).setImageResource(tabIcons_on[position]);
        }else {
            if(BUTTON.equals(TabTYPE))
            {
                ((Button)buttonViewlist.get(position)).setBackgroundResource(tabIcons_on[position]);
                ((Button)buttonViewlist.get(position)).setTextAppearance(context,tabtext_style);
            }
        }
        // 按钮变化
        if (isAddList.get(position) == 0) {
            transaction.add(contextId, (fragmentList.get(position)));
            isAddList.set(position,1);
        } else {
            transaction.show(fragmentList.get(position));
        }
        transaction.commit();
    }


    private void init()
    {
        for (int i=0;i<fragmentList.size();i++)
        {
            isAddList.add(0);
        }
    }
    /**
     * 重置fragment页面按钮
     */
    private void resetBtn() {
        if (IMAGE.equals(TabTYPE)) {
            for (int i = 0; i < fragmentList.size(); i++) {
                ((TextView) textViewlist.get(i)).setTextAppearance(context, tabtext_style_off);
                ((ImageView) imageViewlist.get(i)).setImageResource(tabIcons[i]);
            }
        } else {
            if (BUTTON.equals(TabTYPE)) {
                for (int i = 0; i < fragmentList.size(); i++) {
                    ((Button) buttonViewlist.get(i)).setBackgroundResource(tabIcons[i]);
                    ((Button) buttonViewlist.get(i)).setTextAppearance(context, tabtext_style_off);
                }
            }
        }
    }

    /**
     * fragment页面全部隐藏
     */
    private void hideFragments(FragmentTransaction transaction) {
        for (int i = 0; i < fragmentList.size(); i++) {
            if (isAddList.get(i) != 0) {
                transaction.hide(fragmentList.get(i));
            }
        }
    }

}
