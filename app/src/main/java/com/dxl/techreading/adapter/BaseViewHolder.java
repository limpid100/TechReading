package com.dxl.techreading.adapter;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * @author dxl
 * @date 2018/12/24 14:49
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;

    public BaseViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public void setText(@IdRes int viewId, String text) {
        View view = getView(viewId);
        if (view instanceof TextView) {
            ((TextView) view).setText(text);
        }
    }

    public void setOnClickListener(ListenerWithPosition.OnClickWithPositionListener clickListener, @IdRes int... viewIds) {
        ListenerWithPosition listenerWithPosition = new ListenerWithPosition(this);
        listenerWithPosition.setClickWithPositionListener(clickListener);
        for (int viewId : viewIds) {
            getView(viewId).setOnClickListener(listenerWithPosition);
        }
    }
    public void setOnClickListener(ListenerWithPosition.OnClickWithPositionListener clickListener) {
        ListenerWithPosition listenerWithPosition = new ListenerWithPosition(this);
        listenerWithPosition.setClickWithPositionListener(clickListener);
        itemView.setOnClickListener(listenerWithPosition);
    }


}
