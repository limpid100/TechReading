package com.dxl.techreading.presenter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.dxl.techreading.R;
import com.dxl.techreading.adapter.BaseViewHolder;
import com.dxl.techreading.base.BaseDelegateAdapter;
import com.dxl.techreading.contract.DataContract;
import com.dxl.techreading.customview.CycleViewPager;
import com.dxl.techreading.model.DataModel;
import com.dxl.techreading.model.IDataModel;
import com.dxl.techreading.view.DataFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dxl on 2018/12/27 18:49
 *
 * @author dxl
 */
public class DataPresenter extends BasePresenter<DataContract.IDataView> implements DataContract.IDataPresenter {

    private IDataModel mDataModel;

    public DataPresenter() {
        mDataModel = new DataModel();
    }

    @Override
    public DelegateAdapter initRecyclerView(RecyclerView recyclerView) {
        if (!isViewAttached()) {
            return null;
        }
        /**
         * 创建VirtualLayoutManager对象
         */
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(((DataFragment) mView).getContext());
        recyclerView.setLayoutManager(virtualLayoutManager);

        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(recycledViewPool);
        recycledViewPool.setMaxRecycledViews(0, 10);

        DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager);
        recyclerView.setAdapter(delegateAdapter);

        return delegateAdapter;
    }

    @Override
    public BaseDelegateAdapter initBannerAdapter() {
        if (!isViewAttached()) {
            return null;
        }
        /**
         设置线性布局
         */
        LinearLayoutHelper bannerLinearLayoutHelper = new LinearLayoutHelper();

        //设置顶部banner
        return new BaseDelegateAdapter(((DataFragment) mView).getContext(), bannerLinearLayoutHelper, 1, R.layout.data_banner) {
            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
                CycleViewPager cycleViewPager = holder.getView(R.id.cycle_view);
                mView.setBannerData(cycleViewPager, mDataModel.getBannerImage());
            }
        };
    }

    @Override
    public BaseDelegateAdapter initGridAdapter() {
        Context context = ((DataFragment) mView).getContext();
        TypedArray proPic = context.getResources().obtainTypedArray(R.array.find_gv_image);
        final String[] proName = context.getResources().getStringArray(R.array.find_gv_title);
        final List<Integer> images = new ArrayList<>();
        for (int i = 0; i < proName.length; i++) {
            images.add(proPic.getResourceId(i, 0));
        }
        proPic.recycle();

        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(4);

        gridLayoutHelper.setPadding(0, 20, 0, 20);

        gridLayoutHelper.setVGap(16);
        gridLayoutHelper.setHGap(0);
        gridLayoutHelper.setBgColor(Color.WHITE);

        return new BaseDelegateAdapter(context, gridLayoutHelper, 8, R.layout.item_vp_grid_iv) {
            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, final int position) {
                holder.setText(R.id.tv_new_seed_title, proName[position]);
                ((ImageView) holder.getView(R.id.iv_new_seed_ic)).setImageResource(images.get(position));
                holder.getView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mView.onGridItemClick(position, proName[position]);
                    }
                });
            }
        };

    }
}
