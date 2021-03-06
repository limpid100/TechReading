package com.dxl.techreading.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.Toast;

import com.dxl.techreading.R;
import com.dxl.techreading.adapter.TabViewPagerAdapter;
import com.dxl.techreading.base.BaseActivity;
import com.dxl.techreading.model.GlobalVariables;

import java.util.Arrays;

import butterknife.BindView;

/**
 * @author du_xi
 * @date 2018/11/7
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.tab)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        TabViewPagerAdapter viewPagerAdapter = new TabViewPagerAdapter(getSupportFragmentManager());

        TestFragment testFragment = TestFragment.newInstance("");

        CategoryFragment androidFragment = CategoryFragment.newInstance("Android");
        CategoryFragment iOSFragment = CategoryFragment.newInstance("iOS");
        CategoryFragment expandResourceFragment = CategoryFragment.newInstance("拓展资源");
        CategoryFragment frontFragment = CategoryFragment.newInstance("前端");
        CategoryFragment appFragment = CategoryFragment.newInstance("App");

        viewPagerAdapter.setTitles(Arrays.asList(GlobalVariables.TAB_TITLES));
        viewPagerAdapter.addFragment(testFragment, androidFragment, iOSFragment, expandResourceFragment, frontFragment, appFragment);
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setOffscreenPageLimit(viewPagerAdapter.getCount());
        mViewPager.setCurrentItem(1);
        mTabLayout.setupWithViewPager(mViewPager);
        mNavigationView.setNavigationItemSelectedListener(this);
    }


    /**
     * 实现返回时，不退出应用
     */
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                Toast.makeText(this, "home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_about:
                break;
            case R.id.menu_feedback:
                break;
            case R.id.menu_setting:
                break;
            case R.id.menu_exit:
                exitApplication();
                break;
            default:
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 退出应用
     */
    private void exitApplication() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
