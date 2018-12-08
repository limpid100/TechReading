package com.dxl.techreading.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.dxl.techreading.R;
import com.dxl.techreading.adapter.CategoryRecyclerAdapter;
import com.dxl.techreading.base.BaseFragment;
import com.dxl.techreading.contract.CategoryContract.ICategoryView;
import com.dxl.techreading.presenter.CategoryPresenter;
import com.dxl.techreading.bean.CategoryResult.ResultsBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;

/**
 * @author dxl
 * @date 2018/11/9 13:36
 */
public class CategoryFragment extends BaseFragment<CategoryPresenter> implements ICategoryView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;

    public static final String CATEGORY_NAME = "category_name";
    /**
     * 当前页面标题
     */
    private String title;

    CategoryRecyclerAdapter recyclerAdapter;


    public static CategoryFragment newInstance(String title) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString(CATEGORY_NAME, title);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_category;
    }

    @Override
    protected void init() {
        mPresenter = new CategoryPresenter(this);

        title = getArguments().getString(CATEGORY_NAME);

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getCategoryItems(true);
            }
        });

        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getCategoryItems(false);
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //添加分割线
        DividerItemDecoration decor = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(decor);

        recyclerAdapter = new CategoryRecyclerAdapter(getContext());

        mRecyclerView.setAdapter(recyclerAdapter);


    }

    @Override
    public void setProgress(boolean show) {
        int visible = show ? View.VISIBLE : View.GONE;
        if (progress_bar.getVisibility() != visible) {
            progress_bar.setVisibility(visible);
        }
    }

    @Override
    public String getCategoryName() {
        return title;
    }

    @Override
    public void setCategoryItems(List<ResultsBean> categoryItems) {
        recyclerAdapter.setDatas(categoryItems);
    }

    @Override
    public void addCategoryItems(List<ResultsBean> categoryItems) {
        recyclerAdapter.addDatas(categoryItems);
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        final Snackbar snackbar = Snackbar.make(mRecyclerView, "加载错误 Errrrr..", Snackbar.LENGTH_LONG);
        snackbar.setAction("知道了", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                }).show();
    }

    @Override
    public void refreshFinish(boolean success) {
        mRefreshLayout.finishRefresh();
        if (success) {
            Snackbar.make(mRecyclerView, "刷新成功~", Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void loadMoreFinish(boolean success) {
        mRefreshLayout.finishLoadMore();
    }

}
