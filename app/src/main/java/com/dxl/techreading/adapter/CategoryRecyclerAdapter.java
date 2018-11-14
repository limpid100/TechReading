package com.dxl.techreading.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dxl.techreading.R;
import com.dxl.techreading.activity.WebViewActivity;
import com.dxl.techreading.model.CategoryResult.ResultsBean;
import com.dxl.techreading.model.CommonRecyclerViewHolder;
import com.dxl.techreading.util.DateUtil;

import java.util.List;

/**
 * @author dxl
 * @date 2018/11/9 13:41
 */
public class CategoryRecyclerAdapter
        extends CommonRecyclerViewAdapter<ResultsBean>
        implements ListenerWithPosition.OnClickWithPositionListener<CommonRecyclerViewHolder> {

    private Context mContext;

    public CategoryRecyclerAdapter(Context context) {
        super(context, R.layout.item_view_recycler);
        mContext = context;
    }

    @Override
    protected void convert(CommonRecyclerViewHolder holder, ResultsBean resultsBean) {
        holder.setTextViewText(R.id.tv_title, resultsBean.getDesc());
        holder.setTextViewText(R.id.tv_source, resultsBean.getSource());
        holder.setTextViewText(R.id.tv_who, resultsBean.getWho());
        holder.setTextViewText(R.id.tv_date, DateUtil.formatDateString(resultsBean.getPublishedAt()));
        List<String> images = resultsBean.getImages();
        ImageView imageView = holder.getView(R.id.imageView);
        if (images != null && images.size() > 0) {
            Glide.with(mContext)
                    .load(images.get(0))
                    .error(R.drawable.ic_default_image)
                    .into(imageView);
        }else {
            Glide.with(mContext)
                    .load(R.drawable.ic_default_image)
                    .error(R.drawable.ic_default_image)
                    .into(imageView);
        }
        holder.setOnClickListener(this, R.id.item_view);
    }

    @Override
    public void onClick(View v, int position, CommonRecyclerViewHolder holder) {
        ResultsBean resultsBean = mDatas.get(position);
        WebViewActivity.start(mContext, resultsBean.getUrl(), resultsBean.getDesc());
    }
}
