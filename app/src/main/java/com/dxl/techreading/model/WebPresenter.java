package com.dxl.techreading.model;

/**
 * @author dxl
 * @date 2018/11/14 9:27
 */
public class WebPresenter implements WebContract.IWebPresenter {
    private WebContract.IWebView mIWebView;

    public WebPresenter(WebContract.IWebView iWebView) {
        mIWebView = iWebView;
    }

    @Override
    public void subscribe() {
        mIWebView.initWebView();
        mIWebView.loadUrl(mIWebView.getUrl());
    }

    @Override
    public void unSubscribe() {

    }
}
