package com.greenhi.peach_garden.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.activity.MainActivity;
import com.greenhi.peach_garden.activity.poetry_hall.DynastyActivity;
import com.greenhi.peach_garden.activity.poetry_hall.GrandActivity;
import com.greenhi.peach_garden.activity.poetry_hall.PoemContentActivity;
import com.greenhi.peach_garden.activity.poetry_hall.PoemcourseActivity;
import com.greenhi.peach_garden.activity.poetry_hall.PoeterActivity;
import com.greenhi.peach_garden.activity.poetry_hall.ThemeActivity;
import com.greenhi.peach_garden.activity.poetry_hall.TodayreadActivity;
import com.greenhi.peach_garden.activity.poetry_hall.TranslationActivity;


public class MainFragment2 extends Fragment implements View.OnClickListener{
    private String Tag="com.greenhi.peach_garden.fragment.MainFragment2";
    private Context mContext;
    private View rootView;
    private ViewGroup mContentView;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private ImageButton poeter_btn;
    private ImageButton dynasty_btn;
    private ImageButton grand_btn;
    private ImageButton theme_btn;
    private ImageButton back_btn;
    private ImageButton jumpToToday;
    private EditText editText;

    public static MainFragment2 newInstance() {
        Bundle args = new Bundle();
        MainFragment2 fragment = new MainFragment2();
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
            rootView = inflater.inflate(R.layout.main_fragment2, container, false);
        }
        Log.e("TAG4544", "onCreateView: CommunityFragment");
        initData();
        initView();
        editText=rootView.findViewById(R.id.home_search);
        editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(KeyEvent.KEYCODE_ENTER==i&& keyEvent.getAction()==KeyEvent.ACTION_DOWN){
                    String content=editText.getText().toString();
                    Intent intent=new Intent(getActivity(), PoemContentActivity.class);
                    Toast.makeText(getActivity(),content,Toast.LENGTH_LONG).show();
                    intent.putExtra("title",content);
                    startActivity(intent);
                }
                return false;
            }
        });
       btn1=rootView.findViewById(R.id.btn1);
       btn2=rootView.findViewById(R.id.btn2);
       btn3=rootView.findViewById(R.id.btn3);
       back_btn=rootView.findViewById(R.id.back_btn);
       poeter_btn=rootView.findViewById(R.id.poeter_btn);
       grand_btn=rootView.findViewById(R.id.grand_btn);
       dynasty_btn=rootView.findViewById(R.id.dynasty_btn);
       theme_btn=rootView.findViewById(R.id.theme_btn);
       jumpToToday=rootView.findViewById(R.id.jumpToToday);
       btn2.setOnClickListener(this);
       btn3.setOnClickListener(this);
       back_btn.setOnClickListener(this);
       poeter_btn.setOnClickListener(this);
       grand_btn.setOnClickListener(this);
       dynasty_btn.setOnClickListener(this);
       theme_btn.setOnClickListener(this);
       jumpToToday.setOnClickListener(this);
       Log.i(Tag,btn1.toString());
        return rootView;
    }

    private void initData() {

    }

    private void initView() {
        mContentView = rootView.findViewById(R.id.main_fra2);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_btn:
                Activity activity=getActivity();
                Intent intent=new Intent(activity, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn2:
                Activity activity1=getActivity();
                Intent intent1=new Intent(activity1, TranslationActivity.class);
                startActivity(intent1);
                break;
            case R.id.poeter_btn:
                Activity activity2=getActivity();
                Intent intent2=new Intent(activity2, PoeterActivity.class);
                startActivity(intent2);
                break;
            case R.id.grand_btn:
                Activity activity3=getActivity();
                Intent intent3=new Intent(activity3, GrandActivity.class);
                startActivity(intent3);
                break;
            case R.id.dynasty_btn:
                Activity activity4=getActivity();
                Intent intent4=new Intent(activity4, DynastyActivity.class);
                startActivity(intent4);
                break;
            case R.id.theme_btn:
                Activity activity5=getActivity();
                Intent intent5=new Intent(activity5, ThemeActivity.class);
                startActivity(intent5);
                break;
            case R.id.btn3:
                Activity activity6=getActivity();
                Intent intent6=new Intent(activity6, PoemcourseActivity.class);
                startActivity(intent6);
                break;
            case R.id.jumpToToday:
                Activity activity7=getActivity();
                Intent intent7=new Intent(activity7, TodayreadActivity.class);
                startActivity(intent7);
                break;
        }
    }
}
