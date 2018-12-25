package com.dxl.techreading.adapter;

import android.content.Context;

import com.dxl.techreading.R;
import com.dxl.techreading.customview.ImageInfo;

/**
 * @author dxl
 * @date 2018/12/24 16:21
 */
public class HomeFragmentRecyclerAdapter extends RecyclerArrayAdapter<ImageInfo> {

    public HomeFragmentRecyclerAdapter(Context context) {
        super(context, R.layout.item_view_recycler);
    }

    @Override
    protected void bindData(BaseViewHolder holder, ImageInfo imageInfo) {

    }
}
