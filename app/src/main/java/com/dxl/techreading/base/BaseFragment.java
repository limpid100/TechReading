package com.dxl.techreading.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dxl.techreading.presenter.IPresenter;
import com.dxl.techreading.view.IView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author dxl
 * @date 2018/11/9 13:31
 */
public abstract class BaseFragment<T extends IPresenter> extends Fragment implements IView {

    protected T mPresenter;

    private Unbinder mUnbinder;

    protected Context mContext;

    /**
     * 布局文件
     * @return
     */
    protected abstract int getContentViewId();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getContentViewId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
        initPresenter();
        init();
    }

    protected void initPresenter() {
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    protected T createPresenter(){
        return null;
    }

    /**
     * 界面初始化
     */
    protected abstract void init();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mContext = null;
    }
}
