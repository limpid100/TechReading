package com.dxl.techreading.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dxl.techreading.R;
import com.dxl.techreading.bean.CategoryResult;
import com.dxl.techreading.utils.DateUtil;

import java.util.List;

/**
 * @author dxl
 * @date 2018/12/24 16:21
 */
public class HomeFragmentRecyclerAdapter extends RecyclerArrayAdapter<CategoryResult.ResultsBean> implements ListenerWithPosition.OnClickWithPositionListener {

    private Context mContext;

    public HomeFragmentRecyclerAdapter(Context context) {
        super(context, R.layout.item_view_recycler);
        mContext = context;
    }

    @Override
    protected void bindData(BaseViewHolder holder, CategoryResult.ResultsBean resultsBean) {
        holder.setText(R.id.tv_title, resultsBean.getDesc());
        holder.setText(R.id.tv_source, resultsBean.getSource());
        holder.setText(R.id.tv_who, resultsBean.getWho());
        holder.setText(R.id.tv_date, DateUtil.formatDateString(resultsBean.getPublishedAt()));
        List<String> images = resultsBean.getImages();
        ImageView imageView = holder.getView(R.id.imageView);
        if (images != null && images.size() > 0) {
            Glide.with(mContext)
                    .load(images.get(0))
                    .error(R.drawable.ic_default_image)
                    .placeholder(R.drawable.ic_default_image)
                    .into(imageView);
        }else {
            Glide.with(mContext)
                    .load(R.drawable.ic_default_image)
                    .error(R.drawable.ic_default_image)
                    .into(imageView);
        }
        holder.setOnClickListener(this);
    }

    @Override
    public void onClick(View v, int position, Object holder) {

    }
}
