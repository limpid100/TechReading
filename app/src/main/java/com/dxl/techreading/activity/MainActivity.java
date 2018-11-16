package com.dxl.techreading.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dxl.techreading.R;
import com.dxl.techreading.adapter.TabViewPagerAdapter;
import com.dxl.techreading.fragment.CategoryFragment;
import com.dxl.techreading.fragment.TestFragment;
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
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
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
                break;
            default:
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
