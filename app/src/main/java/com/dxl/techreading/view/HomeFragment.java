package com.dxl.techreading.view;

import android.os.Bundle;

import com.dxl.techreading.R;
import com.dxl.techreading.base.BaseFragment;
import com.dxl.techreading.customview.CycleViewPager;
import com.dxl.techreading.customview.Info;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author dxl
 * @date 2018/12/21 16:15
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.cycle_view)
    CycleViewPager mCycleViewPager;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    /**
     * 模拟请求后得到的数据
     */
    List<Info> mList = new ArrayList<>();

    /**
     * 初始化数据
     */
    private void initData() {
        mList.add(new Info("标题1", "http://img2.3lian.com/2014/c7/25/d/40.jpg"));
        mList.add(new Info("标题2", "http://img2.3lian.com/2014/c7/25/d/41.jpg"));
        mList.add(new Info("标题3", "http://imgsrc.baidu.com/forum/pic/item/b64543a98226cffc8872e00cb9014a90f603ea30.jpg"));
        mList.add(new Info("标题4", "http://imgsrc.baidu.com/forum/pic/item/261bee0a19d8bc3e6db92913828ba61eaad345d4.jpg"));

    }
    
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {
        initData();
        mCycleViewPager.setData(mList, null);
    }
}
