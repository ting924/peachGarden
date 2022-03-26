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

public class ThemeActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton btn;
    private LinearLayout[] linearLayouts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        ActivityCollectorUtil.addActivity(this);
        setContentView(R.layout.activity_theme);
        btn = findViewById(R.id.theme_back);
        btn.setOnClickListener(this);
        linearLayouts = new LinearLayout[]{findViewById(R.id.t1), findViewById(R.id.t2),
                findViewById(R.id.t3), findViewById(R.id.t4), findViewById(R.id.t5), findViewById(R.id.t6),
                findViewById(R.id.t7)};
        for (LinearLayout linearLayout : linearLayouts) {
            linearLayout.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.theme_back:
                finish();
                break;
            case R.id.t1:
                go("忧国忧民");
                break;
            case R.id.t2:
                go("山水");
                break;
            case R.id.t3:
                go("送别");
                break;
            case R.id.t4:
                go("爱国");
                break;
            case R.id.t5:
                go("边塞");
                break;
            case R.id.t6:
                go("思乡");
                break;
            case R.id.t7:
                go("爱情");
                break;

        }
    }

    public void go(String s) {
        Intent intent = new Intent(this, TitleResultsActivity.class);
        intent.putExtra("theme", s);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);
    }
}
