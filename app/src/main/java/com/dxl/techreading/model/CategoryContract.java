package com.dxl.techreading.model;

import java.util.List;

/**
 * @author dxl
 * @date 2018/11/12 13:30
 */
public interface CategoryContract {

    interface ICategoryView{
        void setCategoryItems(List<CategoryResult> categoryItems);
    }

    interface ICategoryPresenter extends BasePresenter{
        void getCategoryItems();
    }
}
