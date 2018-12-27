package com.dxl.techreading.view;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

import com.dxl.techreading.R;
import com.dxl.techreading.adapter.TabViewPagerAdapter;
import com.dxl.techreading.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by dxl on 2018/12/20 21:53
 *
 * @author dxl
 */
public class HomeActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        initToolbar();
        initTab();
        initViewPager();
    }

    private void initViewPager() {
        //设置viewpager
        TabViewPagerAdapter viewPagerAdapter = new TabViewPagerAdapter(getSupportFragmentManager());
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance());
        fragments.add(DataFragment.newInstance());
        fragments.add(TestFragment.newInstance("title3"));
        fragments.add(TestFragment.newInstance("title4"));

        viewPagerAdapter.setFragments(fragments);

//        mTabLayout.setupWithViewPager(mViewPager);
        //不能使用setupWithViewPager关联，因为有自定义view，关联后只有文字tab
        //https://juejin.im/entry/589ec5b01b69e60059c5b9fb
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setOffscreenPageLimit(viewPagerAdapter.getCount());
        mViewPager.setCurrentItem(0);
    }

    private void initTab() {
        //添加底部tab
        for (int i = 0; i < 4; i++) {
            View view = getLayoutInflater().inflate(R.layout.tabitem_icon_layout, null);
            view.findViewById(R.id.icon).setBackgroundResource(R.drawable.tab_item_icon);
            mTabLayout.addTab(mTabLayout.newTab().setCustomView(view));
        }
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);

        //下面的代码主要通过actionbardrawertoggle将toolbar与drawablelayout关联起来
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.START);
            }
        });
    }
}
