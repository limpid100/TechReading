package com.dxl.techreading.model;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author dxl
 * @date 2018/11/12 14:38
 */
public interface GankApi {
    /**
     *
     * @param type
     * @param count
     * @param page
     * @return
     */
    @GET("data/{type}/{count}/{page}")
    Observable<CategoryResult> getCategoryDate(@Path("type") String type, @Path("count") int count, @Path("page") int page);
}
