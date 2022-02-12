package com.greenhi.peach_garden.activity.poetry_hall;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.greenhi.peach_garden.R;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton back_btn;
    private String lg;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        back_btn=findViewById(R.id.back_btn2);
        back_btn.setOnClickListener(this);
        Intent intent=getIntent();
        lg=intent.getStringExtra("language");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_btn2:
                this.finish();
                Intent intent=new Intent(this,TranslationActivity.class);
                startActivity(intent);

        }
    }
}
