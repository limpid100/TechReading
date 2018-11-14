package com.dxl.techreading.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dxl.techreading.R;
import com.dxl.techreading.model.WebContract;
import com.dxl.techreading.model.WebPresenter;

import butterknife.BindView;

/**
 * @author dxl
 * @date 2018/11/14 8:29
 */
public class WebViewActivity extends BaseActivity implements WebContract.IWebView {

    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    public static void start(Context context, String url, String title){
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    private WebContract.IWebPresenter mWebPresenter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_web_view;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void initWebView() {
        WebSettings webSettings = mWebView.getSettings();
        //支持js
        webSettings.setJavaScriptEnabled(true);
        //将图片缩放至适合屏幕大小
        webSettings.setUseWideViewPort(true);
        //缩放至适合屏幕大小
        webSettings.setLoadWithOverviewMode(true);
        //支持缩放
        webSettings.supportZoom();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setAppCacheEnabled(true);

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                mProgressBar.setVisibility(View.GONE);
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mProgressBar.setVisibility(View.VISIBLE);
                mProgressBar.setProgress(newProgress);
            }
        });
    }

    @Override
    public String getUrl() {
        return getIntent().getStringExtra("url");
    }

    @Override
    public void loadUrl(String url) {
        mWebView.loadUrl(url);
    }

    @Override
    protected void initView() {
        mTitle.setText(getIntent().getStringExtra("title"));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mWebPresenter = new WebPresenter(this);
        mWebPresenter.subscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebPresenter.unSubscribe();
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        }else {
            finish();
        }
    }
}
