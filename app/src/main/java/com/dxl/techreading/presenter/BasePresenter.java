package com.dxl.techreading.presenter;

import com.dxl.techreading.view.IView;

/**
 * @author du_xi
 * @date 2018/12/8
 */
public class BasePresenter<T extends IView> implements IPresenter<T> {

    protected T mView;

    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public boolean isViewAttached() {
        return mView != null;
    }
}
