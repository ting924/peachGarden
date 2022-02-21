package com.greenhi.peach_garden.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.adapter.RecyclerAdapterSC;
import com.greenhi.peach_garden.item.ItemDataSC;

import java.util.ArrayList;
import java.util.List;

public class WodeFragment1 extends Fragment {

    private Context mContext;
    private View rootView;

    private RecyclerView recyclerView;
    private RecyclerAdapterSC recyclerAdapter;

    private List<ItemDataSC> scList;

    public static WodeFragment1 newInstance(){
        Bundle args = new Bundle();
        WodeFragment1 fragment = new WodeFragment1();
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
            rootView = inflater.inflate(R.layout.wode_fragment1, container, false);
        }
        initData();
        initView();
        return rootView;
    }

    private void initData() {
        scList = new ArrayList<>();
        scList.add(new ItemDataSC("今日视频教会你掌握隶书技巧","李老师",R.drawable.iv_shoucang1,
                R.drawable.iv_default_head_small));
        scList.add(new ItemDataSC("你不知道的张大千","杨老师",R.drawable.iv_shoucang2,
                R.drawable.iv_default_head_small));
        scList.add(new ItemDataSC("今日视频教会你掌握隶书技巧","李老师",R.drawable.iv_shoucang3,
                R.drawable.iv_default_head_small));
        scList.add(new ItemDataSC("你不知道的张大千","杨老师",R.drawable.iv_shoucang4,
                R.drawable.iv_default_head_small));
        scList.add(new ItemDataSC("你不知道的张大千","杨老师",R.drawable.iv_shoucang4,
                R.drawable.iv_default_head_small));
    }

    private void initView() {
        recyclerView = rootView.findViewById(R.id.rv_sc);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,4);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerAdapter = new RecyclerAdapterSC(scList);
        recyclerView.setAdapter(recyclerAdapter);
    }
}
