package com.greenhi.peach_garden.activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.adapter.MyFragmentPagerAdapter;
import com.greenhi.peach_garden.fragment.MainFragment1;
import com.greenhi.peach_garden.fragment.MainFragment3;
import com.greenhi.peach_garden.fragment.MainFragment4;
import com.greenhi.peach_garden.fragment.MainFragment5;
import com.greenhi.peach_garden.fragment.ShiguanFragment;
import com.greenhi.peach_garden.utils.ActivityCollectorUtil;
import com.greenhi.peach_garden.utils.ScreenUtils;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ArrayList<Fragment> fragments;

    ViewPager2 viewPager;
    LinearLayout tab1, tab2, tab3, tab4, tab5;
    ImageView ivTab1, ivTab2, ivTab3, ivTab4, ivTab5, ivCurrent;
    TextView tvTab1, tvTab2, tvTab3, tvTab4, tvTab5, tvCurrent;

    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//java代码中设置横屏会导致卡顿
        super.onCreate(savedInstanceState);
        ActivityCollectorUtil.addActivity(this);
        //设置全屏和状态栏透明
        ScreenUtils.transparencyBar(this);
        //改变状态栏文字颜色
        ScreenUtils.setAndroidNativeLightStatusBar(this, true);
        //隐藏底部虚拟tab键
        ScreenUtils.hideTab(this);
        setContentView(R.layout.activity_main);
        initPager();
        initView();
        setListener();
        getPermissions(PERMISSIONS_STORAGE);
    }

    private void initPager() {
        viewPager = findViewById(R.id.viewPager);
        viewPager.setUserInputEnabled(false);
        fragments = new ArrayList<>();
        fragments.add(MainFragment1.newInstance());
        fragments.add(ShiguanFragment.newInstance());
        fragments.add(MainFragment3.newInstance());
        fragments.add(MainFragment4.newInstance());
        fragments.add(MainFragment5.newInstance());
        MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), getLifecycle(), fragments);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0, false);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changeTab(position);
            }
        });
    }

    private void initView() {
        tab1 = findViewById(R.id.main_tab1);
        tab2 = findViewById(R.id.main_tab2);
        tab3 = findViewById(R.id.main_tab3);
        tab4 = findViewById(R.id.main_tab4);
        tab5 = findViewById(R.id.main_tab5);

        ivTab1 = findViewById(R.id.iv_tab1);
        ivTab2 = findViewById(R.id.iv_tab2);
        ivTab3 = findViewById(R.id.iv_tab3);
        ivTab4 = findViewById(R.id.iv_tab4);
        ivTab5 = findViewById(R.id.iv_tab5);

        tvTab1 = findViewById(R.id.tv_tab1);
        tvTab2 = findViewById(R.id.tv_tab2);
        tvTab3 = findViewById(R.id.tv_tab3);
        tvTab4 = findViewById(R.id.tv_tab4);
        tvTab5 = findViewById(R.id.tv_tab5);

        ivTab1.setSelected(true);
        tvTab1.setSelected(true);
        ivCurrent = ivTab1;
        tvCurrent = tvTab1;
    }

    private void setListener() {
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);
        tab4.setOnClickListener(this);
        tab5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        changeTab(view.getId());
    }

    private void changeTab(int position) {
        ivCurrent.setSelected(false);
        tvCurrent.setSelected(false);
        switch (position) {
            case R.id.main_tab1:
                viewPager.setCurrentItem(0, false);
            case 0:
                ivTab1.setSelected(true);
                tvTab1.setSelected(true);
                ivCurrent = ivTab1;
                tvCurrent = tvTab1;
                break;
            case R.id.main_tab2:
                viewPager.setCurrentItem(1, false);
            case 1:
                ivTab2.setSelected(true);
                tvTab2.setSelected(true);
                ivCurrent = ivTab2;
                tvCurrent = tvTab2;
                break;
            case R.id.main_tab3:
                viewPager.setCurrentItem(2, false);
            case 2:
                ivTab3.setSelected(true);
                tvTab3.setSelected(true);
                tvCurrent = tvTab3;
                ivCurrent = ivTab3;
                break;
            case R.id.main_tab4:
                viewPager.setCurrentItem(3, false);
            case 3:
                ivTab4.setSelected(true);
                tvTab4.setSelected(true);
                ivCurrent = ivTab4;
                tvCurrent = tvTab4;
                break;
            case R.id.main_tab5:
                viewPager.setCurrentItem(4, false);
            case 4:
                tvTab5.setSelected(true);
                ivTab5.setSelected(true);
                tvCurrent = tvTab5;
                ivCurrent = ivTab5;
                break;
        }
    }

    //实现点两次返回键退出程序
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            showToast("再按一次返回键退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);
    }
}