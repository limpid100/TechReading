package com.dxl.techreading.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.dxl.techreading.base.BaseApplication;

/**
 * Created by dxl on 2018/12/20 19:21
 */
public final class SPUtils {

    private static SharedPreferences mPreferences;

    private static SharedPreferences getInstance(){
        if (mPreferences == null) {
            mPreferences = BaseApplication.getApplication().getSharedPreferences("spUtils", Context.MODE_PRIVATE);
        }
        return mPreferences;
    }

    public static void put(String key, String value) {
        SPUtils.getInstance().edit().putString(key, value).apply();
    }

    public static void put(String key, boolean value){
        SPUtils.getInstance().edit().putBoolean(key, value).apply();
    }

    public static void put(String key, int value){
        SPUtils.getInstance().edit().putInt(key, value).apply();
    }

    public static String getString(String key, String defaultValue) {
        return getInstance().getString(key, defaultValue);
    }

    public static int getInt(String key, int defaultValue) {
        return getInstance().getInt(key, defaultValue);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return getInstance().getBoolean(key, defaultValue);
    }


}
