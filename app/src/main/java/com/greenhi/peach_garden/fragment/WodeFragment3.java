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
import com.greenhi.peach_garden.adapter.RecyclerAdapterGZ;
import com.greenhi.peach_garden.item.ItemDataGZ;

import java.util.ArrayList;
import java.util.List;

public class WodeFragment3 extends Fragment {
    private Context mContext;
    private View rootView;

    private RecyclerView recyclerView;
    private RecyclerAdapterGZ recyclerAdapter;

    private List<ItemDataGZ> gzList;

    public static WodeFragment3 newInstance(){
        Bundle args = new Bundle();
        WodeFragment3 fragment = new WodeFragment3();
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
            rootView = inflater.inflate(R.layout.wode_fragment3, container, false);
        }
        initData();
        initView();
        return rootView;
    }

    private void initData() {
        gzList = new ArrayList<>();
        gzList.add(new ItemDataGZ("吴晓军","中山大学","表表特约校园技术达人，我喜欢…",R.drawable.default_circle_head));
        gzList.add(new ItemDataGZ("罗佳馨","湖南大学","表表特约安利达人，每天给大家…",R.drawable.default_circle_head));
        gzList.add(new ItemDataGZ("祝云凯","浙江农林大学暨阳学院","喜欢你怎么办~",R.drawable.default_circle_head));
        gzList.add(new ItemDataGZ("常熙","贵州亚太职业学院","全民是一个有故事的昵称 圣普勒…",R.drawable.default_circle_head));
    }

    private void initView() {
        recyclerView = rootView.findViewById(R.id.rv_gz);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerAdapter = new RecyclerAdapterGZ(gzList);
        recyclerView.setAdapter(recyclerAdapter);
    }
}
