package com.dxl.techreading.contract;

import com.dxl.techreading.bean.GankCategoryResult.ResultsBean;
import com.dxl.techreading.bean.ImageInfo;
import com.dxl.techreading.view.IView;

import java.util.List;

/**
 * @author dxl
 * @date 2018/11/12 13:30
 */
public interface CategoryContract {

    interface ICategoryView extends IView {
        /**
         * 显示或隐藏正在加载提示
         * @param show
         */
        void setProgress(boolean show);

        /**
         * 获取要加载的tab名称，比如Android，iOS...拼接在请求网址中
         * @return
         */
        String getCategoryName();

        /**
         * 设置获取的数据
         * @param categoryItems 加载结果
         * @param refresh   是否刷新，如果是刷新，则重新设置数据
         */
        void setGankCategoryItems(List<ResultsBean> categoryItems, boolean refresh);

        /**
         * 加载出错，提示错误信息
         *
         * @param errorMessage 错误信息
         */
        void showErrorMessage(String errorMessage);

        /**
         * 下拉刷新完成
         *
         * @param success 是否刷新成功
         */
        void refreshFinish(boolean success);

        /**
         * 上拉加载完成
         *
         * @param success 是否加载成功
         */
        void loadMoreFinish(boolean success);

        /**
         * 设置首页banner
         * @param bannerList 。
         */
        void setBanner(List<ImageInfo> bannerList);
    }

    interface ICategoryPresenter {
        void getCategoryItems(boolean refresh);

        void getBannerData();
    }
}
