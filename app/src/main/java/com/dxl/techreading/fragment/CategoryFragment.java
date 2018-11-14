package com.dxl.techreading.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dxl.techreading.R;
import com.dxl.techreading.adapter.CategoryRecyclerAdapter;
import com.dxl.techreading.model.CategoryContract.ICategoryPresenter;
import com.dxl.techreading.model.CategoryContract.ICategoryView;
import com.dxl.techreading.model.CategoryPresenter;
import com.dxl.techreading.model.CategoryResult.ResultsBean;
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
public class CategoryFragment extends BaseFragment implements ICategoryView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;

    ICategoryPresenter mICategoryPresenter;

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
        mICategoryPresenter = new CategoryPresenter(this);

        title = getArguments().getString(CATEGORY_NAME);

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mICategoryPresenter.getCategoryItems(true);
            }
        });

        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mICategoryPresenter.getCategoryItems(false);
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //添加分割线
        DividerItemDecoration decor = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(decor);

        recyclerAdapter = new CategoryRecyclerAdapter(getContext());

        mRecyclerView.setAdapter(recyclerAdapter);

        mICategoryPresenter.subscribe();

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
        Toast.makeText(getContext(), "加载错误：错误信息 " + errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshFinish(boolean success) {
        mRefreshLayout.finishRefresh();
        if (success) {
            Toast.makeText(getContext(), "刷新成功~", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void loadMoreFinish(boolean success) {
        mRefreshLayout.finishLoadMore();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mICategoryPresenter.unSubscribe();
    }
}
