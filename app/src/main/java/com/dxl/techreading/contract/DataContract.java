package com.dxl.techreading.contract;

import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.dxl.techreading.base.BaseDelegateAdapter;
import com.dxl.techreading.bean.ImageInfo;
import com.dxl.techreading.customview.CycleViewPager;
import com.dxl.techreading.view.IView;

import java.util.List;

/**
 * Created by dxl on 2018/12/27 18:46
 */
public interface DataContract {

    interface IDataView extends IView {
        void setBannerData(CycleViewPager cycleViewPager, List<ImageInfo> imageInfos);

        void onGridItemClick(int position, String itemName);
    }

    interface IDataPresenter {

        DelegateAdapter initRecyclerView(RecyclerView recyclerView);

        BaseDelegateAdapter initBannerAdapter();

        BaseDelegateAdapter initGridAdapter();
    }
}
