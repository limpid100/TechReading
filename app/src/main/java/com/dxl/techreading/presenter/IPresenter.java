package com.dxl.techreading.presenter;

import com.dxl.techreading.view.IView;

/**
 * presenter
 *
 * @author du_xi
 * @date 2018/12/8
 */
public interface IPresenter<T extends IView> {

    /**
     * 依附生命View
     *
     * @param view
     */
    void attachView(T view);

    /**
     * 分离View
     */
    void detachView();

    /**
     * 判断View是否销毁
     *
     * @return
     */
    boolean isViewAttached();
}
