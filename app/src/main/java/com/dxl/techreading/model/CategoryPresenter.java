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
    public void getCategoryItems(final boolean refresh) {
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
                        if (refresh) {
                            mICategoryView.refreshFinish(false);
                        }
                    }

                    @Override
                    public void onNext(CategoryResult categoryResult) {
                        if (refresh) {
                            mICategoryView.setCategoryItems(categoryResult.getResults());
                            mICategoryView.refreshFinish(true);
                        } else {
                            mICategoryView.addCategoryItems(categoryResult.getResults());
                        }
                    }
                });

    }

    @Override
    public void subscribe() {
        getCategoryItems(false);
    }

    @Override
    public void unSubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
