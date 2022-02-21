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
import com.greenhi.peach_garden.adapter.RecyclerAdapterFS;
import com.greenhi.peach_garden.item.ItemDataFS;

import java.util.ArrayList;
import java.util.List;

public class WodeFragment2 extends Fragment {
    private Context mContext;
    private View rootView;

    private RecyclerView recyclerView;
    private RecyclerAdapterFS recyclerAdapter;

    private List<ItemDataFS> fsList;

    public static WodeFragment2 newInstance(){
        Bundle args = new Bundle();
        WodeFragment2 fragment = new WodeFragment2();
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
            rootView = inflater.inflate(R.layout.wode_fragment2, container, false);
        }
        initData();
        initView();
        return rootView;
    }

    private void initData() {
        fsList = new ArrayList<>();
        fsList.add(new ItemDataFS("吴铭","溜溜猫：学习效率也降低了",R.drawable.default_circle_head));
        fsList.add(new ItemDataFS("诗词鉴赏","已认证官方账号",R.drawable.default_circle_head));
        fsList.add(new ItemDataFS("Deep Tech深科技","已认证官方账号",R.drawable.default_circle_head));
        fsList.add(new ItemDataFS("花和尚","知乎/今日头条“花和尚”，十余年电商",R.drawable.default_circle_head));
    }

    private void initView() {
        recyclerView = rootView.findViewById(R.id.rv_fs);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerAdapter = new RecyclerAdapterFS(fsList);
        recyclerView.setAdapter(recyclerAdapter);
    }
}
