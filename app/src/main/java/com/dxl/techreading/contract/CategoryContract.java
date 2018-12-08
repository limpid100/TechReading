package com.dxl.techreading.contract;

import java.util.List;

import com.dxl.techreading.base.BasePresenter;
import com.dxl.techreading.bean.CategoryResult.ResultsBean;

/**
 * @author dxl
 * @date 2018/11/12 13:30
 */
public interface CategoryContract {

    interface ICategoryView {
        void setProgress(boolean show);

        String getCategoryName();

        void setCategoryItems(List<ResultsBean> categoryItems);

        void addCategoryItems(List<ResultsBean> categoryItems);

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
    }

    interface ICategoryPresenter extends BasePresenter {
        void getCategoryItems(boolean refresh);
    }
}