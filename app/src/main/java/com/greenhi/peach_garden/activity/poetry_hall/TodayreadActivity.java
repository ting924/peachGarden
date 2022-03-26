package com.greenhi.peach_garden.activity.poetry_hall;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.utils.ActivityCollectorUtil;

public class TodayreadActivity extends AppCompatActivity {
    private ImageButton imageButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollectorUtil.addActivity(this);
        setContentView(R.layout.activity_todayread);
        imageButton = findViewById(R.id.today_back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);
    }
}
