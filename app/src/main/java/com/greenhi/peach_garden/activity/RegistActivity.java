package com.greenhi.peach_garden.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.activity.poetry_hall.PoemContentActivity;
import com.greenhi.peach_garden.utils.ActivityCollectorUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
//import okhttp3.Request;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegistActivity extends Activity {
    private EditText et_username;
    private EditText et_password;
    private EditText ett_password;

    private Button bt_log;
    private Button bt_bos;
    private String URL = "http://47.108.176.163:7777/user/add";
    private String URL_get = "http://47.108.176.163:7777/user/selectOneByUid?uid=";

    private void postAsynHttp(String user, String password) throws JSONException {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        String formBody = "[ uid=" + user + ",password=" + password + "]";
        System.out.println(formBody);
        JSONObject EventTraceInput = new JSONObject();
        EventTraceInput.put("uid", user);
        EventTraceInput.put("password", password);
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        final okhttp3.Request request = new okhttp3.Request.Builder()
                .url(URL)
                .post(RequestBody.create(mediaType, String.valueOf(EventTraceInput)))
                .build();
        System.out.println(user);
        System.out.println(password);
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                System.out.println(str);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollectorUtil.addActivity(this);
        //??????????????????????????????????????????
        setContentView(R.layout.activity_regist);

        //????????????????????????????????????
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        ett_password = (EditText) findViewById(R.id.ett_password);

        bt_log = (Button) findViewById(R.id.bt_log);
        bt_bos = (Button) findViewById(R.id.bt_bos);


        //?????????????????????????????????????????????????????????????????????
        bt_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //???????????????????????????
                String strUsername = et_username.getText().toString();
                String strPassword = et_password.getText().toString();
                String okPassword = ett_password.getText().toString();

                //????????????????????????????????????????????????????????????????????????????????????????????????admin???
                if (strUsername.equals("")) {
                    Toast.makeText(RegistActivity.this, "?????????????????????", Toast.LENGTH_SHORT).show();
                } else {

                    OkHttpClient okHttpClient = new OkHttpClient();
                    final Request request = new Request.Builder()
                            .url(URL_get + strUsername)
                            .get()//????????????GET?????????????????????
                            .build();
                    Call call = okHttpClient.newCall(request);

                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                       }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                            String res = response.body().string();
                            try {
                                JSONObject jsonObject = new JSONObject(res);
                                if (jsonObject.getString("code").equals("104")) {
                                    if (strPassword.equals("")) {
                                        Toast.makeText(RegistActivity.this, "??????????????????", Toast.LENGTH_SHORT).show();
                                    } else if (!strPassword.equals("")) {
                                        if (okPassword.equals("")) {
                                            Toast.makeText(RegistActivity.this, "????????????????????????", Toast.LENGTH_SHORT).show();
                                        } else if (!strPassword.equals(okPassword)) {
                                            Toast.makeText(RegistActivity.this, "?????????????????????????????????", Toast.LENGTH_SHORT).show();
                                        } else {
                                            try {
                                                postAsynHttp(et_username.getText().toString(), et_password.getText().toString());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            Intent intent = new Intent(RegistActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                            Looper.prepare();
                                            Toast.makeText(RegistActivity.this, "???????????????", Toast.LENGTH_SHORT).show();
                                            Looper.loop();
                                        }
                                    }
                                } else {
                                    Looper.prepare();
                                    Toast.makeText(RegistActivity.this, "??????????????????", Toast.LENGTH_SHORT).show();
                                    Looper.loop();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
        //?????????????????????????????????????????????????????????????????????
        bt_bos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);
    }
}

//