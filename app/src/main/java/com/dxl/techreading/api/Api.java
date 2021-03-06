package com.dxl.techreading.api;

import com.dxl.techreading.bean.Banner;
import com.dxl.techreading.bean.BingDailyPic;
import com.dxl.techreading.bean.GankCategoryResult;
import com.dxl.techreading.bean.WechatList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author dxl
 * @date 2018/11/12 14:38
 */
public interface Api {
    /**
     *
     * @param type
     * @param count
     * @param page
     * @return
     */
    @GET("data/{type}/{count}/{page}")
    Observable<GankCategoryResult> getCategoryDate(@Path("type") String type, @Path("count") int count, @Path("page") int page);

    /**
     * 获取必应每日图片
     * https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=zh-CN
     */
    @GET("HPImageArchive.aspx")
    Observable<BingDailyPic> getBingDailyPic(@Query("format") String format, @Query("idx") int idx, @Query("n") int n, @Query("mkt") String mkt);

    @GET("banner/json")
    Observable<Banner> getBannerData();

    /**
     * http://wanandroid.com/wxarticle/chapters/json
     */
    @GET("wxarticle/chapters/json")
    Observable<WechatList> getWechatList();

}
