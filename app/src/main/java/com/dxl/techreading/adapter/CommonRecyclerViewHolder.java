package com.dxl.techreading.adapter;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.dxl.techreading.adapter.ListenerWithPosition;

/**
 * @author du_xi
 * @date 2018/11/12
 */
public class CommonRecyclerViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;
    private View convertView;

    public void setPosition(int position) {
        mPosition = position;
    }

    private int mPosition;

    public CommonRecyclerViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
        this.convertView = itemView;
    }

    public <T extends View> T getView(@IdRes int viewId){
        View view = mViews.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public void setTextViewText(@IdRes int textViewID, String text) {
        View view = getView(textViewID);
        if (view instanceof TextView) {
            ((TextView) view).setText(text);
        }
    }

    public void setOnClickListener(ListenerWithPosition.OnClickWithPositionListener clickListener, @IdRes int... viewIds){
        ListenerWithPosition listenerWithPosition = new ListenerWithPosition(mPosition, this);
        listenerWithPosition.setClickWithPositionListener(clickListener);
        for (int viewId : viewIds) {
            getView(viewId).setOnClickListener(listenerWithPosition);
        }
    }




}
