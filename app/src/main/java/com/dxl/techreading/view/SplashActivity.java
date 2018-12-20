package com.dxl.techreading.view;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dxl.techreading.R;
import com.dxl.techreading.base.BaseActivity;
import com.dxl.techreading.contract.SplashConstract;
import com.dxl.techreading.presenter.SplashPresenter;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * 闪屏页，先展示默认图片，然后在线去随机获取必应每日图片，并开启倒计时
 *
 * @author du_xi
 * @date 2018-11-7 19:49:25
 */
public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashConstract.ISplashView {

    @BindView(R.id.btn_skip)
    Button btnSkip;
    @BindView(R.id.iv_splash)
    ImageView mSplashImageView;

    //倒计时3秒
    public static final int COUNT_DOWN = 3;

    @Override
    protected SplashPresenter createPresenter() {
        return new SplashPresenter();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_splash;
    }


    @Override
    protected void initView() {
        mPresenter.getImageUrl();
        //倒计时，也可以用CountDownTimer
        mSubscription = Observable.interval(1, TimeUnit.SECONDS).take(COUNT_DOWN)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return COUNT_DOWN - aLong;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        toMainActivity();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        if (btnSkip.getVisibility() != View.VISIBLE) {
                            btnSkip.setVisibility(View.VISIBLE);
                        }
                        btnSkip.setText("跳过 " + aLong);
                    }
                });
    }

    boolean isIn = false;

    @OnClick(R.id.btn_skip)
    void toMainActivity() {
        if (isIn) {
            return;
        }
        isIn = true;
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    @Override
    public void showBingPic(String url) {
        Glide.with(this).load(url).centerCrop().placeholder(R.mipmap.splash_default).error(R.mipmap.splash_default).into(mSplashImageView);
    }

    @Override
    public void failLoadPic(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
