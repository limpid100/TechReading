package com.dxl.techreading.presenter;

import com.dxl.techreading.contract.WebContract;

/**
 * @author dxl
 * @date 2018/11/14 9:27
 */
public class WebPresenter extends BasePresenter<WebContract.IWebView> implements WebContract.IWebPresenter {

    @Override
    public void load(String url) {
        mView.initWebView();
        mView.loadUrl(mView.getUrl());
    }
}
