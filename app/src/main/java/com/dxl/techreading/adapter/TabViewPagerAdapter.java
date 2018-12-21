package com.dxl.techreading.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dxl
 * @date 2018/11/9 13:14
 */
public class TabViewPagerAdapter extends FragmentStatePagerAdapter {

    public void setFragments(List<Fragment> fragments) {
        mFragments = fragments;
    }

    public void addFragment(Fragment... fragments) {
        mFragments.addAll(Arrays.asList(fragments));
    }

    private List<Fragment> mFragments = new ArrayList<>();

    public void setTitles(List<String> titles) {
        mTitles = titles;
    }

    private List<String> mTitles = new ArrayList<>();

    public TabViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.size() <= position ? "测试" : mTitles.get(position);
    }
}
