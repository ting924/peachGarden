package com.greenhi.peach_garden.activity.poetry_hall;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.greenhi.peach_garden.R;

public class TranContentActivity extends AppCompatActivity {
    private ImageButton imageButton;
    private String language;
    private String title;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trancontent);
        imageButton=findViewById(R.id.trancontent_back);
        Intent intent=getIntent();
        language=intent.getStringExtra("language");
        title=intent.getStringExtra("title");
        Toast.makeText(this,language+" "+title,Toast.LENGTH_LONG).show();
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
