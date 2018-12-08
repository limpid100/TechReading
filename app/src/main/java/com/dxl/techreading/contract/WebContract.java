package com.dxl.techreading.contract;

import com.dxl.techreading.view.IView;

/**
 * @author dxl
 * @date 2018/11/14 8:30
 */
public interface WebContract {

    interface IWebView extends IView {
        void initWebView();

        String getUrl();

        void loadUrl(String url);
    }

    interface IWebPresenter {
        void load(String url);
    }
}
