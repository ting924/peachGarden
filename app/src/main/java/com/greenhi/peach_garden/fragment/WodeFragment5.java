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
import com.greenhi.peach_garden.adapter.RecyclerAdapterSZ;
import com.greenhi.peach_garden.item.ItemDataSZ;

import java.util.ArrayList;
import java.util.List;

public class WodeFragment5 extends Fragment {
    private Context mContext;
    private View rootView;

    private RecyclerView recyclerView;
    private RecyclerAdapterSZ recyclerAdapter;

    private List<ItemDataSZ> szList;

    public static WodeFragment5 newInstance(){
        Bundle args = new Bundle();
        WodeFragment5 fragment = new WodeFragment5();
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
            rootView = inflater.inflate(R.layout.wode_fragment5, container, false);
        }
        initData();
        initView();
        return rootView;
    }

    private void initData() {
        szList = new ArrayList<>();
        szList.add(new ItemDataSZ("用户名","今天 16:35","不采而佩亦曾伤,春风座客君子堂.\n" +
                "笔墨千古香飘尽,一片芳心锁幽窗.",R.drawable.default_circle_head,300,66));
        szList.add(new ItemDataSZ("用户名","今天 16:35","生活得最有意义的人，并不就是年岁活" +
                "得最大的人，而是对生活最有感受的人。",R.drawable.default_circle_head,222,44));
        szList.add(new ItemDataSZ("用户名","今天 16:35","不采而佩亦曾伤,春风座客君子堂.\n" +
                "笔墨千古香飘尽,一片芳心锁幽窗.",R.drawable.default_circle_head,300,66));
        szList.add(new ItemDataSZ("用户名","今天 16:35","不采而佩亦曾伤,春风座客君子堂.\n" +
                "笔墨千古香飘尽,一片芳心锁幽窗.",R.drawable.default_circle_head,300,66));
    }

    private void initView() {
        recyclerView = rootView.findViewById(R.id.rv_sz);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerAdapter = new RecyclerAdapterSZ(szList);
        recyclerView.setAdapter(recyclerAdapter);
    }
}
