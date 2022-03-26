package com.greenhi.peach_garden.activity.poetry_hall;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.utils.ActivityCollectorUtil;

public class PoeterActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton btn1, search_btn;
    private EditText editText;
    private Button[] buttons;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        ActivityCollectorUtil.addActivity(this);
        setContentView(R.layout.activity_poeter);
        editText = findViewById(R.id.poeter_name);
        btn1 = findViewById(R.id.poeter_back);
        search_btn = findViewById(R.id.search_poeter_btn);
        search_btn.setOnClickListener(this);
        btn1.setOnClickListener(this);
        buttons = new Button[]{findViewById(R.id.p1), findViewById(R.id.p2), findViewById(R.id.p3)
                , findViewById(R.id.p4), findViewById(R.id.p5), findViewById(R.id.p6), findViewById(R.id.p7),
                findViewById(R.id.p8)};
        for (Button button : buttons) {
            button.setOnClickListener(this);
        }
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (keyEvent != null && keyEvent.KEYCODE_ENTER == keyEvent.getKeyCode()) {
                    Intent intent = new Intent(PoeterActivity.this, TitleResultsActivity.class);
                    String p_name = editText.getText().toString();
                    intent.putExtra("p_name", p_name);
                    Toast.makeText(PoeterActivity.this, p_name, Toast.LENGTH_LONG).show();
                    startActivity(intent);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.poeter_back:
                finish();
                break;
            case R.id.p1:
                go("李白");
                break;
            case R.id.p2:
                go("杜甫");
                break;
            case R.id.p3:
                go("王勃");
                break;
            case R.id.p4:
                go("李清照");
                break;
            case R.id.p5:
                go("苏轼");
                break;
            case R.id.p6:
                go("曹植");
                break;
            case R.id.p7:
                go("辛弃疾");
                break;
            case R.id.p8:
                go("孟浩然");
                break;
            case R.id.search_poeter_btn:
                String p_name = editText.getText().toString();
                go(p_name);
                break;

        }

    }

    public void go(String s) {
        Intent intent = new Intent(this, TitleResultsActivity.class);
        intent.putExtra("p_name", s);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);
    }
}
