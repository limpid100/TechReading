package com.dxl.techreading.model;

import java.util.List;

import com.dxl.techreading.model.CategoryResult.ResultsBean;

/**
 * @author dxl
 * @date 2018/11/12 13:30
 */
public interface CategoryContract {

    interface ICategoryView {
        String getCategoryName();

        void setCategoryItems(List<ResultsBean> categoryItems);

        void addCategoryItems(List<ResultsBean> categoryItems);

        void showErrorMessage(String errorMessage);
    }

    interface ICategoryPresenter extends BasePresenter {
        void getCategoryItems();
    }
}
