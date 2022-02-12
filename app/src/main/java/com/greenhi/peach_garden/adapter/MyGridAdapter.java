package com.greenhi.peach_garden.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.activity.poetry_hall.entity.CourseItem;

import java.util.ArrayList;

public class MyGridAdapter extends BaseAdapter {
    private ArrayList<CourseItem>mDatas;

    public MyGridAdapter(ArrayList<CourseItem>Datas){
        mDatas=Datas;
    }

    @Override
    public int getCount() {
        return mDatas==null?0:mDatas.size();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view==null){
            LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
            view=layoutInflater.inflate(R.layout.course_item,null);

        }
        TextView tv1=(TextView) view.findViewById(R.id.course_intro);
        TextView tv2=(TextView) view.findViewById(R.id.course_teacher);
        TextView tv3=(TextView)view.findViewById(R.id.course_type);
        RelativeLayout relativeLayout=(RelativeLayout) view.findViewById(R.id.course_bg);
       tv1.setText(mDatas.get(i).getIntro());
        tv2.setText(mDatas.get(i).getTeacher());
        tv3.setText(mDatas.get(i).getType());
        relativeLayout.setBackgroundResource(mDatas.get(i).getImg_id());
        return view;
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
}
