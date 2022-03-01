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

public class DynastyActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton btn;
    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;
    private LinearLayout linearLayout3;
    private LinearLayout linearLayout4;
    private LinearLayout linearLayout5;
    private LinearLayout linearLayout6;
    private LinearLayout linearLayout7;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynasty);
        btn=findViewById(R.id.dynasty_back);
        btn.setOnClickListener(this);
        linearLayout1=findViewById(R.id.tang);
        linearLayout1.setOnClickListener(this);
        linearLayout2=findViewById(R.id.song);
        linearLayout2.setOnClickListener(this);
        linearLayout3=findViewById(R.id.yuan);
        linearLayout3.setOnClickListener(this);
        linearLayout4=findViewById(R.id.ming);
        linearLayout4.setOnClickListener(this);
        linearLayout5=findViewById(R.id.qing);
        linearLayout5.setOnClickListener(this);
        linearLayout6=findViewById(R.id.qin);
        linearLayout6.setOnClickListener(this);
        linearLayout7=findViewById(R.id.han);
        linearLayout7.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dynasty_back:
                finish();
                break;
            case R.id.tang:
                go("唐");
                break;
            case R.id.song:
                go("宋");
                break;
            case R.id.yuan:
                go("元");
                break;
            case R.id.ming:
                go("明");
                break;
            case R.id.qing:
                go("清");
                break;
            case R.id.qin:
                go("秦");
                break;
            case R.id.han:
                go("汉");
                break;
        }
    }
    public void go(String s){
        Intent intent=new Intent(this,TitleResultsActivity.class);
        intent.putExtra("dynasty",s);
        startActivity(intent);
    }
}
