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
 * @author dxl
 * @date 2018/12/24 14:49
 */
public abstract class RecyclerArrayAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    /**
     * 数据源
     */
    private List<T> mObjects;
    /**
     * headers和footers
     */
    private ArrayList<ItemView> headers = new ArrayList<>();
    private ArrayList<ItemView> footers = new ArrayList<>();
    /**
     * 主数据源item布局
     */
    private int mLayoutID;
    /**
     * 多布局支持
     */
    protected MuiltipleTypeSupport<T> mTypeSupport;

    private Context mContext;

    public void setData(List<T> objects) {
        mObjects = objects;
    }

    /**
     * 构造方法
     * 如果是多布局，使用这个构造器，adapter继承MuiltipleTypeSupport
     * @param context
     */
    public RecyclerArrayAdapter(Context context) {
        this(context, new ArrayList<T>(), 0);
    }

    public RecyclerArrayAdapter(Context context, int layoutID) {
        this(context, new ArrayList<T>(), layoutID);
    }

    public RecyclerArrayAdapter(Context context, List<T> objects, int layoutID) {
        this.mContext = context;
        this.mObjects = objects;
        this.mLayoutID = layoutID;
    }

    @NonNull
    @Override
    public final BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = createSpViewByType(parent, viewType);
        if (view == null) {
            if (mTypeSupport != null) {
                mLayoutID = mTypeSupport.getLayoutId(viewType);
            }
            view = LayoutInflater.from(mContext).inflate(mLayoutID, parent, false);
        }
        return new BaseViewHolder(view);
    }

    private View createSpViewByType(ViewGroup parent, int viewType) {
        for (ItemView headerView : headers) {
            if (headerView.hashCode() == viewType) {
                return headerView.onCreateView(parent);
            }
        }
        for (ItemView footerView : footers) {
            if (footerView.hashCode() == viewType) {
                return footerView.onCreateView(parent);
            }
        }
        return null;
    }

    public void addHeader(ItemView view) {
        if (view == null) {
            throw new RuntimeException("view can not be null");
        }
        headers.add(view);
        notifyItemInserted(headers.size() - 1);
    }

    public void addFooter(ItemView view) {
        if (view == null) {
            throw new RuntimeException("view can not be null");
        }
        footers.add(view);
        notifyItemInserted(headers.size() + mObjects.size() + footers.size() - 1);
    }

    @Override
    public int getItemViewType(int position) {

        if (headers.size() > 0) {
            if (position < headers.size()) {
                return headers.get(position).hashCode();
            }
        }
        if (footers.size() > 0) {
            int i = position - headers.size() - mObjects.size();
            if (i >= 0) {
                return footers.get(i).hashCode();
            }
        }

        return getViewType(position);
    }

    /**
     * 子类实现，默认为0
     * @param position
     * @return
     */
    protected int getViewType(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        if (headers.size() > 0 && position < headers.size()) {
            headers.get(position).onBindView(holder.itemView);
            return;
        }
        int i = position - headers.size() - mObjects.size();
        if (footers.size() > 0 && i >= 0) {
            footers.get(i).onBindView(holder.itemView);
            return;
        }
        if (mObjects.size() > 0) {
            bindData(holder, mObjects.get(position - headers.size()));
        }
    }

    /**
     * 绑定数据，子类实现
     * @param holder
     * @param t
     */
    protected abstract void bindData(BaseViewHolder holder, T t);

    @Override
    public int getItemCount() {
        return mObjects.size() + headers.size() + footers.size();
    }


    public interface ItemView {
        View onCreateView(ViewGroup parent);

        void onBindView(View headerView);
    }
}
