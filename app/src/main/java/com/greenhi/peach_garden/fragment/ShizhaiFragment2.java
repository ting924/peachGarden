package com.greenhi.peach_garden.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class ShizhaiFragment2 extends Fragment {

    private Context mContext;
    private View rootView;
    private SmartRefreshLayout refreshLayout;
    private int page = 0;
    private int pageSize = 2;
    private int id;
    private int totalNum;

    private RecyclerView recyclerView;
    private RecyclerAdapterJingXuan recyclerAdapter;

    private List<ItemDynamic> jxList;

    public static ShizhaiFragment2 newInstance() {
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
        initView();
        return rootView;
    }


    private void initView() {
        id = UserMessage.getUserInfo(getContext());
        recyclerView = rootView.findViewById(R.id.sz_jx);

        // ????????????
        //DefaultItemAnimator animator = new DefaultItemAnimator(); // RecyclerView?????????????????????
        MyItemAnimator animator = new MyItemAnimator(); // ???????????????????????????
        animator.setRemoveDuration(2000); // ???????????????????????????
        animator.setMoveDuration(2000); // ???????????????????????????
        animator.setAddDuration(2000); // ???????????????????????????
        animator.setSupportsChangeAnimations(true); // ????????????????????????????????????
        animator.setChangeDuration(2000); // ???????????????????????????

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(animator);
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        getAllDynamics(true);
        refreshLayout = rootView.findViewById(R.id.srl);
        // ????????????
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            page = 0;
            getAllDynamics(true);
        });
        //????????????
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            //double totalPage=Math.ceil(((double)totalNum)/pageSize);
            getAllDynamics(false);
        });
    }

    private void getAllDynamics(final boolean isRefresh) {
        page++;
        String url = "http://47.108.176.163:7777/dynamic/selectPaging?pageNo=" + page + "&pageSize=" + pageSize;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] bytes) {
                try {
                    String json = new String(bytes, "utf-8");
                    jxList = JsonParse.Getdynamic(json);
//                    totalNum = JsonParse.GetTotalPage(json);
                    Log.d("print", json);

                    if (jxList != null && jxList.size() > 0) {
                        if (page == 1) {
                            recyclerAdapter = new RecyclerAdapterJingXuan(mContext,jxList,id);
                            recyclerView.setAdapter(recyclerAdapter);
                        } else {
                            recyclerAdapter.addAllData(jxList);
                        }
                        if (isRefresh) {
                            refreshLayout.finishRefresh();//????????????(??????????????????)
                        } else {
                            refreshLayout.finishLoadMore();//???????????????????????????(??????????????????)
                        }
                    } else {
                        //????????????
                        if (isRefresh) {
                            refreshLayout.finishRefresh(false);//????????????
                        } else {
                            refreshLayout.finishLoadMore(false);//??????????????????
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (isRefresh) {
                    refreshLayout.finishRefresh(false);//????????????
                } else {
                    refreshLayout.finishLoadMore(false);//??????????????????
                }

            }
        });
    }
}