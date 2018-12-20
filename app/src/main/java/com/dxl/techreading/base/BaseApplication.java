package com.dxl.techreading.base;

import android.app.Application;

/**
 * Created by dxl on 2018/12/20 19:27
 *
 * @author dxl
 */
public class BaseApplication extends Application {

    private static BaseApplication sApplication;

    public static synchronized BaseApplication getApplication() {
        if (sApplication == null) {
            sApplication = new BaseApplication();
        }
        return sApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }
}
