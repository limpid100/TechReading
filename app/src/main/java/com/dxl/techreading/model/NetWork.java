package com.dxl.techreading.model;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author dxl
 * @date 2018/11/12 14:47
 */
public class NetWork {
    private static GankApi sGankApi;

    public static GankApi getGankApi(){
        if (sGankApi == null) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://gank.io/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            sGankApi = retrofit.create(GankApi.class);
        }
        return sGankApi;
    }
}
