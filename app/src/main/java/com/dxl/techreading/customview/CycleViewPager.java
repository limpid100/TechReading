package com.dxl.techreading.customview;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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
import com.dxl.techreading.bean.ImageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.jianshu.com/p/c052ae42fbc9\
 * todo 1.max页 2.滚动效果 3样式抽取
 *
 * @author dxl
 * @date 2018/12/21 14:38
 */
public class CycleViewPager extends FrameLayout {

    /**
     * viewPager布局
     */
    private ViewPager mViewPager;
    /**
     * 标题显示
     */
    private TextView mTitleTextview;
    /**
     * 小圆点容器
     */
    private LinearLayout mIndicatorLayout;
    /**
     * 需要轮播的View，数量为轮播图数量+2
     */
    private List<View> mViews = new ArrayList<>();

    /**
     * 图片点击事件
     */
    private ImageCycleViewListener mImageCycleViewListener;

    /**
     * 指示器（小圆点）
     */
    private ImageView[] mIndicators;
    /**
     * 图片资源
     */
    private List<ImageInfo> mImageInfos;

    private Context mContext;

    private int mCurrentPosition = 0;

    private int delay = 2000;

    private long releaseTime = 0;
    private boolean isScrolling = false;

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            long now = System.currentTimeMillis();
            if (now - releaseTime > delay - 500) {
                //满足条件，跳转下一页
                mHandler.sendEmptyMessage(WHEEL);
            }else {
                //不满足条件，可能是用户手动操作了
                mHandler.sendEmptyMessage(WHEEL_WAIT);
            }
        }
    };

    private final int WHEEL = 100;
    private final int WHEEL_WAIT = 101;

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case WHEEL:
                    if (!isScrolling) {
                        int position = (mCurrentPosition + 1) % mViews.size();
                        mViewPager.setCurrentItem(position, true);
                    }
                    releaseTime = System.currentTimeMillis();
                    mHandler.removeCallbacks(mRunnable);
                    mHandler.postDelayed(mRunnable, delay);
                    break;
                case WHEEL_WAIT:
                    mHandler.removeCallbacks(mRunnable);
                    mHandler.postDelayed(mRunnable, delay);
                    break;
                default:
            }
        }
    };

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
        mTitleTextview = findViewById(R.id.cycle_title);
        mIndicatorLayout = findViewById(R.id.cycle_indicator);
    }

    public void setData(List<ImageInfo> list, ImageCycleViewListener listener) {
        setData(list, listener, true);
    }

    /**
     *
     * @param list
     * @param listener
     * @param translucence 是否图片半透明，半透明可以使文字清晰
     */
    public void setData(List<ImageInfo> list, ImageCycleViewListener listener, boolean translucence) {
        mImageInfos = list;
        //先添加最后一个图片
        mViews.add(getImageView(list.get(list.size() - 1).getUrl(), translucence));
        for (int i = 0; i < list.size(); i++) {
            mViews.add(getImageView(list.get(i).getUrl(), translucence));
        }
        //最后添加第一个
        mViews.add(getImageView(list.get(0).getUrl(), translucence));

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

        ViewPagerAdapter adapter = new ViewPagerAdapter();
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //顺序为（如果是3个图片） 3| 1 2 3 | 1
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
                if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                    isScrolling = true;
                    return;
                }
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    releaseTime = System.currentTimeMillis();
                    if (mViewPager.getCurrentItem() != mCurrentPosition) {
                        mViewPager.setCurrentItem(mCurrentPosition, false);
                    }
                }
                isScrolling = false;
            }
        });
        mViewPager.setCurrentItem(1);
        setIndicator(0);
        mHandler.postDelayed(mRunnable, delay);
    }

    private void setIndicator(int position) {
        mTitleTextview.setText(mImageInfos.get(position).getTitle());
        for (int i = 0; i < mIndicators.length; i++) {
            if (i == position) {
                mIndicators[i].setBackgroundResource(R.drawable.dots_light);
            }else {
                mIndicators[i].setBackgroundResource(R.drawable.dots_gray);
            }
        }
    }

    private View getImageView(String url, boolean translucence) {
        RelativeLayout rl = new RelativeLayout(mContext);
        ImageView iv = new ImageView(mContext);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        iv.setLayoutParams(params);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(mContext).load(url).into(iv);
        rl.addView(iv);
        if (translucence) {
            ImageView backGround = new ImageView(mContext);
            backGround.setLayoutParams(params);
            backGround.setBackgroundResource(R.color.cycle_image_bg);
            rl.addView(backGround);
        }
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
                        mImageCycleViewListener.onImageClick(mImageInfos.get(position), position, v);
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
        void onImageClick(ImageInfo imageInfo, int position, View imageView);
    }

}
