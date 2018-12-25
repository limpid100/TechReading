package com.dxl.techreading.model;

import com.dxl.techreading.api.Api;
import com.dxl.techreading.bean.Banner;
import com.dxl.techreading.bean.GankCategoryResult;
import com.dxl.techreading.bean.ImageInfo;
import com.dxl.techreading.inteface.Callback;
import com.dxl.techreading.utils.NetWork;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author du_xi
 * @date 2018/12/12
 */
public class CategoryModel implements ICategoryModel {

    private int mPage = 0;
    private Subscription mSubscription;

    private static Api sApi;

    @Override
    public void getCategoryItems(boolean refresh, String categoryName, final Callback callback) {
        if (refresh) {
            mPage = 1;
        } else {
            mPage++;
        }
        mSubscription = NetWork.getApi().getCategoryDate(categoryName, 10, mPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GankCategoryResult>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e.toString());
                    }

                    @Override
                    public void onNext(GankCategoryResult gankCategoryResult) {
                        callback.onSuccess(gankCategoryResult);
                    }
                });
    }

    @Override
    public void getBannerData(final Callback callback) {
        if (sApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.wanandroid.com/")
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            sApi = retrofit.create(Api.class);
        }
        mSubscription = sApi.getBannerData()
                .map(new Func1<Banner, List<ImageInfo>>() {
                    @Override
                    public List<ImageInfo> call(Banner banner) {
                        List<ImageInfo> infoList = new ArrayList<>();
                        for (int i = 0; i < banner.getData().size(); i++) {
                            Banner.Data data = banner.getData().get(i);
                            infoList.add(new ImageInfo(data.getTitle(), data.getImagePath()));
                        }
                        return infoList;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ImageInfo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(List<ImageInfo> banner) {
                        callback.onSuccess(banner);
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
