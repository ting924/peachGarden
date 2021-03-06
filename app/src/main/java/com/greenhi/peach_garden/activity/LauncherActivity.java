package com.greenhi.peach_garden.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.utils.ActivityCollectorUtil;
import com.greenhi.peach_garden.utils.ShareUtils;

public class LauncherActivity extends Activity {

    /**
     * 1.延时2000ms
     * 2.判断程序是否第一次运行
     * 3.Activity全屏主题
     */

    //闪屏业延时
    private static final int HANDLER_SPLASH = 1001;
    //判断程序是否是第一次运行
    private static final String SHARE_IS_FIRST = "isFirst";


    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLER_SPLASH:
                    //判断程序是否是第一次运行
                    if (isFirst()) {
//                    if (true) {
                        startActivity(new Intent(LauncherActivity.this, IntroActivity.class));
                    } else {
                        if (haveLogin()) {
                            startActivity(new Intent(LauncherActivity.this, MainActivity.class));
                        } else {
                            startActivity(new Intent(LauncherActivity.this, LoginActivity.class));
                        }
                    }
                    finish();
                    break;
                default:
                    break;
            }
            return false;
        }

    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollectorUtil.addActivity(this);
        setContentView(R.layout.activity_launcher);
        initView();

    }

    //初始化View
    private void initView() {
        //延时2000ms
        handler.sendEmptyMessageDelayed(HANDLER_SPLASH, 2000);
    }

    //判断程序是否第一次运行
    private boolean isFirst() {
        boolean isFirst = ShareUtils.getBoolean(this, SHARE_IS_FIRST, true);
        if (isFirst) {
            ShareUtils.putBoolean(this, SHARE_IS_FIRST, false);
            //是第一次运行
            return true;
        } else {
            return false;
        }
    }

    //判断程序是否已登录
    private boolean haveLogin() {
        boolean haveLogin = ShareUtils.getBoolean(this, ShareUtils.HAVE_LOGIN, true);
        if (haveLogin) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);
    }
}
