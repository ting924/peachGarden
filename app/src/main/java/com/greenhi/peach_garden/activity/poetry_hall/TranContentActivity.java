package com.greenhi.peach_garden.activity.poetry_hall;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.fragment.ShiguanFragment3;
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
    private Button english;
    private Button french;
    private Button russian;
    private Button japanese;
    private Button korean;
    private Boolean english_state=true;
    private Boolean french_state=false;
    private Boolean russian_state=false;
    private Boolean japanese_state=false;
    private Boolean korean_state=false;
    public static String content_japanese = "";
    public static String content_english = "";
    public static String content_french = "";
    public static String content_korean = "";
    public static String content_russian = "";

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
//        english.setBackgroundColor(Color.parseColor("#FFD12324"));
//        english.setTextColor(Color.parseColor("#FFFFFFFF"));
        english = findViewById(R.id.English);
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(content_english);
                english.setBackgroundColor(Color.parseColor("#FFD12324"));
                french.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                russian.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                japanese.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                korean.setBackgroundColor(Color.parseColor("#FFFFFFFF"));

                english.setTextColor(Color.parseColor("#FFFFFFFF"));
                french.setTextColor(Color.parseColor("#FF000000"));
                russian.setTextColor(Color.parseColor("#FF000000"));
                japanese.setTextColor(Color.parseColor("#FF000000"));
                korean.setTextColor(Color.parseColor("#FF000000"));
            }
        });
        french = findViewById(R.id.French);
        french.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(content_french);
                english.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                french.setBackgroundColor(Color.parseColor("#FFD12324"));
                russian.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                japanese.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                korean.setBackgroundColor(Color.parseColor("#FFFFFFFF"));

                english.setTextColor(Color.parseColor("#FF000000"));
                french.setTextColor(Color.parseColor("#FFFFFFFF"));
                russian.setTextColor(Color.parseColor("#FF000000"));
                japanese.setTextColor(Color.parseColor("#FF000000"));
                korean.setTextColor(Color.parseColor("#FF000000"));
            }
        });
        russian = findViewById(R.id.Russian);
        russian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(content_russian);
                english.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                french.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                russian.setBackgroundColor(Color.parseColor("#FFD12324"));
                japanese.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                korean.setBackgroundColor(Color.parseColor("#FFFFFFFF"));

                english.setTextColor(Color.parseColor("#FF000000"));
                french.setTextColor(Color.parseColor("#FF000000"));
                russian.setTextColor(Color.parseColor("#FFFFFFFF"));
                japanese.setTextColor(Color.parseColor("#FF000000"));
                korean.setTextColor(Color.parseColor("#FF000000"));
            }
        });
        japanese = findViewById(R.id.Japan);
        japanese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(content_japanese);
                english.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                french.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                russian.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                japanese.setBackgroundColor(Color.parseColor("#FFD12324"));
                korean.setBackgroundColor(Color.parseColor("#FFFFFFFF"));

                english.setTextColor(Color.parseColor("#FF000000"));
                french.setTextColor(Color.parseColor("#FF000000"));
                russian.setTextColor(Color.parseColor("#FF000000"));
                japanese.setTextColor(Color.parseColor("#FFFFFFFF"));
                korean.setTextColor(Color.parseColor("#FF000000"));
            }
        });
        korean = findViewById(R.id.Korean);
        korean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(content_korean);
                english.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                french.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                russian.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                japanese.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                korean.setBackgroundColor(Color.parseColor("#FFD12324"));

                english.setTextColor(Color.parseColor("#FF000000"));
                french.setTextColor(Color.parseColor("#FF000000"));
                russian.setTextColor(Color.parseColor("#FF000000"));
                japanese.setTextColor(Color.parseColor("#FF000000"));
                korean.setTextColor(Color.parseColor("#FFFFFFFF"));
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
                                System.out.println(jsonObject1);
                                content_japanese = jsonObject1.getString("japanese");
                                content_russian = jsonObject1.getString("russian");
                                content_english = jsonObject1.getString("english");
                                content_french = jsonObject1.getString("french");
                                content_korean = jsonObject1.getString("korean");
                                String content = content_english+content_french+content_russian+content_japanese+content_korean;
                                if (!content.equals("null")) {
                                    Message message = new Message();
                                    message.obj = content_english;
                                    handler.sendMessage(message);
                                } else {
                                    Log.i(Tag, "暂无此诗文！");
                                    Message message = new Message();
                                    message.obj = "暂无此诗文！";
                                    handler.sendMessage(message);
                                }
                            } catch (JSONException e) {
                                Log.i(Tag, "暂无此诗文！");
                                Message message = new Message();
                                message.obj = "暂无此诗文！";
                                handler.sendMessage(message);
                            }
                        }
                    }
                });
            }
        }).start();
    }

    public void noDatas() {
//        Intent intent2 = new Intent(this, NoneActivity.class);
//        startActivity(intent2);
//        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);
    }
}
