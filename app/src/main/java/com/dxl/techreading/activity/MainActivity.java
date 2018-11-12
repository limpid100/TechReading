package com.dxl.techreading.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.dxl.techreading.R;
import com.dxl.techreading.adapter.TabViewPagerAdapter;
import com.dxl.techreading.fragment.CategoryFragment;
import com.dxl.techreading.fragment.TestFragment;
import com.dxl.techreading.model.GlobalVariables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<Fragment> fragments = new ArrayList<>();
        for (String tabTitle : GlobalVariables.TAB_TITLES) {
            Fragment e = TestFragment.newInstance(tabTitle);
            if (tabTitle.equals("Android")) {
                e = CategoryFragment.newInstance("Android");
            }
            fragments.add(e);

        }
        TabViewPagerAdapter viewPagerAdapter = new TabViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.setTitles(Arrays.asList(GlobalVariables.TAB_TITLES));
        viewPagerAdapter.setFragments(fragments);
        mViewPager.setAdapter(viewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }


}
