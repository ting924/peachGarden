package com.greenhi.peach_garden.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.adapter.RecyclerAdapterComment;
import com.greenhi.peach_garden.item.ItemComment;
import com.greenhi.peach_garden.item.ItemDataSC;
import com.greenhi.peach_garden.utils.ActivityCollectorUtil;
import com.greenhi.peach_garden.utils.InputTextMsgDialog;
import com.greenhi.peach_garden.utils.JsonParse;
import com.greenhi.peach_garden.utils.UserMessage;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CommentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapterComment recyclerAdapter;
    private InputTextMsgDialog inputTextMsgDialog;

    private List<ItemComment> commentList;

    private String URL = "http://47.108.176.163:7777/comment/add";
    private int uid;
    private int dynamicId;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollectorUtil.addActivity(this);
        setContentView(R.layout.activity_comment);
        uid = UserMessage.getUserInfo(this);
        Intent intent = getIntent();
        dynamicId = intent.getIntExtra("dynamicId", 0);
        inputTextMsgDialog = new InputTextMsgDialog(this, R.style.dialog_center);
        inputTextMsgDialog.setmOnTextSendListener(new InputTextMsgDialog.OnTextSendListener() {
            @Override
            public void onTextSend(String msg) {
                //点击发送按钮后，回调此方法，msg为输入的值
                inputTextMsgDialog.dismiss();
                try {
                    postAsynHttp(uid, dynamicId, msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Button pinlun = findViewById(R.id.comment_pinlun);
        pinlun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputTextMsgDialog.show();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recycler_comment);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        getComment(dynamicId);
    }


    private void getComment(int dynamicId) {
        String url = "http://47.108.176.163:7777/comment/selectByDid?did=" + dynamicId;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] bytes) {
                try {
                    String json = new String(bytes, "utf-8");
                    commentList = JsonParse.GetCommment(json);
                    recyclerAdapter = new RecyclerAdapterComment(commentList);
                    recyclerView.setAdapter(recyclerAdapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("获取评论失败", "失败");
            }
        });

    }

    private void postAsynHttp(Integer uid, Integer dynamicId, String commentContent) throws JSONException {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        JSONObject EventTraceInput = new JSONObject();
        EventTraceInput.put("uid", uid);
        EventTraceInput.put("dynamicId", dynamicId);
        EventTraceInput.put("commentContent", commentContent);
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        final okhttp3.Request request = new okhttp3.Request.Builder()
                .url(URL)
                .post(RequestBody.create(mediaType, String.valueOf(EventTraceInput)))
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getApplicationContext(), "请求失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        position = recyclerAdapter.getItemCount();
                        Log.d("position", position + "");
                        Toast.makeText(getApplicationContext(), "请求成功", Toast.LENGTH_SHORT).show();
                        addCommentNumber();
                        recyclerAdapter.add(position, new ItemComment(0, uid, dynamicId, commentContent, null, null));
                        recyclerView.scrollToPosition(recyclerAdapter.getItemCount());
                    }
                });
            }
        });
    }

    private void addCommentNumber() {
        String url = "http://47.108.176.163:7777/dynamic/addCommById?id=" + dynamicId + "&add=true";
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] bytes) {
                try {
                    Log.d("ccomment", "评论数+1成功");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("ccomment", "评论数+1失败");
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);
    }
}