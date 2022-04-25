package com.greenhi.peach_garden.fragment;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.adapter.MyFragmentPagerAdapter;

import java.util.ArrayList;

public class ShiguanFragment extends Fragment {
    private Context mContext;
    private View rootView;
    private ViewGroup mContentView;
    private ViewPager2 viewPager;
    private ArrayList<Fragment> fragments;
    private TextView shiwen,yiwen,shike,current;
    public static ShiguanFragment newInstance() {
        Bundle args = new Bundle();
        ShiguanFragment fragment = new ShiguanFragment();
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
            rootView = inflater.inflate(R.layout.fragment_shiguan, container, false);
        }
        Log.e("TAG4544", "onCreateView: CommunityFragment");
        initData();
        initView();
        initPager();
        return rootView;
    }

    private void initData() {

    }

    private void initView() {
        mContentView = rootView.findViewById(R.id.main_fra2);
        shiwen=rootView.findViewById(R.id.shiwen);
        yiwen=rootView.findViewById(R.id.yiwen);
        shike=rootView.findViewById(R.id.shike);
        current=shiwen;
        current.setSelected(true);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        shiwen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTab(0);
            }
        });
        yiwen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTab(1);
            }
        });
        shike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTab(2);
            }
        });
    }
    private void changeTab(int position) {
        current.setSelected(false);
        switch (position) {
            case 0:
                viewPager.setCurrentItem(0);
                shiwen.setSelected(true);
                current=shiwen;
                break;
            case 1:
                viewPager.setCurrentItem(1);
                yiwen.setSelected(true);
                current=yiwen;
                break;
            case 2:
                viewPager.setCurrentItem(2);
                shike.setSelected(true);
                current=shike;
                break;
        }
    }
    public void initPager(){
        viewPager = rootView.findViewById(R.id.shiguan_viewPager);
        // 禁止滑动
//        viewPager.setUserInputEnabled(false);
//        viewPager.setOffscreenPageLimit(5);
        fragments = new ArrayList<>();
        fragments.add(MainFragment2.newInstance());
        fragments.add(ShiguanFragment3.newInstance());
        fragments.add(ShiguanFragment2.newInstance());
        MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(),getLifecycle(),fragments);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                View view = fragments.get(viewPager.getCurrentItem()).getView();
//                if(view!=null){
//                    updatePagerHeightForChild(view,viewPager);
//                }
            }
        });
        viewPager.registerOnPageChangeCallback(new PageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changeTab(position);
                //viewPager.requestLayout();
            }
        });
    }
    class PageChangeCallback extends ViewPager2.OnPageChangeCallback{
        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            View view=fragments.get(position).getView();
//            if(view!=null){
//                updatePagerHeightForChild(view,viewPager);
//            }
        }

    };
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







