package com.dxl.techreading.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.dxl.techreading.R;
import com.dxl.techreading.base.BaseDelegateAdapter;
import com.dxl.techreading.base.BaseFragment;
import com.dxl.techreading.bean.ImageInfo;
import com.dxl.techreading.bean.WechatList;
import com.dxl.techreading.contract.DataContract;
import com.dxl.techreading.customview.CycleViewPager;
import com.dxl.techreading.presenter.DataPresenter;

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

    DelegateAdapter delegateAdapter;

    BaseDelegateAdapter<WechatList.Data> wechatListAdapter;

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
        delegateAdapter = mPresenter.initRecyclerView(mRecyclerView);

        /**
         * banner
         */
        BaseDelegateAdapter bannerAdapter = mPresenter.initBannerAdapter();
        delegateAdapter.addAdapter(bannerAdapter);
        /**
         * grid
         */
        BaseDelegateAdapter gridAdapter = mPresenter.initGridAdapter();
        delegateAdapter.addAdapter(gridAdapter);

        /**
         * douban share
         */
        BaseDelegateAdapter doubanAdapter = mPresenter.initTitleAdapter("豆瓣分享");
        delegateAdapter.addAdapter(doubanAdapter);
        /**
         * douban 3 grid
         */
        BaseDelegateAdapter douban3GridAdapter = mPresenter.initList3Adapter();
        delegateAdapter.addAdapter(douban3GridAdapter);

        BaseDelegateAdapter weichatAdapter = mPresenter.initTitleAdapter("微信公众号");
        delegateAdapter.addAdapter(weichatAdapter);
        /**
         * list
         */
        wechatListAdapter = mPresenter.initWechatListAdapter();
        delegateAdapter.addAdapter(wechatListAdapter);

        mPresenter.getList();


    }

    /**
     * 设置首页banner数据
     * @param cycleViewPager
     * @param imageInfos
     */
    @Override
    public void setBannerData(CycleViewPager cycleViewPager, List<ImageInfo> imageInfos) {
        cycleViewPager.setData(imageInfos, null, false);
    }

    /**
     * 九宫格item点击
     * @param position
     * @param itemName
     */
    @Override
    public void onGridItemClick(int position, String itemName) {
        Toast.makeText(mContext, itemName, Toast.LENGTH_SHORT).show();
    }

    /**
     * 点击查看更多
     * @param title
     */
    @Override
    public void onMoreClicked(String title) {
        Toast.makeText(mContext, title, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setList(WechatList wechatList) {
        wechatListAdapter.setDataList(wechatList.getData());
    }

    @Override
    public void onListError(String errorMessage) {
        Toast.makeText(mContext, errorMessage, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected DataPresenter createPresenter() {
        return new DataPresenter();
    }
}
