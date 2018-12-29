package com.dxl.techreading.model;

import com.dxl.techreading.bean.GankCategoryResult;
import com.dxl.techreading.bean.ImageInfo;
import com.dxl.techreading.inteface.Callback;

import java.util.List;

/**
 * @author du_xi
 * @date 2018/12/12
 */
public interface ICategoryModel extends IModel {

    void getCategoryItems(boolean refresh, String categoryName, Callback<GankCategoryResult, String> callback);

    void getBannerData(Callback<List<ImageInfo>, String> callback);

    void unsubscribe();

}
