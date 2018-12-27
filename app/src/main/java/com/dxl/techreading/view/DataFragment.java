package com.dxl.techreading.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.dxl.techreading.R;
import com.dxl.techreading.adapter.BaseViewHolder;
import com.dxl.techreading.base.BaseDelegateAdapter;
import com.dxl.techreading.base.BaseFragment;
import com.dxl.techreading.bean.ImageInfo;
import com.dxl.techreading.contract.DataContract;
import com.dxl.techreading.customview.CycleViewPager;
import com.dxl.techreading.presenter.DataPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 第二个tab
 * Created by dxl on 2018/12/27 18:44
 *
 * @author dxl
 */
public class DataFragment extends BaseFragment<DataPresenter> implements DataContract.IDataView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_data;
    }

    @Override
    protected void init() {
        initRecyclerView();
    }

    public static DataFragment newInstance() {
        
        Bundle args = new Bundle();
        
        DataFragment fragment = new DataFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initRecyclerView() {
        /**
         * 创建VirtualLayoutManager对象
         */
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(mContext);
        mRecyclerView.setLayoutManager(virtualLayoutManager);

        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        mRecyclerView.setRecycledViewPool(recycledViewPool);
        recycledViewPool.setMaxRecycledViews(0, 10);

        DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager);
        mRecyclerView.setAdapter(delegateAdapter);


        /**
         设置线性布局
         */
        LinearLayoutHelper bannerLinearLayoutHelper = new LinearLayoutHelper();

        //设置顶部banner
        BaseDelegateAdapter bannerAdapter = new BaseDelegateAdapter(mContext, bannerLinearLayoutHelper, 1, R.layout.data_banner) {
            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
                List<ImageInfo> imageInfos  = new ArrayList<>();
                imageInfos.add(new ImageInfo("", "http://bpic.wotucdn.com/11/66/23/55bOOOPIC3c_1024.jpg"));
                imageInfos.add(new ImageInfo("", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505470629546&di=194a9a92bfcb7754c5e4d19ff1515355&imgtype=0&src=http%3A%2F%2Fpics.jiancai.com%2Fimgextra%2Fimg01%2F656928666%2Fi1%2FT2_IffXdxaXXXXXXXX_%2521%2521656928666.jpg"));
                CycleViewPager cycleViewPager = holder.getView(R.id.cycle_view);
                cycleViewPager.setData(imageInfos, null, false);
            }
        };
        
        delegateAdapter.addAdapter(bannerAdapter);


    }
}
