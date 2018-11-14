package com.dxl.techreading.model;

/**
 * @author dxl
 * @date 2018/11/14 8:30
 */
public interface WebContract {

    interface IWebView {
        void initWebView();

        String getUrl();

        void loadUrl(String url);
    }

    interface IWebPresenter extends BasePresenter {

    }
}
