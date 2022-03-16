package com.greenhi.peach_garden.activity.poetry_hall;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.greenhi.peach_garden.R;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class TitleResultsActivity extends AppCompatActivity {
    private String baseURL="http://47.108.176.163:7777/poetry/";
    private ImageButton imageButton;
    private String p_name;
    private String dynasty;
    private String theme;
    private String grand;
    private String Tag="com.greenhi.peach_garden.activity.poetry_hall.TitleResultsActivity";
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
          List<String> list=(ArrayList<String>)msg.obj;
            showDatas(list);


        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titleresults);
        imageButton=findViewById(R.id.title_back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 finish();
            }
        });
        Intent intent=getIntent();
       p_name= intent.getStringExtra("p_name");
       dynasty=intent.getStringExtra("dynasty");
       grand=intent.getStringExtra("grand");
       theme=intent.getStringExtra("theme");
       getValidInput();

    }

    public void getValidInput(){
        if(p_name!=null){
            Toast.makeText(this,p_name,Toast.LENGTH_LONG).show();
            getDatas("getTitleByAuthor","author="+p_name);
        }
        else if(dynasty!=null){
            Toast.makeText(this,dynasty,Toast.LENGTH_LONG).show();
            getDatas("getTitleByDynasty","dynasty="+dynasty);
        }
        else if(grand!=null){
            Toast.makeText(this,grand,Toast.LENGTH_LONG).show();
            getDatas("getTitleByDiff","diff="+grand);
        }
        else if(theme!=null){
            Toast.makeText(this,theme,Toast.LENGTH_LONG).show();
            getDatas("getTitleByTheme","theme="+theme);
        }
    }

    public void showDatas(List<String>list){
        if(list.size()==0)
        {
           noDatas();
        }
        else
        {
            ArrayAdapter<String>adapter=new ArrayAdapter<String>(TitleResultsActivity.this, android.R.layout.simple_list_item_1,list);
            ListView listView=(ListView)findViewById(R.id.title_list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String title=list.get(i);
                    Intent intent1=new Intent(TitleResultsActivity.this,PoemContentActivity.class);
                    intent1.putExtra("title",title);
                    startActivity(intent1);
                }
            });}

    }

    public void getDatas(String api,String arg){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient okHttpClient=new OkHttpClient();
                    Request request=new Request.Builder()
                            .url(baseURL+api+"?"+arg)
                            .build();
                    okHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if(response.isSuccessful()){
                                String res=response.body().string();
                                try {
                                    JSONObject jsonObject=new JSONObject(res);
                                    String msg=jsonObject.getString("msg");
                                    if(jsonObject.getString("data").equals("null")){
                                        noDatas();
                                    }
                                    else{
                                    JSONArray content=jsonObject.getJSONArray("data");
                                    List<String> l=new ArrayList<String>();
                                    for (int i=0;i<content.length();i++){
                                        String s= content.getString(i);
                                        l.add(s);
                                        Log.i(Tag,s+i);
                                    }
                                    Message message=new Message();
                                    message.obj=l;
                                    handler.sendMessage(message);
                                    Log.i(Tag,msg);}
                                }
                                catch (JSONException e){
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
//                    Response response=null;
//                    response=okHttpClient.newCall(request).execute();

                }
                catch (Exception e){
                    e.printStackTrace();
                }

    }




}).start(); }
    public void noDatas(){
    Intent intent1=new Intent(this,NoneActivity.class);
    startActivity(intent1);
    finish();
}
}
