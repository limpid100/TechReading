package com.dxl.techreading.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;

import com.dxl.techreading.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author du_xi
 * @date 2018-11-7 19:49:25
 */
public class SplashActivity extends BaseActivity {

    @BindView(R.id.btn_skip)
    Button btnSkip;

    CountDownTimer timer;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_splash;
    }


    @Override
    protected void initView() {
        timer = new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long s = millisUntilFinished / 1000;
                btnSkip.setText(s == 0 ? "跳过" : "跳过 " + s);
            }

            @Override
            public void onFinish() {
                toMainActivity();
            }
        }.start();
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

}
