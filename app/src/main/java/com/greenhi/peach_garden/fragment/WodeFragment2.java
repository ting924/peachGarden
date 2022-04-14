package com.greenhi.peach_garden.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.adapter.RecyclerAdapterFS;
import com.greenhi.peach_garden.adapter.RecyclerAdapterGZ;
import com.greenhi.peach_garden.item.ItemDataFS;
import com.greenhi.peach_garden.item.ItemUser;
import com.greenhi.peach_garden.utils.JsonParse;
import com.greenhi.peach_garden.utils.UserMessage;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class WodeFragment2 extends Fragment {
    private Context mContext;
    private View rootView;
    private int id;

    private RecyclerView recyclerView;
    private RecyclerAdapterFS recyclerAdapter;

    private List<ItemUser> users;

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
        initView();
        getUsers();
        return rootView;
    }

    private void initView() {
        id= UserMessage.getUserInfo(getContext());
        recyclerView = rootView.findViewById(R.id.rv_fs);
        ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void getUsers(){
        String url = "http://47.108.176.163:7777/focus/selectByFid?fid="+id;
        AsyncHttpClient client=new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] bytes) {
                try{
                    String json=new String(bytes,"utf-8");
                    users = JsonParse.Getuser(json);
                    recyclerAdapter = new RecyclerAdapterFS(mContext,users,id);
                    recyclerView.setAdapter(recyclerAdapter);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getContext(),"粉丝获取失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
