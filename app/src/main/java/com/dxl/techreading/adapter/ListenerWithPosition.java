package com.dxl.techreading.adapter;

import android.view.View;

/**
 * @author du_xi
 * @date 2018/11/13
 */
public class ListenerWithPosition implements View.OnClickListener {

    private int mPosition;
    private Object mHolder;

    public void setClickWithPositionListener(OnClickWithPositionListener clickWithPositionListener) {
        mClickWithPositionListener = clickWithPositionListener;
    }

    private OnClickWithPositionListener mClickWithPositionListener;

    public ListenerWithPosition(Object holder) {
        this.mPosition = -1;
        this.mHolder = holder;
    }

    public ListenerWithPosition(int position, Object holder) {
        this.mPosition = position;
        this.mHolder = holder;
    }

    @Override
    public void onClick(View v) {
        if (mClickWithPositionListener != null) {
            mClickWithPositionListener.onClick(v, mPosition, mHolder);
        }

    }

    public interface OnClickWithPositionListener<T> {
        void onClick(View v, int position, T holder);
    }
}
