package com.greenhi.peach_garden.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.fragment.SetFragment1;
import com.greenhi.peach_garden.fragment.SetFragment2;
import com.greenhi.peach_garden.fragment.SetFragment3;
import com.greenhi.peach_garden.fragment.SetFragment4;
import com.greenhi.peach_garden.fragment.SetFragment5;
import com.greenhi.peach_garden.fragment.SetFragment6;
import com.greenhi.peach_garden.fragment.SetFragment7;
import com.greenhi.peach_garden.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout mTab1, mTab2, mTab3, mTab4, mTab5, mTab6, mTab7, tabCurrent;
    ImageView setBack;

    private List<Fragment> fragmentList = new ArrayList<>();

    private SetFragment1 fragment1;
    private SetFragment2 fragment2;
    private SetFragment3 fragment3;
    private SetFragment4 fragment4;
    private SetFragment5 fragment5;
    private SetFragment6 fragment6;
    private SetFragment7 fragment7;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        //设置全屏和状态栏透明
        ScreenUtils.transparencyBar(this);
        //改变状态栏文字颜色
        ScreenUtils.setAndroidNativeLightStatusBar(this, true);
        //隐藏底部虚拟tab键
        ScreenUtils.hideTab(this);
        setContentView(R.layout.activity_setting);
        mTab1 = findViewById(R.id.set_tab1);
        mTab2 = findViewById(R.id.set_tab2);
        mTab3 = findViewById(R.id.set_tab3);
        mTab4 = findViewById(R.id.set_tab4);
        mTab5 = findViewById(R.id.set_tab5);
        mTab6 = findViewById(R.id.set_tab6);
        mTab7 = findViewById(R.id.set_tab7);
        setBack = findViewById(R.id.iv_set_back);

        fragmentManager = getSupportFragmentManager();
        tabCurrent = mTab1;
        mTab1.setSelected(true);  //默认选中
        fragment1 = SetFragment1.newInstance();
        fragmentList.add(fragment1);
        hideOthersFragment(fragment1, true);
    }

    private void initData() {

    }

    private void initListener(){
        mTab1.setOnClickListener(this);
        mTab2.setOnClickListener(this);
        mTab3.setOnClickListener(this);
        mTab4.setOnClickListener(this);
        mTab5.setOnClickListener(this);
        mTab6.setOnClickListener(this);
        mTab7.setOnClickListener(this);
        setBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        changeTab(view.getId());
    }

    private void changeTab(int position) {
        tabCurrent.setSelected(false);
        switch (position) {
            case R.id.set_tab1:
                mTab1.setSelected(true);
                tabCurrent = mTab1;
                hideOthersFragment(fragment1, false);
                break;
            case R.id.set_tab2:
                mTab2.setSelected(true);
                tabCurrent = mTab2;
                if (fragment2 == null) {
                    fragment2 = SetFragment2.newInstance();
                    fragmentList.add(fragment2);
                    hideOthersFragment(fragment2, true);
                } else {
                    hideOthersFragment(fragment2, false);
                }
                break;
            case R.id.set_tab3:
                mTab3.setSelected(true);
                tabCurrent = mTab3;
                if (fragment3 == null) {
                    fragment3 = SetFragment3.newInstance();
                    fragmentList.add(fragment3);
                    hideOthersFragment(fragment3, true);
                } else {
                    hideOthersFragment(fragment3, false);
                }
                break;
            case R.id.set_tab4:
                mTab4.setSelected(true);
                tabCurrent = mTab4;
                if (fragment4 == null) {
                    fragment4 = SetFragment4.newInstance();
                    fragmentList.add(fragment4);
                    hideOthersFragment(fragment4, true);
                } else {
                    hideOthersFragment(fragment4, false);
                }
                break;
            case R.id.set_tab5:
                mTab5.setSelected(true);
                tabCurrent = mTab5;
                if (fragment5 == null) {
                    fragment5 = SetFragment5.newInstance();
                    fragmentList.add(fragment5);
                    hideOthersFragment(fragment5, true);
                } else {
                    hideOthersFragment(fragment5, false);
                }
                break;
            case R.id.set_tab6:
                mTab6.setSelected(true);
                tabCurrent = mTab6;
                if (fragment6 == null) {
                    fragment6 = SetFragment6.newInstance();
                    fragmentList.add(fragment6);
                    hideOthersFragment(fragment6, true);
                } else {
                    hideOthersFragment(fragment6, false);
                }
                break;
            case R.id.set_tab7:
                mTab7.setSelected(true);
                tabCurrent = mTab7;
                if (fragment7 == null) {
                    fragment7 = SetFragment7.newInstance();
                    fragmentList.add(fragment7);
                    hideOthersFragment(fragment7, true);
                } else {
                    hideOthersFragment(fragment7, false);
                }
                break;
            case R.id.iv_set_back:
                this.finish();
                break;
        }
    }

    private void hideOthersFragment(Fragment showFragment, boolean add) {

        fragmentTransaction = fragmentManager.beginTransaction();
        if (add) { //never create before
            fragmentTransaction.add(R.id.frameLayout, showFragment);
        }
        for (Fragment fragment : fragmentList) {
            if (showFragment.equals(fragment)) {
                fragmentTransaction.show(fragment);
            } else {
                fragmentTransaction.hide(fragment);
            }
        }
        fragmentTransaction.commit();
    }

}