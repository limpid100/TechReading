package com.dxl.techreading.customview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dxl.techreading.R;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.jianshu.com/p/c052ae42fbc9
 * @author dxl
 * @date 2018/12/21 14:38
 */
public class CycleViewPager extends FrameLayout {

    private ViewPager mViewPager;
    private TextView mTitle;
    private LinearLayout mIndicatorLayout;
    private List<View> mViews = new ArrayList<>(); //需要轮播的View，数量为轮播图数量+2

    private ImageCycleViewListener mImageCycleViewListener;

    //指示器
    private ImageView[] mIndicators;
    private List<Info> mInfos;
    private ViewPagerAdapter mAdapter;

    private Context mContext;

    private int mCurrentPosition = 0;

    public CycleViewPager(@NonNull Context context) {
        this(context, null);
    }

    public CycleViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CycleViewPager(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.layout_cycle_view, this, true);
        mViewPager = findViewById(R.id.cycle_view_pager);
        mTitle = findViewById(R.id.cycle_title);
        mIndicatorLayout = findViewById(R.id.cycle_indicator);
    }

    public void setData(List<Info> list, ImageCycleViewListener listener) {
        mInfos = list;
        mViews.add(getImageView(list.get(list.size() - 1).getUrl()));
        for (int i = 0; i < list.size(); i++) {
            mViews.add(getImageView(list.get(i).getUrl()));
        }
        mViews.add(getImageView(list.get(0).getUrl()));

        mImageCycleViewListener = listener;

        mIndicators = new ImageView[list.size()];
        mIndicatorLayout.removeAllViews();
        for (int i = 0; i < mIndicators.length; i++) {
            mIndicators[i] = new ImageView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 0, 10, 0);
            mIndicators[i].setLayoutParams(params);
            mIndicatorLayout.addView(mIndicators[i]);
        }

        mAdapter = new ViewPagerAdapter();
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPosition = position;
                if (position == 0) {
                    mCurrentPosition = mViews.size() - 2;
                }else if (position == mViews.size() - 1) {
                    mCurrentPosition = 1;
                }
                setIndicator(mCurrentPosition - 1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (mViewPager.getCurrentItem() != mCurrentPosition) {
                    mViewPager.setCurrentItem(mCurrentPosition, false);
                }
            }
        });
        setIndicator(0);

    }

    private void setIndicator(int position) {
        for (int i = 0; i < mIndicators.length; i++) {
            if (i == position) {
                mIndicators[i].setBackgroundResource(R.drawable.dots_light);
            }else {
                mIndicators[i].setBackgroundResource(R.drawable.dots_gray);
            }
        }
    }

    private View getImageView(String url) {
        RelativeLayout rl = new RelativeLayout(mContext);
        ImageView iv = new ImageView(mContext);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        iv.setLayoutParams(params);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(mContext).load(url).into(iv);
        rl.addView(iv);
        return rl;
    }


    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mViews.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, final int position) {
            View view = mViews.get(position);
            if (mImageCycleViewListener != null) {
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mImageCycleViewListener.onImageClick(mInfos.get(position), position, v);
                    }
                });
            }
            container.addView(view);
            return view;
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return POSITION_NONE;
        }
    }

    interface ImageCycleViewListener {
        void onImageClick(Info info, int position, View imageView);
    }

}
