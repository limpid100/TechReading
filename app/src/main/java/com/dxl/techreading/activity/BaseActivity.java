package com.dxl.techreading.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * @author du_xi
 * @date 2018/11/7
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeInit();
        setContentView(getContentViewLayoutID());
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 界面初始化前工作
     */
    protected void beforeInit() {

    }

    /**
     * 布局ID
     * @return .
     */
    protected abstract int getContentViewLayoutID();

    /**
     * 界面初始化
     */
    protected void initView(){

    }
}
