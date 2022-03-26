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

public class TranContentActivity extends AppCompatActivity {
    private ImageButton imageButton;
    private String language;
    private String title;
    private TextView textView;
    private String baseURL = "http://47.108.176.163:7777/poetry/";
    private String Tag = "com.greenhi.peach_garden.activity.poetry_hall.TranContentActivity";
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            String content = (String) msg.obj;
            if (TextUtils.isEmpty(content)) {
                noDatas();
            } else {
                textView.setText(content);
            }

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollectorUtil.addActivity(this);
        setContentView(R.layout.activity_trancontent);
        imageButton = findViewById(R.id.trancontent_back);
        textView = findViewById(R.id.tracontent);
        Intent intent = getIntent();
        language = intent.getStringExtra("language");
        title = intent.getStringExtra("title");
        Toast.makeText(this, language + " " + title, Toast.LENGTH_LONG).show();
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getDatas("getTransByTitle", title);
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
                            try {
                                JSONObject jsonObject = new JSONObject(res);
                                JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                                String content = jsonObject1.getString(language);
                                if (!content.equals("null")) {
                                    Log.i(Tag, content);
                                    Message message = new Message();
                                    message.obj = content;
                                    handler.sendMessage(message);
                                } else {
                                    noDatas();
                                }
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
