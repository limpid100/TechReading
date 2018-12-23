package com.dxl.techreading.adapter;

/**
 * recyclerview 多种type
 *
 * @author dxl
 * @date 2018/12/23 11:11
 */
public interface MuiltipleTypeSupport<T> {
    int getLayoutId(int itemType);

    int getItemViewType(int position, T t);
}
