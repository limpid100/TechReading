package com.dxl.techreading.contract;

import com.dxl.techreading.base.BasePresenter;
import com.dxl.techreading.view.IView;

/**
 * @author du_xi
 * @date 2018/11/14
 */
public interface SplashConstract {
    interface ISplashView extends IView {
        /**
         * 获取图片url成功，展示图片
         *
         * @param url
         */
        void showBingPic(String url);

        /**
         * 获取图片失败
         *
         * @param errorMessage
         */
        void failLoadPic(String errorMessage);
    }

    interface ISplashPresenter {
        /**
         * 获取bing每日壁纸URL
         */
        void getImageUrl();
    }
}
