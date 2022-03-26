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

    private List<RecordsDTO> jxList;

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

        // 动画效果
        //DefaultItemAnimator animator = new DefaultItemAnimator(); // RecyclerView默认的属性动画
        MyItemAnimator animator = new MyItemAnimator(); // 我们自己的属性动画
        animator.setRemoveDuration(2000); // 删除动画的延迟时间
        animator.setMoveDuration(2000); // 移动动画的延迟时间
        animator.setAddDuration(2000); // 增加动画的延迟时间
        animator.setSupportsChangeAnimations(true); // 还要改变动画需要设置支持
        animator.setChangeDuration(2000); // 改变动画的延迟时间

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(animator);
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        getAllDynamics(true);
        refreshLayout = rootView.findViewById(R.id.srl);
        // 下拉刷新
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            page = 0;
            getAllDynamics(true);
        });
        //上拉加载
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            //double totalPage=Math.ceil(((double)totalNum)/pageSize);
            getAllDynamics(false);
        });
    }

    private void getAllDynamics(final boolean isRefresh) {
        page++;
        String url = "http://47.108.176.163:7777/dynamic/getAllPaging?pageNo=" + page + "&pageSize=" + pageSize;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] bytes) {
                try {
                    String json = new String(bytes, "utf-8");
                    jxList = JsonParse.GetAllDynamic(json);
                    totalNum = JsonParse.GetTotalPage(json);
                    Log.d("print", json);

                    if (jxList != null && jxList.size() > 0) {
                        if (page == 1) {
                            recyclerAdapter = new RecyclerAdapterJingXuan(mContext,jxList, id);
                            recyclerView.setAdapter(recyclerAdapter);
                        } else {
                            recyclerAdapter.addAllData(jxList);
                        }
                        if (isRefresh) {
                            refreshLayout.finishRefresh();//刷新完成(关闭刷新动画)
                        } else {
                            refreshLayout.finishLoadMore();//加载更多完成并提示(关闭刷新动画)
                        }
                    } else {
                        //加载失败
                        if (isRefresh) {
                            refreshLayout.finishRefresh(false);//刷新失败
                        } else {
                            refreshLayout.finishLoadMore(false);//加载更多失败
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (isRefresh) {
                    refreshLayout.finishRefresh(false);//刷新失败
                } else {
                    refreshLayout.finishLoadMore(false);//加载更多失败
                }

            }
        });
    }
}