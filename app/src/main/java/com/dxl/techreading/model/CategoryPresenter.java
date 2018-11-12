package com.dxl.techreading.model;

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

    public CategoryPresenter(CategoryContract.ICategoryView iCategoryView) {
        mICategoryView = iCategoryView;
    }

    @Override
    public void getCategoryItems() {
        subscription = NetWork.getGankApi().getCategoryDate(mICategoryView.getCategoryName(), 10, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CategoryResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mICategoryView.showErrorMessage(e.getMessage());
                    }

                    @Override
                    public void onNext(CategoryResult categoryResult) {
                        mICategoryView.addCategoryItems(categoryResult.getResults());
                    }
                });

    }

    @Override
    public void subscribe() {
        getCategoryItems();
    }

    @Override
    public void unSubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
