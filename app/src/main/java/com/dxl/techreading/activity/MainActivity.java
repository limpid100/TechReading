package com.dxl.techreading.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.dxl.techreading.R;
import com.dxl.techreading.adapter.TabViewPagerAdapter;
import com.dxl.techreading.fragment.CategoryFragment;
import com.dxl.techreading.fragment.TestFragment;
import com.dxl.techreading.model.GlobalVariables;

import java.util.Arrays;

import butterknife.BindView;

/**
 * @author du_xi
 * @date 2018/11/7
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.tab)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        TabViewPagerAdapter viewPagerAdapter = new TabViewPagerAdapter(getSupportFragmentManager());

        TestFragment testFragment = TestFragment.newInstance("");

        CategoryFragment androidFragment = CategoryFragment.newInstance("Android");
        CategoryFragment iOSFragment = CategoryFragment.newInstance("iOS");
        CategoryFragment expandResourceFragment = CategoryFragment.newInstance("拓展资源");
        CategoryFragment frontFragment = CategoryFragment.newInstance("前端");
        CategoryFragment appFragment = CategoryFragment.newInstance("App");

        viewPagerAdapter.setTitles(Arrays.asList(GlobalVariables.TAB_TITLES));
        viewPagerAdapter.addFragment(testFragment, androidFragment, iOSFragment, expandResourceFragment, frontFragment, appFragment);
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setOffscreenPageLimit(viewPagerAdapter.getCount());
        mViewPager.setCurrentItem(1);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    /**
     * 实现返回时，不退出应用
     */
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}
