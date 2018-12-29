package com.dxl.techreading.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.dxl.techreading.adapter.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dxl on 2018/12/27 19:08
 * @author dxl
 */
public class BaseDelegateAdapter<T> extends DelegateAdapter.Adapter<BaseViewHolder> {

    private Context mContext;
    private int mCount = 0;
    private LayoutHelper mLayoutHelper;
    private int mLayoutID;
    private int mViewType = -1;

    public void setDataList(List<T> dataList) {
        mDataList = dataList;
        notifyDataSetChanged();
    }

    public List<T> getDataList() {
        return mDataList;
    }

    private List<T> mDataList;

    public BaseDelegateAdapter(Context context, LayoutHelper layoutHelper, int count, int layoutId, int viewType) {
        mContext = context;
        mCount = count;
        mLayoutHelper = layoutHelper;
        mLayoutID = layoutId;
        mDataList = new ArrayList<>();
        mViewType = viewType;
    }



    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == mViewType) {
            return new BaseViewHolder(LayoutInflater.from(mContext).inflate(mLayoutID, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return mViewType;
    }

    @Override
    public int getItemCount() {
        int size = mDataList.size();
        if (size > 0) {
            return size;
        }
        return mCount;
    }
}
