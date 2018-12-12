package com.dxl.techreading.model;

import com.dxl.techreading.bean.CategoryResult;
import com.dxl.techreading.inteface.Callback;
import com.dxl.techreading.utils.NetWork;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author du_xi
 * @date 2018/12/12
 */
public class CategoryModel implements ICategoryModel {

    private int mPage = 0;
    private Subscription mSubscription;

    @Override
    public void getCategoryItems(boolean refresh, String categoryName, final Callback callback) {
        if (refresh) {
            mPage = 1;
        } else {
            mPage++;
        }
        mSubscription = NetWork.getGankApi().getCategoryDate(categoryName, 10, mPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CategoryResult>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(CategoryResult categoryResult) {
                        callback.onSuccess(categoryResult);
                    }
                });
    }

    @Override
    public void unsubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
