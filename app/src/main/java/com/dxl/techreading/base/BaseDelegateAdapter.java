package com.dxl.techreading.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.dxl.techreading.adapter.BaseViewHolder;

/**
 * Created by dxl on 2018/12/27 19:08
 * @author dxl
 */
public class BaseDelegateAdapter extends DelegateAdapter.Adapter<BaseViewHolder> {

    private Context mContext;
    private int mCount = 0;
    private LayoutHelper mLayoutHelper;
    private int mLayoutID;

    public BaseDelegateAdapter(Context context, LayoutHelper layoutHelper, int count, int layoutId) {
        mContext = context;
        mCount = count;
        mLayoutHelper = layoutHelper;
        mLayoutID = layoutId;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseViewHolder(LayoutInflater.from(mContext).inflate(mLayoutID, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mCount;
    }
}
