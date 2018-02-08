package com.lbw.blackcat.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AnimationSet;
import android.widget.ImageView;

import com.lbw.blackcat.R;
import com.lbw.blackcat.activity.base.BaseActivity;
import com.lbw.blackcat.activity.fragment.ImageListFragment;
import com.lbw.blackcat.activity.fragment.LiveListFragment;
import com.lbw.blackcat.utils.CommonUtils;
import com.lbw.blackcat.utils.MoveEvalutor;
import com.lbw.blackcat.widget.MyActionBarDrawerToggle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.main_function)
    ConstraintLayout mainFunction;
    @BindView(R.id.function_back)
    ImageView functionBack;
    @BindView(R.id.function_fab_live)
    FloatingActionButton functionFabLive;
    @BindView(R.id.function_fab_vedio)
    FloatingActionButton functionFabVedio;
    @BindView(R.id.function_fab_iamge)
    FloatingActionButton functionFabIamge;

    private List<Fragment> fragments = new ArrayList<>();
    private FragmentManager fragmentManager;

    /**
     * 功能点击展开动画
     */
    private PointF startPoint = null;//原点坐标就是view的所在
    private PointF flagPoint = null;//点p1
    private PointF endPoint = null;//点p2
    private List<View> functionViews ;
    private boolean functionState = false;

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
            transaction.add(R.id.fragmetn_content, fragment);
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


    @OnClick({R.id.fab,R.id.function_back, R.id.function_fab_live, R.id.function_fab_vedio, R.id.function_fab_iamge})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab:
                initFunctionAnim();
                onOffAnim(fab);
                startBezierAnim(view, flagPoint, startPoint, endPoint);
                break;
            case R.id.function_back:
                functionFabBtnAnim();
                break;
            case R.id.function_fab_live:
                break;
            case R.id.function_fab_vedio:
                break;
            case R.id.function_fab_iamge:
                break;
        }
    }

    /**
     * @desc 初始化 动画相关
     * @another lbw
     * @time 2018/2/8 15:37
     */
    private void initFunctionAnim()
    {
        if (null == startPoint) {
            endPoint = new PointF(fab.getX(), fab.getY());
            flagPoint = new PointF(CommonUtils.getScreenMetrics().x / 3f * 2, CommonUtils.getScreenMetrics().y / 3f * 2);
            startPoint = new PointF(CommonUtils.getScreenMetrics().x / 2f - fab.getWidth() / 2, CommonUtils.getScreenMetrics().y / 2f - fab.getHeight() / 2);

            functionViews = new ArrayList<>();
            functionViews.add(functionBack);
            functionViews.add(functionFabIamge);
            functionViews.add(functionFabLive);
            functionViews.add(functionFabVedio);
        }
    }

    /**
     * @desc 动画 开关
     * @another lbw
     * @time 2018/2/8 11:21
     */
    private void onOffAnim(View view) {
        if (functionState) {
            PointF t = startPoint;
            startPoint = endPoint;
            endPoint = t;
            flagPoint = new PointF(startPoint.x, endPoint.y );

        }else {
            PointF t = endPoint;
            endPoint = startPoint;
            startPoint = t;
            flagPoint = new PointF(endPoint.x, startPoint.y );
        }
    }


    /**
     * @desc 贝塞尔曲线动画
     * @another lbw
     * @time 2018/2/8 10:46
     */
    private void startBezierAnim(final View view, PointF flagPoint, final PointF startPoint, final PointF endPoint) {

        ValueAnimator mValueAnimator = ValueAnimator.ofObject(new MoveEvalutor(flagPoint),
                startPoint, endPoint);
        mValueAnimator.setDuration(200);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point = (PointF) animation.getAnimatedValue();
                view.setX(point.x);
                view.setY(point.y);
                //移动到结束点的时候开启扩大动画
                if (endPoint.x == point.x && endPoint.y == point.y) {
                    if (functionState) {
                        functionState = false;
                    }else {
                        fab.setVisibility(View.GONE);
                        startScaleAnim();
                    }
                }
            }
        });
        mValueAnimator.start();

    }

    /**
     * @desc 背景扩大的动画
     * @another lbw
     * @time 2018/2/8 13:53
     */
    private void startScaleAnim() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            int centerX = CommonUtils.getScreenMetrics().x / 2;
            int centerY = CommonUtils.getScreenMetrics().y / 2;
            float startRadius = 0;
            float finalRadius = 0;
            if (functionState) {
                finalRadius = 0;
                startRadius = (float) Math.hypot((double) centerX, (double) centerY);
            }else {
                startRadius = 0;
                finalRadius = (float) Math.hypot((double) centerX, (double) centerY);
            }
            if (functionState) {
            }else {
                mainFunction.setVisibility(View.VISIBLE);
            }
            Animator mCircularReveal = ViewAnimationUtils.createCircularReveal(mainFunction, centerX, centerY, startRadius, finalRadius);
            // 设置动画持续时间，并开始动画
            mCircularReveal.setDuration(200).start();
            mCircularReveal.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    if (!functionState) {
                        functionFabBtnAnim();
                    }else {
                        onOffAnim(fab);
                        fab.setVisibility(View.VISIBLE);
                        startBezierAnim(fab,flagPoint,startPoint,endPoint);
                        mainFunction.setVisibility(View.GONE);
                    }
                }
            });
        } else {

        }
    }

    /**
     * @desc 功能fab的显示隐藏动画
     * @another lbw
     * @time 2018/2/8 15:41
     */
    private void functionFabBtnAnim() {
        if (functionState) {
            for (View view : functionViews) {
                view.setVisibility(View.GONE);
            }
            startScaleAnim();
        }else {
            for (View functionView : functionViews) {
                functionView.setVisibility(View.VISIBLE);
            }
            functionState = true;
        }
    }


}