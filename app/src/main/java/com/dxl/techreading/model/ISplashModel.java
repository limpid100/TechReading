package com.dxl.techreading.model;

import com.dxl.techreading.inteface.Callback;

/**
 * @author du_xi
 * @date 2018/12/8
 */
public interface ISplashModel extends IModel {
    void getImageUrl(Callback<String, String> callback);
}
