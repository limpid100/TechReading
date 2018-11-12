package com.dxl.techreading.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dxl.techreading.R;
import com.dxl.techreading.adapter.CategoryRecyclerAdapter;
import com.dxl.techreading.model.CategoryContract;
import com.dxl.techreading.model.CategoryPresenter;
import com.dxl.techreading.model.CategoryResult;

import java.util.List;

import butterknife.BindView;

/**
 * @author dxl
 * @date 2018/11/9 13:36
 */
public class CategoryFragment extends BaseFragment implements CategoryContract.ICategoryView {

    CategoryContract.ICategoryPresenter mICategoryPresenter;

    public static final String CATEGORY_NAME = "category_name";

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    CategoryRecyclerAdapter recyclerAdapter;

    public static CategoryFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString(CATEGORY_NAME, title);
        CategoryFragment fragment = new CategoryFragment();
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

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerAdapter = new CategoryRecyclerAdapter(getContext());

        mRecyclerView.setAdapter(recyclerAdapter);

        mICategoryPresenter.subscribe();

    }

    @Override
    public void setCategoryItems(List<CategoryResult> categoryItems) {
        recyclerAdapter.setCategoryResults(categoryItems);
    }
}
