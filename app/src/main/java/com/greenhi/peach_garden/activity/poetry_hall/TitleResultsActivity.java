package com.greenhi.peach_garden.activity.poetry_hall;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.greenhi.peach_garden.R;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;


public class TitleResultsActivity extends AppCompatActivity {
    private ImageButton imageButton;
    private String p_name;
    private String dynasty;
    private String theme;
    private String grand;
    private String []titles;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titleresults);
        imageButton=findViewById(R.id.title_back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent=getIntent();
       p_name= intent.getStringExtra("p_name");
       dynasty=intent.getStringExtra("dynasty");
       grand=intent.getStringExtra("grand");
       theme=intent.getStringExtra("theme");
       if(p_name!=null){
       //模拟向后端要数据
        if(p_name.equals("李白")){
            titles=new String[]{"静夜思","赠汪伦","敬亭山","送孟浩然之广陵","望西湖"};
            showDatas();
        }
        else {
            Intent intent1=new Intent(this,NoneActivity.class);
            startActivity(intent1);
            finish();
        }}
       else if(dynasty!=null){
           if(dynasty.equals("唐")){
               titles=new String[]{"静夜思","赠汪伦","敬亭山","送孟浩然之广陵","望西湖"};
               showDatas();
           }
           else {
               Intent intent1=new Intent(this,NoneActivity.class);
               startActivity(intent1);
               finish();

              }
       }
       else if(grand!=null){
           if(grand.equals("简单")){
               titles=new String[]{"静夜思","赠汪伦","敬亭山","送孟浩然之广陵","望西湖"};
               showDatas();
           }
           else {
               Intent intent1=new Intent(this,NoneActivity.class);
               startActivity(intent1);
               finish();}
       }
       else if(theme!=null){
           if(theme.equals("思乡")){
               titles=new String[]{"静夜思","赠汪伦","敬亭山","送孟浩然之广陵","望西湖"};
               showDatas();
           }
           else {
               Intent intent1=new Intent(this,NoneActivity.class);
               startActivity(intent1);
               finish();}
       }



    }
    public void showDatas(){
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(TitleResultsActivity.this, android.R.layout.simple_list_item_1,titles);
        ListView listView=(ListView)findViewById(R.id.title_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String title=titles[i];
                Intent intent1=new Intent(TitleResultsActivity.this,PoemContentActivity.class);
                intent1.putExtra("title",title);
                startActivity(intent1);
            }
        });
    }



}
