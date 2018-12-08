package com.dxl.techreading.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.dxl.techreading.presenter.IPresenter;
import com.dxl.techreading.view.IView;

import butterknife.ButterKnife;

/**
 * @author du_xi
 * @date 2018/11/7
 */
public abstract class BaseActivity<T extends IPresenter> extends AppCompatActivity implements IView {

    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeInit();
        initPresenter();
        setContentView(getContentViewLayoutID());
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 创建presenter
     */
    private void initPresenter() {
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    protected T createPresenter(){
        return null;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }


    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }


}
