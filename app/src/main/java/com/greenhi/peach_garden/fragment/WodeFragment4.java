package com.greenhi.peach_garden.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.adapter.RecyclerAdapterXJ;
import com.greenhi.peach_garden.item.ItemDataXJ;

import java.util.ArrayList;
import java.util.List;

public class WodeFragment4 extends Fragment {
    private Context mContext;
    private View rootView;

    private RecyclerView recyclerView;
    private RecyclerAdapterXJ recyclerAdapter;

    private List<ItemDataXJ> xjList;

    public static WodeFragment4 newInstance(){
        Bundle args = new Bundle();
        WodeFragment4 fragment = new WodeFragment4();
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
            rootView = inflater.inflate(R.layout.wode_fragment4, container, false);
        }
        initData();
        initView();
        return rootView;
    }

    private void initData() {
        xjList = new ArrayList<>();
        xjList.add(new ItemDataXJ("超人的学员天团","下午3:55","点赞了你的评论：哈哈哈哈",R.drawable.default_circle_head));
        xjList.add(new ItemDataXJ("小李爱学习","下午1:03","04-阿熙：[图片]",R.drawable.default_circle_head));
        xjList.add(new ItemDataXJ("古诗词交流","下午3:55","辉夜：当你看到移除酸梅干超人的时…",R.drawable.default_circle_head));
        xjList.add(new ItemDataXJ("Vzpominka","下午4:10","在吗？在吗？",R.drawable.default_circle_head));
        xjList.add(new ItemDataXJ("腾讯新闻","上午7:29","红衣女子称要跳楼 调整姿势不慎坠...",R.drawable.default_circle_head));
        xjList.add(new ItemDataXJ("腾讯新闻","上午7:29","红衣女子称要跳楼 调整姿势不慎坠...",R.drawable.default_circle_head));
    }

    private void initView() {

        recyclerView = rootView.findViewById(R.id.rv_xj);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerAdapter = new RecyclerAdapterXJ(xjList);
        recyclerView.setAdapter(recyclerAdapter);

    }
}
