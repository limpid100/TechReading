package com.dxl.techreading.model;

/**
 * @author du_xi
 * @date 2018/11/14
 */
public interface SplashConstract {
    interface ISplashView {
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

    interface ISplashPresenter extends BasePresenter{

    }
}
