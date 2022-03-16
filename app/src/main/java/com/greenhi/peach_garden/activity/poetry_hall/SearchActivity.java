package com.greenhi.peach_garden.activity.poetry_hall;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.greenhi.peach_garden.Dao.RecordDao;
import com.greenhi.peach_garden.R;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton back_btn;
    private String lg;
    private List<String>history=new ArrayList<>();
    private GridView gridView;
    private EditText editText;
    private Button button;
    private ArrayAdapter<String>adapter;
    private String Tag="com.greenhi.peach_garden.activity.poetry_hall.SearchActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        history.add("茅屋为秋风所破歌");
        history.add("登高");
        button=findViewById(R.id.clear_history);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history.clear();
                adapter.notifyDataSetChanged();
            }
        });
        Log.i(Tag,history.size()+"");
        editText=findViewById(R.id.tran_s);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(keyEvent!=null&&keyEvent.KEYCODE_ENTER==keyEvent.getKeyCode()){
                    String p_name=editText.getText().toString();
                    Intent intent=new Intent(SearchActivity.this,TranContentActivity.class);
                    intent.putExtra("language",lg);
                    intent.putExtra("title",p_name);
                    if(!history.contains(p_name))
                    {history.add(p_name);}
                    adapter.notifyDataSetChanged();
                    startActivity(intent);
                    return true;
                }
                else {
                    return false;
                }
            }
        });
        gridView=findViewById(R.id.history_record);
        back_btn=findViewById(R.id.back_btn2);
        back_btn.setOnClickListener(this);
        Intent intent=getIntent();
        lg=intent.getStringExtra("language");
        Toast.makeText(this,lg,Toast.LENGTH_LONG).show();
        adapter=new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_list_item_1,history);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String title=history.get(i);
                Intent intent1=new Intent(SearchActivity.this,TranContentActivity.class);
                intent1.putExtra("title",title);
                intent1.putExtra("language",lg);
                startActivity(intent1);
            }
        });

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
