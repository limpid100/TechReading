package com.dxl.techreading.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.dxl.techreading.R;
import com.dxl.techreading.adapter.CategoryRecyclerAdapter;
import com.dxl.techreading.model.CategoryContract.ICategoryPresenter;
import com.dxl.techreading.model.CategoryContract.ICategoryView;
import com.dxl.techreading.model.CategoryPresenter;
import com.dxl.techreading.model.CategoryResult.ResultsBean;

import java.util.List;

import butterknife.BindView;

/**
 * @author dxl
 * @date 2018/11/9 13:36
 */
public class CategoryFragment extends BaseFragment implements ICategoryView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

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

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //添加分割线
        DividerItemDecoration decor = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
//        decor.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.list_divider));
        mRecyclerView.addItemDecoration(decor);

        recyclerAdapter = new CategoryRecyclerAdapter(getContext());

        mRecyclerView.setAdapter(recyclerAdapter);

        mICategoryPresenter.subscribe();

    }

    @Override
    public String getCategoryName() {
        return title;
    }

    @Override
    public void setCategoryItems(List<ResultsBean> categoryItems) {
        recyclerAdapter.setCategoryResults(categoryItems);
    }

    @Override
    public void addCategoryItems(List<ResultsBean> categoryItems) {
        recyclerAdapter.addCategoryResults(categoryItems);
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(getContext(), "加载错误：错误信息 " + errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mICategoryPresenter.unSubscribe();
    }
}
