package com.greenhi.peach_garden.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.giftedcat.picture.lib.selector.MultiImageSelector;
import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.adapter.NineGridAdapterCZ;
import com.greenhi.peach_garden.retrofit.API;
import com.greenhi.peach_garden.retrofit.PermissionListener;
import com.greenhi.peach_garden.retrofit.PostResult;
import com.greenhi.peach_garden.retrofit.RetrofitManager;
import com.greenhi.peach_garden.retrofit.RetrofitUtil;
import com.greenhi.peach_garden.utils.OnAddPicturesListener;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ShizhaiFragment3 extends Fragment {
    private Context mContext;
    private View rootView;

    private static String TAG = "ShizhaiFragment3 retrofit";

    private static final int REQUEST_IMAGE = 2;
    private int maxNum = 9;

    Unbinder unbinder;

    @BindView(R.id.sz_cz_images)
    RecyclerView sz_cz_images;

    NineGridAdapterCZ adapter;
    List<String> mSelectList;

    private API mApi;
    private static PermissionListener mListener;

    public static ShizhaiFragment3 newInstance() {
        ShizhaiFragment3 fragment = new ShizhaiFragment3();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
        mApi = RetrofitManager.getRetrofit().create(API.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.shizhai_fragment3, container, false);
        }
        unbinder=ButterKnife.bind(this,rootView);
        mSelectList = new ArrayList<>();
        initView();
        return rootView;
    }

    private void initView() {
        sz_cz_images.setLayoutManager(new GridLayoutManager(mContext,3));
        adapter = new NineGridAdapterCZ(mContext, mSelectList, sz_cz_images);
        adapter.setMaxSize(maxNum);
        sz_cz_images.setAdapter(adapter);
        adapter.setOnAddPicturesListener(new OnAddPicturesListener() {
            @Override
            public void onAdd() {
                pickImage();
            }
        });
    }



    private void pickImage() {
        MultiImageSelector selector = MultiImageSelector.create(mContext);
        selector.showCamera(true);
        selector.count(maxNum);
        selector.multi();
        selector.origin(mSelectList);
        selector.start(this,REQUEST_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                List<String> select = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);

                //上传动态图片  id: 动态id
                uploadImgs(3,select);

                mSelectList.clear();
                mSelectList.addAll(select);
                adapter.notifyDataSetChanged();
                Log.d("mSelectList" ,"mSelectList"+mSelectList);
            }
        }
    }

    private void uploadImgs(Integer id, List<String> urls){
        List<MultipartBody.Part> parts = RetrofitUtil.createPartWithParams(urls);
        System.out.println(urls.toString());
        Map<String,Integer> params = new HashMap<>();
        params.put("id",id);
        Call<PostResult> task = mApi.postDynamicImgs(parts,params);
        enqueueTask(task);
    }

    private void enqueueTask(Call<PostResult> task) {
        task.enqueue(new Callback<PostResult>() {
            @Override
            public void onResponse(Call<PostResult> call, Response<PostResult> response) {
                int code = response.code();
                Log.e(TAG, "code --> " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    Log.e(TAG, "responseBody --> " + response.body());
                }
            }

            @Override
            public void onFailure(Call<PostResult> call, Throwable t) {
                Log.e(TAG, "onFailure --> " + t.toString());
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}