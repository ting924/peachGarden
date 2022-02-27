package com.greenhi.peach_garden.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.adapter.RecyclerAdapterJingXuan;
import com.greenhi.peach_garden.item.ItemDataSZ;

import java.util.ArrayList;
import java.util.List;


public class ShizhaiFragment2 extends Fragment {

    private Context mContext;
    private View rootView;

    private RecyclerView recyclerView;
    private RecyclerAdapterJingXuan recyclerAdapter;

    private List<ItemDataSZ> jxList;

    public static ShizhaiFragment2 newInstance(){
        Bundle args = new Bundle();
        ShizhaiFragment2 fragment = new ShizhaiFragment2();
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
            rootView = inflater.inflate(R.layout.shizhai_fragment2, container, false);
        }
        initData();
        initView();
        return rootView;
    }

    private void initData() {
        jxList = new ArrayList<>();
        jxList.add(new ItemDataSZ("小李","今天 12:24","不采而佩亦曾伤,春风座客君子堂.\n" +
                "笔墨千古香飘尽,一片芳心锁幽窗.",R.drawable.default_circle_head,300,66));
        jxList.add(new ItemDataSZ("小刘","今天 16:35","生活得最有意义的人，并不就是年岁活" +
                "得最大的人，而是对生活最有感受的人。",R.drawable.default_circle_head,222,44));
        jxList.add(new ItemDataSZ("用户名","今天 16:35","不采而佩亦曾伤,春风座客君子堂.\n" +
                "笔墨千古香飘尽,一片芳心锁幽窗.",R.drawable.default_circle_head,300,66));
        jxList.add(new ItemDataSZ("用户名","今天 16:35","不采而佩亦曾伤,春风座客君子堂.\n" +
                "笔墨千古香飘尽,一片芳心锁幽窗.",R.drawable.default_circle_head,300,66));
    }

    private void initView() {
        recyclerView = rootView.findViewById(R.id.sz_jx);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerAdapter = new RecyclerAdapterJingXuan(jxList);
        recyclerView.setAdapter(recyclerAdapter);
    }
}