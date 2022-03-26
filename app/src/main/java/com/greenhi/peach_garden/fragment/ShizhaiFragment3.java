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

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.giftedcat.picture.lib.selector.MultiImageSelector;
import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.adapter.NineGridAdapterCZ;
import com.greenhi.peach_garden.item.ItemComment;
import com.greenhi.peach_garden.retrofit.API;
import com.greenhi.peach_garden.retrofit.PermissionListener;
import com.greenhi.peach_garden.retrofit.PostResult;
import com.greenhi.peach_garden.retrofit.RetrofitManager;
import com.greenhi.peach_garden.retrofit.RetrofitUtil;
import com.greenhi.peach_garden.utils.OnAddPicturesListener;
import com.greenhi.peach_garden.utils.UserMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ShizhaiFragment3 extends Fragment {
    private static final int FABUSUCCESS=1;
    private Context mContext;
    private View rootView;
    private int uid;

    private static String TAG = "ShizhaiFragment3 retrofit";

    private static final int REQUEST_IMAGE = 2;
    private int maxNum = 9;
    private int dynamicId;


    Unbinder unbinder;

    private TextView btnfabu;

    @BindView(R.id.sz_cz_edittext)
    EditText editText;

    @BindView(R.id.sz_cz_images)
    RecyclerView sz_cz_images;

    NineGridAdapterCZ adapter;
    List<String> mSelectList;

    private API mApi;

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
        //照片数组
        mSelectList = new ArrayList<>();
        //登录用户id
        uid= UserMessage.getUserInfo(getContext());
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnfabu = view.findViewById(R.id.btnfabu);
        btnfabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dynamicContent= editText.getText().toString();
                try {
                    postAsynHttp(uid,dynamicContent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case FABUSUCCESS:
                    if (editText != null) {
                        //传递消息obj
                        editText.setText("");
                        mSelectList.clear();
                        adapter.notifyDataSetChanged();
                    }
                    break;
            }
            return false;
        }
    });

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
                mSelectList.clear();
                mSelectList.addAll(select);
                adapter.notifyDataSetChanged();
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
        Log.d("fabu","发布成功");
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
        if(handler!=null){
            //关闭activity时，移除消息
            handler.removeMessages(FABUSUCCESS);
            handler = null;
        }
    }

    private void postAsynHttp(Integer uid,String dynamicContent) throws JSONException {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        JSONObject EventTraceInput =new JSONObject();
        EventTraceInput.put("uid",uid);
        EventTraceInput.put("dynamicContent", dynamicContent);
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        final okhttp3.Request request = new okhttp3.Request.Builder()
                .url("http://47.108.176.163:7777/dynamic/add")
                .post(RequestBody.create(mediaType, String.valueOf(EventTraceInput)))
                .build();
        okhttp3.Call call = mOkHttpClient.newCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Toast.makeText(getContext(), "动态发表失败", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                String str = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    JSONObject jsonObject2=jsonObject.getJSONObject("data");
                    dynamicId=jsonObject2.getInt("id");
                    Log.d("fabu",""+dynamicId);
                    //上传动态图片  id: 动态id
                    uploadImgs(dynamicId,mSelectList);

                    Log.d("fabu",""+mSelectList);
                    //obtain享元模式
                    Message message = Message.obtain(handler);
                    //what相当于标记
                    message.what = FABUSUCCESS;
                    //obj传数据
                    message.obj = new String("更新UI");
                    //普通发送message
                    handler.sendMessage(message);
                    Looper.prepare();
                    Toast.makeText(getContext(), "动态发表成功", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }
}