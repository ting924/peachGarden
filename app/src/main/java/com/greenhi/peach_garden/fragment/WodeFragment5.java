package com.greenhi.peach_garden.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.adapter.RecyclerAdapterGuanZhu;
import com.greenhi.peach_garden.adapter.RecyclerAdapterSZ;
import com.greenhi.peach_garden.item.ItemDataSZ;
import com.greenhi.peach_garden.item.ItemDynamic;
import com.greenhi.peach_garden.utils.JsonParse;
import com.greenhi.peach_garden.utils.UserMessage;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class WodeFragment5 extends Fragment {
    private Context mContext;
    private View rootView;
    private int id;

    private RecyclerView recyclerView;
    private RecyclerAdapterSZ recyclerAdapter;

    private List<ItemDataSZ> szList;
    private List<ItemDynamic> dynamics;

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
                "笔墨千古香飘尽,一片芳心锁幽窗.",R.drawable.default_circle_head,2,300,66));
        szList.add(new ItemDataSZ("用户名","今天 16:35","生活得最有意义的人，并不就是年岁活" +
                "得最大的人，而是对生活最有感受的人。",R.drawable.default_circle_head,2,222,44));
        szList.add(new ItemDataSZ("用户名","今天 16:35","不采而佩亦曾伤,春风座客君子堂.\n" +
                "笔墨千古香飘尽,一片芳心锁幽窗.",R.drawable.default_circle_head,2,300,66));
        szList.add(new ItemDataSZ("用户名","今天 16:35","不采而佩亦曾伤,春风座客君子堂.\n" +
                "笔墨千古香飘尽,一片芳心锁幽窗.",R.drawable.default_circle_head,2,300,66));
    }

    private void initView() {
        id= UserMessage.getUserInfo(getContext());
        recyclerView = rootView.findViewById(R.id.rv_sz);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        getMyDynamics();
    }

    private void getMyDynamics(){
        String url = "http://47.108.176.163:7777/dynamic/selectByUid?uid="+id;
        AsyncHttpClient client=new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] bytes) {
                try{
                    String json=new String(bytes,"utf-8");
                    dynamics = JsonParse.Getdynamic(json);
                    recyclerAdapter = new RecyclerAdapterSZ(dynamics);
                    recyclerView.setAdapter(recyclerAdapter);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getContext(),"动态获取失败",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
