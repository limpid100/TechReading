package com.dxl.techreading.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dxl.techreading.R;
import com.dxl.techreading.model.SplashConstract;
import com.dxl.techreading.model.SplashPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 闪屏页，先展示默认图片，然后在线去随机获取必应每日图片，并开启倒计时
 *
 * @author du_xi
 * @date 2018-11-7 19:49:25
 */
public class SplashActivity extends BaseActivity implements SplashConstract.ISplashView {

    @BindView(R.id.btn_skip)
    Button btnSkip;
    @BindView(R.id.iv_splash)
    ImageView mSplashImageView;

    CountDownTimer timer;

    private SplashConstract.ISplashPresenter mSplashPresenter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_splash;
    }


    @Override
    protected void initView() {
        mSplashPresenter = new SplashPresenter(this);
        timer = new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long s = Math.round(millisUntilFinished / 1000.0);
                if (btnSkip.getVisibility() != View.VISIBLE) {
                    btnSkip.setVisibility(View.VISIBLE);
                }
                btnSkip.setText("跳过 " + s);
            }

            @Override
            public void onFinish() {
                toMainActivity();
            }
        };
        mSplashPresenter.subscribe();
    }

    boolean isIn = false;

    @OnClick(R.id.btn_skip)
    void toMainActivity() {
        timer.cancel();
        if (isIn) {
            return;
        }
        isIn = true;
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    @Override
    public void showBingPic(String url) {
        Glide.with(this).load(url).centerCrop().placeholder(R.mipmap.splash_default).error(R.mipmap.splash_default).into(mSplashImageView);
        timer.start();
    }

    @Override
    public void failLoadPic(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        timer.start();
    }
}
