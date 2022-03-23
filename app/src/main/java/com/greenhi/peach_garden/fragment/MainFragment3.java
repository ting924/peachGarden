package com.greenhi.peach_garden.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.adapter.MyFragmentPagerAdapter;

import java.util.ArrayList;


public class MainFragment3 extends Fragment {

    private Context mContext;
    private View rootView;
    private ViewPager2 viewPager;
    private ArrayList<Fragment> fragments;
    private ViewGroup mContentView;
    private TextView zhengjiling,taolin,xiaoyouxi,jishi,current;


    public static MainFragment3 newInstance() {
        Bundle args = new Bundle();
        MainFragment3 fragment = new MainFragment3();
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
            rootView = inflater.inflate(R.layout.main_fragment3, container, false);
        }
        Log.e("TAG4544", "onCreateView: CommunityFragment");
        initPager();
        initData();
        initView();
        return rootView;
    }

    private void initPager() {
        viewPager = rootView.findViewById(R.id.sz_viewPager3);
//        viewPager.setOffscreenPageLimit(5);
        fragments = new ArrayList<>();
        fragments.add(ShisheFragment1.newInstance());
        fragments.add(ShisheFragment2.newInstance
                ());
        fragments.add(ShisheFragment3.newInstance());
        fragments.add(ShisheFragment4.newInstance());
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
        viewPager.registerOnPageChangeCallback(new MainFragment3.PageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changeTab(position);
                //viewPager.requestLayout();
            }
        });
    }

    private void initData() {

    }

    private void initView() {
        zhengjiling = rootView.findViewById(R.id.zhengjiling);
        taolin = rootView.findViewById(R.id.taolin);
        xiaoyouxi = rootView.findViewById(R.id.xiaoyouxi);
        jishi = rootView.findViewById(R.id.jishi);
        current = zhengjiling;

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        zhengjiling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTab(0);
            }
        });
        taolin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTab(1);
            }
        });
        xiaoyouxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTab(2);
            }
        });
        jishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTab(3);
            }
        });
    }

    private void changeTab(int position) {
        current.setSelected(false);
        switch (position) {
            case 0:
                viewPager.setCurrentItem(0);
                zhengjiling.setSelected(true);
                current=zhengjiling;
                break;
            case 1:
                viewPager.setCurrentItem(1);
                taolin.setSelected(true);
                current=taolin;
                break;
            case 2:
                viewPager.setCurrentItem(2);
                xiaoyouxi.setSelected(true);
                current=xiaoyouxi;
                break;
            case 3:
                viewPager.setCurrentItem(3);
                jishi.setSelected(true);
                current=jishi;
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
