package com.dxl.techreading.presenter;

import com.dxl.techreading.bean.CategoryResult;
import com.dxl.techreading.contract.CategoryContract;
import com.dxl.techreading.customview.ImageInfo;
import com.dxl.techreading.inteface.Callback;
import com.dxl.techreading.model.CategoryModel;
import com.dxl.techreading.model.ICategoryModel;

import java.util.List;

/**
 * @author dxl
 * @date 2018/11/12 13:37
 */
public class CategoryPresenter extends BasePresenter<CategoryContract.ICategoryView> implements CategoryContract.ICategoryPresenter {

    private ICategoryModel mCategoryModel;

    public CategoryPresenter() {
        mCategoryModel = new CategoryModel();
    }

    @Override
    public void getCategoryItems(final boolean refresh) {
        mCategoryModel.getCategoryItems(refresh, mView.getCategoryName(), new Callback<CategoryResult, String>(){

            @Override
            public void onSuccess(CategoryResult categoryResult) {
                mView.setProgress(false);
                if (refresh) {
                    mView.setCategoryItems(categoryResult.getResults());
                    mView.refreshFinish(true);
                } else {
                    mView.addCategoryItems(categoryResult.getResults());
                    mView.loadMoreFinish(true);
                }
            }

            @Override
            public void onFailure(String message) {
                mView.setProgress(false);
                mView.showErrorMessage(message);
                if (refresh) {
                    mView.refreshFinish(false);
                }else {
                    mView.loadMoreFinish(false);
                }
            }
        });

    }

    @Override
    public void getBannerData() {
        mCategoryModel.getBannerData(new Callback<List<ImageInfo>, String>() {
            @Override
            public void onSuccess(List<ImageInfo> data) {
                mView.setBanner(data);
            }

            @Override
            public void onFailure(String message) {
                mView.showErrorMessage(message);
            }

        });
    }

    @Override
    public void detachView() {
        super.detachView();
        mCategoryModel.unsubscribe();
    }
}
