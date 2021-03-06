package com.greenhi.peach_garden.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.adapter.RecyclerAdapterGuanZhu;
import com.greenhi.peach_garden.adapter.RecyclerAdapterJingXuan;
import com.greenhi.peach_garden.animator.MyItemAnimator;
import com.greenhi.peach_garden.item.ItemDataSZ;
import com.greenhi.peach_garden.item.ItemDynamic;
import com.greenhi.peach_garden.item.RecordsDTO;
import com.greenhi.peach_garden.utils.JsonParse;
import com.greenhi.peach_garden.utils.UserMessage;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class ShizhaiFragment1 extends Fragment implements RecyclerAdapterGuanZhu.OnMyItemClickListener {

    private Context mContext;
    private View rootView;
    private int page = 0;
    private int pageSize = 8;
    private int id;

    private RecyclerView recyclerView;
    private RecyclerAdapterGuanZhu recyclerAdapter;

    private List<RecordsDTO> jxList;

    private List<ItemDynamic> dynamics;

    public static ShizhaiFragment1 newInstance(){
        Bundle args = new Bundle();
        ShizhaiFragment1 fragment = new ShizhaiFragment1();
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
            rootView = inflater.inflate(R.layout.shizhai_fragment1, container, false);
        }
        initView();
        return rootView;
    }


    private void initView() {
        id= UserMessage.getUserInfo(getContext());
        recyclerView = rootView.findViewById(R.id.sz_gz);

        // ????????????
//        DefaultItemAnimator animator = new DefaultItemAnimator(); // RecyclerView?????????????????????
        MyItemAnimator animator = new MyItemAnimator(); // ???????????????????????????
        animator.setRemoveDuration(2000); // ???????????????????????????
        animator.setMoveDuration(2000); // ???????????????????????????
        animator.setAddDuration(2000); // ???????????????????????????
        animator.setSupportsChangeAnimations(true); // ????????????????????????????????????
        animator.setChangeDuration(2000); // ???????????????????????????


        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(animator);
        ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        getFocusDynamics();
    }

    private void getFocusDynamics(){
        String url = "http://47.108.176.163:7777/dynamic/selectFocusByUidPaging?uid="+id+"&pageNo=1&pageSize=2";
        AsyncHttpClient client=new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] bytes) {
                try{
                    String json=new String(bytes,"utf-8");
                    dynamics =JsonParse.Getdynamic(json);
                    System.out.println("dynamics----> "+dynamics.toString());
                    if(dynamics!=null&&dynamics.size()>0){
                        recyclerAdapter = new RecyclerAdapterGuanZhu(mContext,dynamics);
                        recyclerView.setAdapter(recyclerAdapter);
                    }
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

    @Override
    public void onMyItemClick(RecyclerView parent, View view, int position, ItemDynamic data) {
//        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
        //adapter.remove(position); // ????????????
        //adapter.add(position,"New"); // ????????????
        recyclerAdapter.change(position,data); // ????????????
    }
}