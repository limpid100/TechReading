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
import com.bumptech.glide.Glide;
import com.dxl.techreading.R;
import com.dxl.techreading.adapter.BaseViewHolder;
import com.dxl.techreading.base.BaseDelegateAdapter;
import com.dxl.techreading.bean.WechatList;
import com.dxl.techreading.contract.DataContract;
import com.dxl.techreading.customview.CycleViewPager;
import com.dxl.techreading.inteface.Callback;
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

    class ViewType {
        public final static int BANNER_TYPE = 0;
        public final static int GRID_TYPE = 1;
        public final static int DOUBAN_GRID = 3;
        public final static int ITEM_TITLE = 4;
        public final static int WECHAT_LIST = 5;

    }

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
        recycledViewPool.setMaxRecycledViews(0, 20);

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
        return new BaseDelegateAdapter(((DataFragment) mView).getContext(), bannerLinearLayoutHelper, 1, R.layout.data_banner, ViewType.BANNER_TYPE) {
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
        gridLayoutHelper.setMargin(0, 0, 0, 10);

        gridLayoutHelper.setVGap(16);
        gridLayoutHelper.setHGap(0);
        gridLayoutHelper.setBgColor(Color.WHITE);

        return new BaseDelegateAdapter(context, gridLayoutHelper, 8, R.layout.item_vp_grid_iv, ViewType.GRID_TYPE) {
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

    @Override
    public BaseDelegateAdapter initTitleAdapter(final String title) {
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        linearLayoutHelper.setBgColor(Color.WHITE);

        return new BaseDelegateAdapter(((DataFragment) mView).getContext(), linearLayoutHelper, 1, R.layout.base_view_title, ViewType.ITEM_TITLE) {
            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
                holder.setText(R.id.tv_title, title);
                holder.getView(R.id.tv_more).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mView.onMoreClicked(title);
                    }
                });
            }
        };
    }

    @Override
    public BaseDelegateAdapter initList3Adapter() {
        final Context context = ((DataFragment) mView).getContext();
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
        gridLayoutHelper.setWeights(new float[]{30, 40, 30});
        gridLayoutHelper.setBgColor(Color.WHITE);
        gridLayoutHelper.setMargin(0, 0, 0, 10);
        gridLayoutHelper.setPadding(10, 10, 10, 10);
        gridLayoutHelper.setHGap(5);
        final String[] titles = context.getResources().getStringArray(R.array.find_list3_title);
        TypedArray typedArray = context.getResources().obtainTypedArray(R.array.find_list3_image);
        final List<Integer> images = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            int resourceId = typedArray.getResourceId(i, 0);
            images.add(resourceId);
        }
        typedArray.recycle();

        return new BaseDelegateAdapter(context, gridLayoutHelper, 3, R.layout.base_btn_title_view, ViewType.DOUBAN_GRID) {
            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
                final int positionFinal = position;
                holder.setText(R.id.tv_title, titles[position]);
                Glide.with(context).load(images.get(position)).into(((ImageView) holder.getView(R.id.image)));
                holder.getView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mView.onMoreClicked(titles[positionFinal]);
                    }
                });
            }
        };
    }

    @Override
    public void getList() {
        mDataModel.getList(new Callback<WechatList, String>() {
            @Override
            public void onSuccess(WechatList data) {
                mView.setList(data);
            }

            @Override
            public void onFailure(String message) {
                mView.onListError(message);
            }
        });
    }

    @Override
    public BaseDelegateAdapter<WechatList.Data> initWechatListAdapter() {
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        linearLayoutHelper.setBgColor(Color.WHITE);
        return new BaseDelegateAdapter<WechatList.Data>(((DataFragment) mView).getContext(), linearLayoutHelper, 0, R.layout.wechat_list_item, ViewType.WECHAT_LIST) {
            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, final int position) {
                holder.setText(R.id.tv_title, getDataList().get(position).getName());
                holder.getView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mView.onMoreClicked(getDataList().get(position).getName());
                    }
                });
            }
        };
    }

    @Override
    public void detachView() {
        super.detachView();
        mDataModel.unSubscribe();
    }
}
