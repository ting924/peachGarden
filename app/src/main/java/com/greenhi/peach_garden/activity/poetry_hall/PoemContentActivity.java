package com.greenhi.peach_garden.activity.poetry_hall;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.utils.ActivityCollectorUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PoemContentActivity extends AppCompatActivity {
    private String baseURL = "http://47.108.176.163:7777/poetry/";
    private String Tag = "com.greenhi.peach_garden.activity.poetry_hall.PoemContentActivity";
    String title;

    class Poem {
        String author;
        String content;
        String notes;
        String transform;//翻译
        String appreciation;//赏析
        String creativeBackground;//创作背景
        String authorIntroduction;//作者介绍

        public Poem(String author, String content, String notes,String transform,String appreciation,String creativeBackground,String authorIntroduction) {
            this.author = author;
            this.content = content;
            this.notes = notes;
            this.transform=transform;
            this.appreciation=appreciation;
            this.authorIntroduction=authorIntroduction;
            this.creativeBackground=creativeBackground;
        }

    }

    ;
    TextView titleText;
    TextView contentText;
    TextView writterText;
    TextView annotationText;
    TextView fanyiText;
    TextView shangxiText;
    TextView czbjText;
    TextView zzjjText;
    ImageButton imageButton;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            Poem poem = (Poem) msg.obj;
            titleText.setText(title);
            contentText.setText(poem.content);
            writterText.setText(poem.author);
            annotationText.setText(poem.notes);
            fanyiText.setText(poem.transform);
            shangxiText.setText(poem.appreciation);
            czbjText.setText(poem.creativeBackground);
            zzjjText.setText(poem.authorIntroduction);

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollectorUtil.addActivity(this);
        setContentView(R.layout.activity_poemcontent);
        imageButton = findViewById(R.id.content_back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        Toast.makeText(this, title, Toast.LENGTH_LONG).show();
        titleText = findViewById(R.id.title);
        contentText = findViewById(R.id.content);
        writterText = findViewById(R.id.writter);
        annotationText = findViewById(R.id.annotation);
        fanyiText=findViewById(R.id.fanyi);
        shangxiText=findViewById(R.id.shangxi);
        czbjText=findViewById(R.id.czbj);
        zzjjText=findViewById(R.id.zzjj);
        getDatas("getOntByTitle", title);

    }

    public void getDatas(String api, String arg) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(baseURL + api + "?title=" + arg)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String res = response.body().string();
                            Log.i(Tag, res);
                            try {
                                JSONObject jsonObject = new JSONObject(res);
                                JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                                String author = jsonObject1.getString("author");
                                String content = jsonObject1.getString("content");
                                String notes = jsonObject1.getString("notes");
                                String transform=jsonObject1.getString("transform");
                                String appreciation=jsonObject1.getString("appreciation");
                                String creativeBackground=jsonObject1.getString("creativeBackground");
                                String authorIntroduction=jsonObject1.getString("authorIntroduction");
                                if (notes.equals("null")) {
                                    notes = "暂无注释哦！";
                                }
                                Log.i(Tag, author);
                                Log.i(Tag, content);
                                Log.i(Tag, notes);
                                Poem poem = new Poem(author, content, notes,transform,appreciation,creativeBackground,authorIntroduction);
                                Message message = new Message();
                                message.obj = poem;
                                handler.sendMessage(message);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                noDatas();
                            }

                        }
                    }
                });
            }
        }).start();
    }

    public void noDatas() {
        Intent intent1 = new Intent(this, NoneActivity.class);
        startActivity(intent1);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);
    }
}
