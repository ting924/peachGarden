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

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.adapter.RecyclerAdapterComment;
import com.greenhi.peach_garden.item.ItemComment;
import com.greenhi.peach_garden.item.ItemDataSC;
import com.greenhi.peach_garden.utils.InputTextMsgDialog;
import com.greenhi.peach_garden.utils.JsonParse;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class CommentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapterComment recyclerAdapter;
    private  InputTextMsgDialog inputTextMsgDialog;

    private List<ItemComment> commentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Intent intent =getIntent();
        int dynamicId = intent.getIntExtra("dynamicId",0);
        inputTextMsgDialog = new InputTextMsgDialog(this, R.style.dialog_center);
        inputTextMsgDialog.setmOnTextSendListener(new InputTextMsgDialog.OnTextSendListener() {
            @Override
            public void onTextSend(String msg) {
                //点击发送按钮后，回调此方法，msg为输入的值
            }
        });
        Button pinlun = findViewById(R.id.comment_pinlun);
        pinlun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputTextMsgDialog.show();
            }
        });
        recyclerView =(RecyclerView) findViewById(R.id.recycler_comment);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        getComment(dynamicId);
    }




    private void getComment(int dynamicId){
        String url = "http://47.108.176.163:7777/comment/selectByDid?did="+dynamicId;
        AsyncHttpClient client=new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] bytes) {
                try{
                    String json=new String(bytes,"utf-8");
                    commentList = JsonParse.GetCommment(json);
                    recyclerAdapter=new RecyclerAdapterComment(commentList);
                    recyclerView.setAdapter(recyclerAdapter);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("获取评论失败","失败");
            }
        });

    }
}