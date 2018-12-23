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
public abstract class CommonRecyclerViewAdapter<T> extends RecyclerView.Adapter<CommonRecyclerViewHolder> {

    private int mLayoutID;
    private Context mContext;
    List<T> mDatas;
    protected MuiltipleTypeSupport<T> mTypeSupport;

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
    public CommonRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mTypeSupport != null) {
            mLayoutID = mTypeSupport.getLayoutId(viewType);
        }
        View view = LayoutInflater.from(mContext).inflate(mLayoutID, parent, false);
        return new CommonRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommonRecyclerViewHolder holder, int position) {
        holder.setPosition(position);
        convert(holder, mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mTypeSupport != null) {
            return mTypeSupport.getItemViewType(position, mDatas.get(position));
        }
        return super.getItemViewType(position);
    }

    protected abstract void convert(CommonRecyclerViewHolder holder, T t);


}
