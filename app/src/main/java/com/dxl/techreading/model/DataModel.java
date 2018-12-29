package com.dxl.techreading.model;

import com.dxl.techreading.api.Api;
import com.dxl.techreading.bean.ImageInfo;
import com.dxl.techreading.bean.WechatList;
import com.dxl.techreading.inteface.Callback;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dxl on 2018/12/27 21:45
 *
 * @author dxl
 */
public class DataModel implements IDataModel {

    private static Api sApi;
    private Subscription mSubscription;

    @Override
    public List<ImageInfo> getBannerImage() {
        List<ImageInfo> imageInfos = new ArrayList<>();
        imageInfos.add(new ImageInfo("", "http://bpic.wotucdn.com/11/66/23/55bOOOPIC3c_1024.jpg"));
        imageInfos.add(new ImageInfo("", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505470629546&di=194a9a92bfcb7754c5e4d19ff1515355&imgtype=0&src=http%3A%2F%2Fpics.jiancai.com%2Fimgextra%2Fimg01%2F656928666%2Fi1%2FT2_IffXdxaXXXXXXXX_%2521%2521656928666.jpg"));

        return imageInfos;
    }

    @Override
    public void getList(final Callback<WechatList, String> callback) {
        if (sApi == null) {
            sApi = new Retrofit.Builder()
                    .baseUrl("http://wanandroid.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build()
                    .create(Api.class);
        }
        mSubscription = sApi.getWechatList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WechatList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(WechatList wechatList) {
                        callback.onSuccess(wechatList);
                    }
                });
    }

    @Override
    public void unSubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
