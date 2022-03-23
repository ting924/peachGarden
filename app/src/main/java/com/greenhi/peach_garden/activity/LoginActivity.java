package com.greenhi.peach_garden.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.activity.poetry_hall.PoemContentActivity;
import com.greenhi.peach_garden.utils.UserMessage;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends Activity {
    private EditText et_username;
    private EditText et_password;
    private TextView newUser;
    private TextView forgetPassword;
    private Button bt_log;
    private Button bt_bos;
    private String URL="http://47.108.176.163:7777/user/selectOneByUid?uid=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //利用布局资源文件设置用户界面
        setContentView(R.layout.activity_login);

        //通过资源标识获得控件实例
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        newUser = (TextView) findViewById(R.id.newUser);
        forgetPassword = (TextView) findViewById(R.id.forgetPassword);
        bt_log = (Button) findViewById(R.id.bt_log);
        bt_bos = (Button) findViewById(R.id.bt_bos);
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
            }
        });
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this,"请联系管理员！",Toast.LENGTH_SHORT).show();
            }
        });
        //给登录按钮注册监听器，实现监听器接口，编写事件
        bt_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 //获取用户输入的数据
                String strUsername = et_username.getText().toString();
                String strPassword = et_password.getText().toString();
                OkHttpClient okHttpClient = new OkHttpClient();
                final Request request = new Request.Builder()
                        .url(URL+strUsername)
                        .get()//默认就是GET请求，可以不写
                        .build();
                Call call = okHttpClient.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String res=response.body().string();
                       try {
                           JSONObject jsonObject = new JSONObject(res);
                           if (jsonObject.getString("code").equals("104")){
                               Looper.prepare();
                               Toast.makeText(LoginActivity.this,"用户名或密码错误！",Toast.LENGTH_SHORT).show();
                               Looper.loop();
                           }else{
                               JSONObject jsonObject2=jsonObject.getJSONObject("data");
                               String password=jsonObject2.getString("password");
                               if(strUsername.equals(strUsername) && strPassword.equals(password)){
                                   Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                   startActivity(intent);
                                   int id=jsonObject2.getInt("id");
                                   UserMessage.saveUserInfo(LoginActivity.this,id);
                                   Looper.prepare();
                                   Toast toast= Toast.makeText(LoginActivity.this,"登录成功！",Toast.LENGTH_SHORT);
                                   toast.show();
                                   Looper.loop();
                               }else {
                                   Looper.prepare();
                                   Toast.makeText(LoginActivity.this,"用户名或密码错误！",Toast.LENGTH_SHORT).show();
                                   Looper.loop();
                               }
                           }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
        //给注册按钮注册监听器，实现监听器接口，编写事件
        bt_bos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
            }
        });
    }
}

//