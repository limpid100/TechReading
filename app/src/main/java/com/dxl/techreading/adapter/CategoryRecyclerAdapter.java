package com.dxl.techreading.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dxl.techreading.R;
import com.dxl.techreading.model.CategoryResult;
import com.dxl.techreading.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dxl
 * @date 2018/11/9 13:41
 */
public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryViewHolder> {

    public void setCategoryResults(List<CategoryResult> categoryResults) {
        mCategoryResults = categoryResults;
        notifyDataSetChanged();
    }

    private List<CategoryResult> mCategoryResults = new ArrayList<>();

    private Context mContext;

    public CategoryRecyclerAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_view_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryResult categoryResult = mCategoryResults.get(position);
        holder.tvTitle.setText(categoryResult.getDesc());
        holder.tvSource.setText(categoryResult.getSource());
        holder.tvWho.setText(categoryResult.getWho());
        holder.tvDate.setText(DateUtil.formatDateString(categoryResult.getPublishedAt()));
    }

    @Override
    public int getItemCount() {
        return mCategoryResults.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvWho, tvSource, tvDate;
        ImageView imageView;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvWho = itemView.findViewById(R.id.tv_who);
            tvSource = itemView.findViewById(R.id.tv_source);
            tvDate = itemView.findViewById(R.id.tv_date);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
