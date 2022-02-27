package com.greenhi.peach_garden.fragment;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.adapter.MyFragmentPagerAdapter;

import java.util.ArrayList;


public class MainFragment4 extends Fragment{

    private Context mContext;
    private View rootView;
    private ViewPager2 viewPager;
    private ArrayList<Fragment> fragments;

    private TextView guanzhu,jingxuan,chuangzuo,current;

    public static MainFragment4 newInstance() {
        Bundle args = new Bundle();
        MainFragment4 fragment = new MainFragment4();
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
            rootView = inflater.inflate(R.layout.main_fragment4, container, false);
        }
        Log.e("TAG4544", "onCreateView: CommunityFragment");
        initPager();
        initData();
        initView();
        return rootView;
    }

    private void initPager() {
        viewPager = rootView.findViewById(R.id.sz_viewPager);
//        viewPager.setOffscreenPageLimit(5);
        fragments = new ArrayList<>();
        fragments.add(ShizhaiFragment1.newInstance());
        fragments.add(ShizhaiFragment2.newInstance());
        fragments.add(ShizhaiFragment3.newInstance());
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
                //viewPager.requestLayout();
            }
        });
    }

    private void initData() {

    }

    private void initView() {
        guanzhu = rootView.findViewById(R.id.guanzhu);
        jingxuan = rootView.findViewById(R.id.jingxuan);
        chuangzuo=rootView.findViewById(R.id.chuangzuo);
        current = guanzhu;

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTab(0);
            }
        });
        jingxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTab(1);
            }
        });
        chuangzuo.setOnClickListener(new View.OnClickListener() {
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
                guanzhu.setSelected(true);
                current=guanzhu;
                break;
            case 1:
                viewPager.setCurrentItem(1);
                jingxuan.setSelected(true);
                current=jingxuan;
                break;
            case 2:
                viewPager.setCurrentItem(2);
                chuangzuo.setSelected(true);
                current=chuangzuo;
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
