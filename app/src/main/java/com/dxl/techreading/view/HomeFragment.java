package com.dxl.techreading.view;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dxl.techreading.R;
import com.dxl.techreading.adapter.HomeFragmentRecyclerAdapter;
import com.dxl.techreading.adapter.RecyclerArrayAdapter;
import com.dxl.techreading.base.BaseFragment;
import com.dxl.techreading.bean.GankCategoryResult;
import com.dxl.techreading.contract.CategoryContract;
import com.dxl.techreading.customview.CycleViewPager;
import com.dxl.techreading.bean.ImageInfo;
import com.dxl.techreading.presenter.CategoryPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * @author dxl
 * @date 2018/12/21 16:15
 */
public class HomeFragment extends BaseFragment<CategoryPresenter> implements CategoryContract.ICategoryView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    CycleViewPager mCycleViewPager;

    HomeFragmentRecyclerAdapter adapter;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected CategoryPresenter createPresenter() {
        return new CategoryPresenter();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {
        initView();
    }

    private void initView() {
        mPresenter.getBannerData();
        mPresenter.getCategoryItems(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        adapter = new HomeFragmentRecyclerAdapter(mContext);
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                return LayoutInflater.from(mContext).inflate(R.layout.fragment_home_head, parent, false);
            }

            @Override
            public void onBindView(View headerView) {
                initHeaderView(headerView);
            }
        });
        mRecyclerView.setAdapter(adapter);
    }


    private void initHeaderView(View headerView) {
        mCycleViewPager = headerView.findViewById(R.id.cycle_view);

    }

    @Override
    public void setProgress(boolean show) {

    }

    @Override
    public String getCategoryName() {
        return "Android";
    }

    @Override
    public void setGankCategoryItems(List<GankCategoryResult.ResultsBean> categoryItems, boolean refresh) {
        if (refresh) {
            adapter.setData(categoryItems);
        } else {

        }
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Log.e("dxl", errorMessage);
    }

    @Override
    public void refreshFinish(boolean success) {

    }

    @Override
    public void loadMoreFinish(boolean success) {

    }

    @Override
    public void setBanner(List<ImageInfo> bannerList) {
        mCycleViewPager.setData(bannerList, null);
    }
}
