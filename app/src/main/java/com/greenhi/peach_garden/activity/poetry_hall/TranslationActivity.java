package com.greenhi.peach_garden.activity.poetry_hall;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.activity.MainActivity;

public class TranslationActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton back_btn;
    private Button lg_btn1;
    private Button lg_btn2;
    private Button lg_btn3;
    private Button lg_btn4;
    private Button lg_btn5;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation);
        back_btn=findViewById(R.id.back_btn1);
        back_btn.setOnClickListener(this);
        lg_btn1=findViewById(R.id.English);
        lg_btn1.setOnClickListener(this);
        lg_btn2=findViewById(R.id.French);
        lg_btn2.setOnClickListener(this);
        lg_btn3=findViewById(R.id.Russian);
       lg_btn3.setOnClickListener(this);
        lg_btn4=findViewById(R.id.Japan);
        lg_btn5=findViewById(R.id.Korean);
        lg_btn5.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_btn1:
                this.finish();
                break;

            case R.id.English:
                Intent intent=new Intent(TranslationActivity.this,SearchActivity.class);
                intent.putExtra("language","英");
                startActivity(intent);
                break;
            case R.id.French:
                Intent intent1=new Intent(TranslationActivity.this,SearchActivity.class);
                intent1.putExtra("language","法");
                startActivity(intent1);
                break;
            case R.id.Japan:
                Intent intent2=new Intent(TranslationActivity.this,SearchActivity.class);
                intent2.putExtra("language","日");
                startActivity(intent2);
                break;

            case R.id.Russian:
                Intent intent3=new Intent(TranslationActivity.this,SearchActivity.class);
                intent3.putExtra("language","德");
                startActivity(intent3);
                break;

            case R.id.Korean:
                Intent intent4=new Intent(TranslationActivity.this,SearchActivity.class);
                intent4.putExtra("language","韩");
                startActivity(intent4);
                break;

        }
    }
}
