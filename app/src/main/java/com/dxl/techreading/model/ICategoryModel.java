package com.dxl.techreading.model;

import com.dxl.techreading.inteface.Callback;

/**
 * @author du_xi
 * @date 2018/12/12
 */
public interface ICategoryModel extends IModel {

    void getCategoryItems(boolean refresh, String categoryName, Callback callback);

    void getBannerData(Callback callback);

    void unsubscribe();

}
