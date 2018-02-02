package com.lbw.blackcat.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

import com.lbw.blackcat.R;
import com.lbw.blackcat.activity.base.BaseActivity;
import com.lbw.blackcat.activity.fragment.ImageListFragment;
import com.lbw.blackcat.activity.fragment.LiveListFragment;
import com.lbw.blackcat.widget.MyActionBarDrawerToggle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.main_context)
    ConstraintLayout mainContext;

    private List<Fragment> fragments = new ArrayList<>();
    private FragmentManager fragmentManager ;

    @Override
    public int setContentViewId() {
        return R.layout.cat_activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        initDrawer();
        initFragment();
    }

    /**
     * @desc fragment
     * @another lbw
     * @time 2018/2/2 13:48
     */
    private void initFragment() {
        LiveListFragment liveListFragment = new LiveListFragment();
        ImageListFragment imageListFragment = new ImageListFragment();
        fragments.add(liveListFragment);
        fragments.add(imageListFragment);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        for (Fragment fragment : fragments) {
            transaction.add( R.id.fragmetn_content, fragment);
        }
        showDetal(0);
        /**设置MenuItem默认选中项**/
        navigationView.getMenu().getItem(0).setChecked(true);
        transaction.commit();
    }

    /**
     * @desc 抽屉设置
     * @another lbw
     * @time 2018/2/2 11:21a
     */
    private void initDrawer() {
        MyActionBarDrawerToggle toggle = new MyActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        toggle.setOnDragListener(new MyActionBarDrawerToggle.onDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //实现主页面随着 抽屉拉开而移动
                mainContext.setX(slideOffset * drawerView.getWidth());
            }
        });

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * @desc 侧滑菜单点击事件
     * @another lbw
     * @time 2018/1/26 10:56
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_livelist:
                showDetal(0);
                break;
            case R.id.nav_imagelist:
                showDetal(1);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * @desc 切换fragment
     * @another lbw
     * @time 2018/2/2 14:26
     */
    private void showDetal(int position) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            if (position == i) {
                transaction.show(fragments.get(i));
            } else {
                transaction.hide(fragments.get(i));
            }
        }
        transaction.commit();
    }


    @OnClick({R.id.fab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab:
                startAnim();
                break;
        }
    }

    /**
     * @desc fab 动画
     * @another lbw
     * @time 2018/2/2 17:18
     */
    private void startAnim() {
    }

}
