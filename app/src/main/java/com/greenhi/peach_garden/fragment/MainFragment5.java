package com.greenhi.peach_garden.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.transition.AutoTransition;
import androidx.transition.Fade;
import androidx.transition.Scene;
import androidx.transition.Transition;
import androidx.transition.TransitionInflater;
import androidx.transition.TransitionManager;
import androidx.transition.TransitionSet;
import androidx.viewpager2.widget.ViewPager2;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.activity.SettingActivity;
import com.greenhi.peach_garden.adapter.MyFragmentPagerAdapter;
import com.greenhi.peach_garden.scroll_view.MyScrollView;
import com.greenhi.peach_garden.utils.UserMessage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainFragment5 extends Fragment {

    private Context mContext;
    private View rootView;
    private int id;

    private ViewPager2 viewPager;
    private ArrayList<Fragment> fragments;

    private TextView tvSc, tvFs, tvGz, tvXj, tvSz, tvCurrent;
    private LinearLayout llSc, llFs, llGz, llXj, llSz;
    private ImageView ivSetting,touxiang;

    private MyScrollView scrollView;
    private MyScrollView.OnMyScrollListener listener;
    private int oldY = 0;

    private ViewGroup sceneRoot;
    private ViewGroup sceneRoot2;
    private Scene preScene;
    private Scene endScene;
    private Scene preScene2;
    private Scene endScene2;

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
//        viewPager.setOffscreenPageLimit(5);
        fragments = new ArrayList<>();
        fragments.add(WodeFragment1.newInstance());
        fragments.add(WodeFragment2.newInstance());
        fragments.add(WodeFragment3.newInstance());
        fragments.add(WodeFragment4.newInstance());
        fragments.add(WodeFragment5.newInstance());
        MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), getLifecycle(), fragments);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                View view = fragments.get(viewPager.getCurrentItem()).getView();
                if (view != null) {
                    updatePagerHeightForChild(view, viewPager);
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
        id = UserMessage.getUserInfo(getContext());
        String url = "http://47.108.176.163:7777/img_user_head/"+id+".png";
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
        ivSetting = rootView.findViewById(R.id.iv_setting);
        scrollView = rootView.findViewById(R.id.sc_scroll);
        touxiang=rootView.findViewById(R.id.iv_head);

        sceneRoot = (ViewGroup) rootView.findViewById(R.id.scene_root);
        preScene = Scene.getSceneForLayout(sceneRoot, R.layout.main_fragment5_scene_begin, mContext);
        endScene = Scene.getSceneForLayout(sceneRoot, R.layout.main_fragment5_scene_end, mContext);

        sceneRoot2 = (ViewGroup) rootView.findViewById(R.id.scene_root_head);
        preScene2 = Scene.getSceneForLayout(sceneRoot2, R.layout.head_scene_begin, mContext);
        endScene2 = Scene.getSceneForLayout(sceneRoot2, R.layout.head_scene_end, mContext);

        Picasso.with(getContext()).load(url).placeholder(R.drawable.default_circle_head).into(touxiang);
        listener = new MyScrollView.OnMyScrollListener() {
            @Override
            public void onScrollStateChanged(MyScrollView view, int state) {
                String str;
                if (state == 0) {
                    str = "IDLE";
                } else if (state == 1) {
                    str = "DRAG";
                } else if (state == 2) {
                    str = "FLING";
                } else {
                    str = "ERROR";
                }
                Log.d("brycegao", "滑动状态：" + str);
            }

            @Override
            public void onScroll(MyScrollView view, int y) {
                int i = oldY - y;
                if (i < 0) {
//                Transition fadeTransition = new Fade();
//                TransitionSet transitionSet = new AutoTransition();
                    TransitionInflater.from(mContext);
                    Transition fadeTransition = new AutoTransition();
//                transitionSet.addTransition(fadeTransition);
                    TransitionManager.go(endScene, fadeTransition);
                    TransitionManager.go(endScene2, fadeTransition);
                } else if (y == 0) {
                    TransitionManager.go(preScene);
                    TransitionManager.go(preScene2);
                }
                Log.e("brycegao", "yyyyyy" + y);
            }

            @Override
            public void onScrollToTop() {
                Log.d("brycegao", "滑到顶部");
            }

            @Override
            public void onScrollToBottom() {
                Log.d("brycegao", "滑动底部");
            }
        };

        scrollView.addOnScrollListner(listener);

    }

    @Override
    public void onResume() {
        super.onResume();
        scrollView.addOnScrollListner(listener);
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
        ivSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SettingActivity.class);
                mContext.startActivity(intent);
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

    class PageChangeCallback extends ViewPager2.OnPageChangeCallback {
        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            View view = fragments.get(position).getView();
            if (view != null) {
                updatePagerHeightForChild(view, viewPager);
            }
        }
    }

    private void updatePagerHeightForChild(View view, ViewPager2 pager) {
        view.post(new Runnable() {
            @Override
            public void run() {
                int wMeasureSpec = View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY);
                int hMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                view.measure(wMeasureSpec, hMeasureSpec);
                if (pager.getLayoutParams().height != view.getMeasuredHeight()) {
                    pager.getLayoutParams().height = view.getMeasuredHeight();
                }
            }
        });
    }

}
