package com.dxl.techreading.activity;

import android.content.Intent;
import android.os.Handler;
import android.widget.Button;

import com.dxl.techreading.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author du_xi
 * @date 2018-11-7 19:49:25
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toMainActivity();
            }
        }, 3000);
    }

    boolean isIn = false;

    @OnClick(R.id.btn_skip)
    void toMainActivity() {
        if (isIn) {
            return;
        }
        isIn = true;
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
