package com.greenhi.peach_garden.activity.poetry_hall;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.greenhi.peach_garden.R;

public class PoemContentActivity extends AppCompatActivity {
    String title;//题目
    String writer;//作者
    String content;//内容
    String bg;//背景
    String annotation;//注释
    TextView titleText;
    TextView contentText;
    TextView writterText;
    TextView bgText;
    TextView annotationText;
    ImageButton imageButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poemcontent);
        imageButton=findViewById(R.id.content_back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent=getIntent();
        title=intent.getStringExtra("title");
        titleText=findViewById(R.id.title);
        contentText=findViewById(R.id.content);
        writterText=findViewById(R.id.writter);
        bgText=findViewById(R.id.bg);
        annotationText=findViewById(R.id.annotation);
        titleText.setText(title);
        //从后端要具体内容
        writer="李白";
        content="床前明月光，疑是地上霜。举头望明月，低头思故乡。";
        bg="公元200年(开元十八年)，诗人李白经历了XXXXXXXXX，思念起故乡xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        annotation="明：有光亮。疑：怀疑。霜：白霜。举：抬头。望：欣赏。思：思念。";
        contentText.setText(content);
        writterText.setText(writer);
        bgText.setText(bg);
        annotationText.setText(annotation);
    }
}
