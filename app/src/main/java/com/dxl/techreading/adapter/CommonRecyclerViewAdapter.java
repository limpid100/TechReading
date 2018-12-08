package com.dxl.techreading.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author du_xi
 * @date 2018/11/12
 */
public abstract class CommonRecyclerViewAdapter<T> extends RecyclerView.Adapter {

    private int mLayoutID;
    Context mContext;
    protected List<T> mDatas;

    public void setDatas(List<T> datas) {
        mDatas.clear();
        addDatas(datas);
    }

    public void addDatas(List<T> datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }


    public CommonRecyclerViewAdapter(Context context, int layoutId) {
        this.mLayoutID = layoutId;
        this.mContext = context;
        this.mDatas = new ArrayList<>();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mLayoutID, parent, false);
        return new CommonRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CommonRecyclerViewHolder) {
            CommonRecyclerViewHolder commonRecyclerViewHolder = (CommonRecyclerViewHolder) holder;
            commonRecyclerViewHolder.setPosition(position);
            convert(commonRecyclerViewHolder, mDatas.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    protected abstract void convert(CommonRecyclerViewHolder holder, T t);
}
