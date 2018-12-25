package com.dxl.techreading.utils;

import com.dxl.techreading.api.Api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author dxl
 * @date 2018/11/12 14:47
 */
public class NetWork {
    private static Api sApi;

    public static Api getApi(){
        if (sApi == null) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://gank.io/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            sApi = retrofit.create(Api.class);
        }
        return sApi;
    }
}
