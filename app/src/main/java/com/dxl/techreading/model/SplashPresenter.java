package com.dxl.techreading.model;

import com.dxl.techreading.model.SplashConstract.ISplashView;

import java.util.List;
import java.util.Random;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author du_xi
 * @date 2018/11/14
 */
public class SplashPresenter implements SplashConstract.ISplashPresenter {

    private ISplashView mSplashView;
    private static GankApi sGankApi;

    public SplashPresenter(ISplashView splashView) {
        mSplashView = splashView;
    }

    private static final String BASE_URL = "https://cn.bing.com";

    //https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=zh-CN

    @Override
    public void subscribe() {
        if (sGankApi == null) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://cn.bing.com/")
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            sGankApi = retrofit.create(GankApi.class);
        }
        sGankApi.getBingDailyPic("js", 0, 8, "zh-CN")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BingDailyPic>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mSplashView.failLoadPic(e.getMessage());
                    }

                    @Override
                    public void onNext(BingDailyPic bingDailyPic) {
                        List<BingDailyPic.Images> images = bingDailyPic.getImages();
                        if (images != null && images.size() > 0) {
                            int i = new Random().nextInt(images.size());
                            mSplashView.showBingPic(BASE_URL + images.get(i).getUrl());
                        }
                    }
                });
    }

    @Override
    public void unSubscribe() {

    }
}
