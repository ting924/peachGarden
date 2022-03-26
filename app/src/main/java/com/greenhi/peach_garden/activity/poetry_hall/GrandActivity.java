package com.greenhi.peach_garden.activity.poetry_hall;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.utils.ActivityCollectorUtil;

public class GrandActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton btn;
    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;
    private LinearLayout linearLayout3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        ActivityCollectorUtil.addActivity(this);
        setContentView(R.layout.activity_grand);
        btn = findViewById(R.id.grand_back);
        btn.setOnClickListener(this);
        linearLayout1 = findViewById(R.id.simple);
        linearLayout1.setOnClickListener(this);
        linearLayout2 = findViewById(R.id.middle);
        linearLayout2.setOnClickListener(this);
        linearLayout3 = findViewById(R.id.difficult);
        linearLayout3.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.grand_back:
                finish();
                break;
            case R.id.simple:
                go("简单");
                break;
            case R.id.middle:
                go("中等");
                break;
            case R.id.difficult:
                go("困难");
                break;
        }
    }

    void go(String s) {
        Intent intent = new Intent(this, TitleResultsActivity.class);
        intent.putExtra("grand", s);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);
    }
}
