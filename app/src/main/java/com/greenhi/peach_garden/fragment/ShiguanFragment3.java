package com.greenhi.peach_garden.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
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
import com.greenhi.peach_garden.activity.RegistActivity;
import com.greenhi.peach_garden.utils.ActivityCollectorUtil;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.activity.poetry_hall.SearchActivity;
import com.greenhi.peach_garden.activity.poetry_hall.TranContentActivity;
import com.greenhi.peach_garden.activity.poetry_hall.TranslationActivity;

import java.util.ArrayList;
import java.util.List;

public class ShiguanFragment3 extends Fragment implements View.OnClickListener{
    private Context mContext;
    private View rootView;
    private ViewGroup mContentView;
    private ImageButton back_btn;
    private String lg;
    private List<String> history = new ArrayList<>();
    private GridView gridView;
    private EditText editText;
    private Button button;
    private ArrayAdapter<String> adapter;
    private String Tag = "com.greenhi.peach_garden.activity.poetry_hall.SearchActivity";

    public static ShiguanFragment3 newInstance() {
        Bundle args = new Bundle();
        ShiguanFragment3 fragment = new ShiguanFragment3();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.activity_translation, container, false);
        }
        initView();
        editText.setHint("请输入诗歌题目");
        history.add("茅屋为秋风所破歌");
        history.add("登高");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history.clear();
                adapter.notifyDataSetChanged();
            }
        });
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (!editText.getText().toString().equals("")) {
                    String p_name = editText.getText().toString();
                    if (!history.contains(p_name)) {
                        history.add(p_name);
                    }
                    adapter.notifyDataSetChanged();
                    Activity activity1=getActivity();
                    Intent intent = new Intent(activity1, TranContentActivity.class);
                    intent.putExtra("title", p_name);
                    startActivity(intent);
                    return true;
                } else {
                    Activity activity1=getActivity();
                    Toast.makeText(activity1, "请输入诗名！", Toast.LENGTH_SHORT).show();
                    return true;
                }
            }
        });
        Activity activity3=getActivity();
        adapter = new ArrayAdapter<String>(activity3, android.R.layout.simple_list_item_1, history);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String title = history.get(i);
                Activity activity2=getActivity();
                Intent intent1 = new Intent(activity2, TranContentActivity.class);
                intent1.putExtra("title", title);
                startActivity(intent1);
            }
        });
       return rootView;
    }
    public void initView(){
        button = rootView.findViewById(R.id.clear_history);
        editText = rootView.findViewById(R.id.tran_s);
        gridView = rootView.findViewById(R.id.history_record);
    }

    @Override
    public void onClick(View view) {
    }
}
