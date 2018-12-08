package com.dxl.techreading.inteface;

/**
 * model回调
 *
 * @author du_xi
 * @date 2018/12/8
 */
public interface Callback<K, V> {

    void onSuccess(K data);

    void onFailure(V message);
}
