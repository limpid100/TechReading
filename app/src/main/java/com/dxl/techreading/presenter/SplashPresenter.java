package com.dxl.techreading.presenter;

import com.dxl.techreading.contract.SplashConstract;
import com.dxl.techreading.contract.SplashConstract.ISplashView;
import com.dxl.techreading.inteface.Callback;
import com.dxl.techreading.model.ISplashModel;
import com.dxl.techreading.model.SplashModel;

/**
 * @author du_xi
 * @date 2018/11/14
 */
public class SplashPresenter extends BasePresenter<ISplashView> implements SplashConstract.ISplashPresenter {

    private ISplashModel mSplashModel;

    public SplashPresenter() {
        mSplashModel = new SplashModel();
    }

    @Override
    public void getImageUrl() {
        mSplashModel.getImageUrl(new Callback<String, String>() {
            @Override
            public void onSuccess(String data) {
                mView.showBingPic(data);
            }

            @Override
            public void onFailure(String message) {
                mView.failLoadPic(message);
            }
        });
    }
}
