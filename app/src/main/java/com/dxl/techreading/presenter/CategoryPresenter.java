package com.dxl.techreading.presenter;

import com.dxl.techreading.bean.CategoryResult;
import com.dxl.techreading.contract.CategoryContract;
import com.dxl.techreading.utils.NetWork;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author dxl
 * @date 2018/11/12 13:37
 */
public class CategoryPresenter implements CategoryContract.ICategoryPresenter {

    private CategoryContract.ICategoryView mICategoryView;
    private Subscription subscription;
    private int mPage = 0;

    public CategoryPresenter(CategoryContract.ICategoryView iCategoryView) {
        mICategoryView = iCategoryView;
    }

    @Override
    public void getCategoryItems(final boolean refresh) {
        if (refresh) {
            mPage = 1;
        }else {
            mPage++;
        }
        subscription = NetWork.getGankApi().getCategoryDate(mICategoryView.getCategoryName(), 10, mPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CategoryResult>() {
                    @Override
                    public void onCompleted() {
                        mICategoryView.setProgress(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mICategoryView.setProgress(false);
                        mICategoryView.showErrorMessage(e.getMessage());
                        if (refresh) {
                            mICategoryView.refreshFinish(false);
                        }else {
                            mICategoryView.loadMoreFinish(false);
                        }
                    }

                    @Override
                    public void onNext(CategoryResult categoryResult) {
                        if (refresh) {
                            mICategoryView.setCategoryItems(categoryResult.getResults());
                            mICategoryView.refreshFinish(true);
                        } else {
                            mICategoryView.addCategoryItems(categoryResult.getResults());
                            mICategoryView.loadMoreFinish(true);
                        }
                    }
                });

    }

    @Override
    public void subscribe() {
        mICategoryView.setProgress(true);
        getCategoryItems(false);
    }

    @Override
    public void unSubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}