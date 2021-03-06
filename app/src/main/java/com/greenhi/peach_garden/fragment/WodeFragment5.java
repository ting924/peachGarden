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
        initView();
        return rootView;
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
                    recyclerAdapter = new RecyclerAdapterSZ(mContext,dynamics,id);
                    recyclerView.setAdapter(recyclerAdapter);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getContext(),"??????????????????",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
