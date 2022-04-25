package com.greenhi.peach_garden.fragment;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.activity.poetry_hall.NoneActivity;
import com.greenhi.peach_garden.activity.poetry_hall.entity.CourseItem;
import com.greenhi.peach_garden.adapter.MyFragmentPagerAdapter;
import com.greenhi.peach_garden.adapter.MyGridAdapter;

import java.util.ArrayList;

public class ShiguanFragment2 extends Fragment{
    private Context mContext;
    private View rootView;
    private ViewGroup mContentView;
    private GridView gridView;
    private EditText editText;
    private ArrayList<CourseItem>datas=new ArrayList<CourseItem>();
    private ArrayList<ImageView>imageViews;
    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private final int [] imageId={R.drawable.s1,R.drawable.s2,R.drawable.s3,R.drawable.s4};
    private int preposition=0;
    private boolean isDragging = false;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int item = viewPager.getCurrentItem()+1;
           viewPager.setCurrentItem(item);
            //延迟发消息
            handler.sendEmptyMessageDelayed(0,400000);
        }
    };
    public static ShiguanFragment2 newInstance() {
        Bundle args = new Bundle();
        ShiguanFragment2 fragment = new ShiguanFragment2();
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
            rootView = inflater.inflate(R.layout.activity_poemcourse, container, false);
        }
        initData();
        initView();
        getDatas();
        gridView.setAdapter(new MyGridAdapter(datas));
//        gridView.setAdapter(new MyGridAdapter(datas));
        imageViews=new ArrayList<ImageView>();
        editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(KeyEvent.KEYCODE_ENTER==i&& keyEvent.getAction()==KeyEvent.ACTION_DOWN){
                    String content=editText.getText().toString();
                    Activity activity1=getActivity();
                    Intent intent=new Intent(activity1, NoneActivity.class);
                    intent.putExtra("content",content);
                    Toast.makeText(activity1,content,Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
                return false;
            }
        });
        for(int i=0;i<imageId.length;i++){
            Activity activity1=getActivity();
            ImageView imageView=new ImageView(activity1);
            imageView.setBackgroundResource(imageId[i]);
            imageViews.add(imageView);
            ImageView point = new ImageView(activity1);
            point.setBackgroundResource(R.drawable.point_selector);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(35,35);
            if(i==0){
                point.setEnabled(true); //显示红色
            }else{
                point.setEnabled(false);//显示灰色
                params.leftMargin = 8;
            }


            point.setLayoutParams(params);

            linearLayout.addView(point);
        }
        viewPager.setAdapter(new MyPagerAdapter());
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());
        handler.sendEmptyMessageDelayed(0,300000);
        return rootView;
    }
    private void initData() {

    }

    private void initView() {
        mContentView = rootView.findViewById(R.id.main_fra2);
        editText=rootView.findViewById(R.id.course_search);
        viewPager=rootView.findViewById(R.id.viewpager1);
        linearLayout=rootView.findViewById(R.id.points);
        gridView=rootView.findViewById(R.id.gv);
    }

    public void getDatas(){
        CourseItem courseItem1=new CourseItem(R.drawable.k1,"书法","今日视频教你学会隶书技巧","李老师");
        datas.add(courseItem1);
        CourseItem courseItem2=new CourseItem(R.drawable.k2,"国画","你不知道的张大千","张老师");
        datas.add(courseItem2);
        CourseItem courseItem3=new CourseItem(R.drawable.k3,"书法","今日视频教你掌握隶书技巧","李老师");
        datas.add(courseItem3);
        CourseItem courseItem4=new CourseItem(R.drawable.k4,"国画","你不知道的张大千","刘老师");
        datas.add(courseItem4);
        CourseItem courseItem5=new CourseItem(R.drawable.k5,"书法","今日视频教你熟悉隶书技巧","王老师");
        datas.add(courseItem5);

    }
    class MyPagerAdapter extends PagerAdapter {

        /**
         * 得到图片的总数
         * @return
         */
        @Override
        public int getCount() {
//            return imageViews.size();
            return Integer.MAX_VALUE;
        }

        /**
         * 相当于getView方法
         * @param container ViewPager自身
         * @param position 当前实例化页面的位置
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            int realPosition = position%imageViews.size();
             ImageView imageView =  imageViews.get(realPosition);
            ViewParent parent=imageView.getParent();
            if(imageView.getParent()!=null){
                ((ViewGroup)imageView.getParent()).removeView(imageView);
            }

                container.addView(imageView);//添加到ViewPager中

//            Log.e(TAG, "instantiateItem==" + position + ",---imageView==" + imageView);
            imageView.setTag(position);
            return imageView;}

        @Override
        public boolean isViewFromObject(View view, Object object) {
//            if(view == object){
//                return true;
//            }else{
//                return  false;
//            }
            return view == object;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            Log.e(TAG, "destroyItem==" + position + ",---object==" + object);
            int realP=position%imageViews.size();
            container.removeView(imageViews.get(realP));

        }

    };

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        /**
         * 当页面滚动了的时候回调这个方法
         * @param position 当前页面的位置
         * @param positionOffset 滑动页面的百分比
         * @param positionOffsetPixels 在屏幕上滑动的像数
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        /**
         * 当某个页面被选中了的时候回调
         * @param position 被选中页面的位置
         */
        @Override
        public void onPageSelected(int position) {
            int realPosition = position%imageViews.size();
            //把上一个高亮的设置默认-灰色
            linearLayout.getChildAt(preposition).setEnabled(false);
            //当前的设置为高亮-红色
            linearLayout.getChildAt(realPosition).setEnabled(true);
            preposition = realPosition;



        }

        /**
         当页面滚动状态变化的时候回调这个方法
         静止->滑动
         滑动-->静止
         静止-->拖拽

         */
        @Override
        public void onPageScrollStateChanged(int state) {
            if(state == ViewPager.SCROLL_STATE_DRAGGING){
                isDragging = true;
                handler.removeCallbacksAndMessages(null);

            }else if(state == ViewPager.SCROLL_STATE_SETTLING){


            }else if(state == ViewPager.SCROLL_STATE_IDLE&&isDragging){
                isDragging = false;
                handler.removeCallbacksAndMessages(null);
                handler.sendEmptyMessageDelayed(0,4000);
            }

        }
    }

}
