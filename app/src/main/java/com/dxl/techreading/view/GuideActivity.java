package com.dxl.techreading.view;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dxl.techreading.R;
import com.dxl.techreading.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 引导页
 * https://www.jianshu.com/p/adb21180862a
 * https://github.com/LRH1993/CustomViewPager
 * @author dxl
 * @date 2018/12/20 14:30
 */
public class GuideActivity extends BaseActivity {

    @BindView(R.id.iv_light_dots)
    ImageView mLightDots;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.ll_dots)
    LinearLayout mDotsContainer;
    @BindView(R.id.btn_go)
    Button mBtnGo;

    /**
     * 两个小圆点之前的距离
     */
    double mDistance = 0.0;
    /**
     * 引导页面
     */
    private static final int[] GUIDE_LAYOUT = {R.layout.guide1, R.layout.guide2, R.layout.guide3};

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView() {
        initGuideView();
    }


    void initGuideView(){
        List<View> viewList = new ArrayList<>();
        LayoutInflater inflater = LayoutInflater.from(this);
        //小圆点属性
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 20, 0);
        //添加引导页和小圆点
        for (int layoutID : GUIDE_LAYOUT) {
            viewList.add(inflater.inflate(layoutID, null));

            ImageView dotsView = new ImageView(this);
            dotsView.setImageResource(R.drawable.dots_gray);
            mDotsContainer.addView(dotsView, params);
        }
        mViewPager.setAdapter(new ViewPagerAdapter(viewList));

        //获取两个小圆点之间的距离
        mLightDots.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mDistance = mDotsContainer.getChildAt(1).getLeft() - mDotsContainer.getChildAt(0).getLeft();
                mLightDots.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                double leftMargin = mDistance * (position + positionOffset);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mLightDots.getLayoutParams();
                layoutParams.leftMargin = (int) leftMargin;
                mLightDots.setLayoutParams(layoutParams);
                if (position == 2) {
                    mBtnGo.setVisibility(View.VISIBLE);
                } else {
                    mBtnGo.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick(R.id.btn_go)
    void btnGoClicked(){
        startActivity(SplashActivity.class);
        finish();
    }


    class ViewPagerAdapter extends PagerAdapter {

        private List<View> mViewList;

        ViewPagerAdapter(List<View> viewList) {
            mViewList = viewList;
        }

        @Override
        public int getCount() {
            return mViewList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = mViewList.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(mViewList.get(position));
        }
    }
}
