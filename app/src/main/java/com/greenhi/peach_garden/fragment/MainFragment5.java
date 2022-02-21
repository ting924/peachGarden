package com.greenhi.peach_garden.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.adapter.MyFragmentPagerAdapter;

import java.util.ArrayList;

public class MainFragment5 extends Fragment {

    private Context mContext;
    private View rootView;

    private ViewPager2 viewPager;
    private ArrayList<Fragment> fragments;

    private TextView tvSc,tvFs,tvGz,tvXj,tvSz,tvCurrent;
    private LinearLayout llSc,llFs,llGz,llXj,llSz;

    public static MainFragment5 newInstance() {
        Bundle args = new Bundle();
        MainFragment5 fragment = new MainFragment5();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.main_fragment5, container, false);
        }
        Log.e("TAG4544", "onCreateView: CommunityFragment");
        initPager();
        initData();
        initView();
        return rootView;
    }

    private void initPager() {
        viewPager = rootView.findViewById(R.id.sc_viewPager);
        fragments = new ArrayList<>();
        fragments.add(WodeFragment1.newInstance());
        fragments.add(WodeFragment2.newInstance());
        fragments.add(WodeFragment3.newInstance());
        fragments.add(WodeFragment4.newInstance());
        fragments.add(WodeFragment5.newInstance());
        MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(),getLifecycle(),fragments);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                View view = fragments.get(viewPager.getCurrentItem()).getView();
                if(view!=null){
                    updatePagerHeightForChild(view,viewPager);
                }
            }
        });
        viewPager.registerOnPageChangeCallback(new PageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changeTab(position);
            }
        });
    }

    private void initData() {

    }

    private void initView() {
        tvSc = rootView.findViewById(R.id.tv_wode_sc);
        tvFs = rootView.findViewById(R.id.tv_wode_fs);
        tvGz = rootView.findViewById(R.id.tv_wode_gz);
        tvXj = rootView.findViewById(R.id.tv_wode_xj);
        tvSz = rootView.findViewById(R.id.tv_wode_sz);
        tvCurrent = tvSc;
        llSc = rootView.findViewById(R.id.ll_wode_sc);
        llFs = rootView.findViewById(R.id.ll_wode_fs);
        llGz = rootView.findViewById(R.id.ll_wode_gz);
        llXj = rootView.findViewById(R.id.ll_wode_xj);
        llSz = rootView.findViewById(R.id.ll_wode_sz);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        llSc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTab(0);
            }
        });
        llFs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTab(1);
            }
        });
        llGz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTab(2);
            }
        });
        llXj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTab(3);
            }
        });
        llSz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTab(4);
            }
        });
    }

    private void changeTab(int position) {
        tvCurrent.setSelected(false);
        switch (position) {
            case 0:
                viewPager.setCurrentItem(0);
                tvSc.setSelected(true);
                tvCurrent = tvSc;
                break;
            case 1:
                viewPager.setCurrentItem(1);
                tvFs.setSelected(true);
                tvCurrent = tvFs;
                break;
            case 2:
                viewPager.setCurrentItem(2);
                tvGz.setSelected(true);
                tvCurrent = tvGz;
                break;
            case 3:
                viewPager.setCurrentItem(3);
                tvXj.setSelected(true);
                tvCurrent = tvXj;
                break;
            case 4:
                viewPager.setCurrentItem(4);
                tvSz.setSelected(true);
                tvCurrent = tvSz;
                break;
        }
    }

    class PageChangeCallback extends ViewPager2.OnPageChangeCallback{
        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            View view = fragments.get(position).getView();
            if(view!=null){
                updatePagerHeightForChild(view,viewPager);
            }
        }
    }

    private void updatePagerHeightForChild(View view,ViewPager2 pager){
        view.post(new Runnable() {
            @Override
            public void run() {
                int wMeasureSpec = View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY);
                int hMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                view.measure(wMeasureSpec, hMeasureSpec);
                if (pager.getLayoutParams().height != view.getMeasuredHeight()){
                    pager.getLayoutParams().height = view.getMeasuredHeight();
                }
            }
        });
    }

}
