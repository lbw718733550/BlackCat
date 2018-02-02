package com.lbw.blackcat.activity.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lbw.blackcat.R;
import com.lbw.blackcat.activity.base.BaseFragment;
import com.lbw.blackcat.utils.ToastUtil;

/**
 * @autor lbw
 * @time 2018/2/2 13:51
 * @desc 直播fragment
 */

public class LiveListFragment extends BaseFragment {


    @Override
    public int setContentViewId() {
        return R.layout.cat_fragment_livelist;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    }


    @Override
    public void onResume() {
        super.onResume();
    }
}
