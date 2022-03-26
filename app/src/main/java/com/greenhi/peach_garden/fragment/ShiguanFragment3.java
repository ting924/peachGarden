package com.greenhi.peach_garden.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.activity.poetry_hall.SearchActivity;
import com.greenhi.peach_garden.activity.poetry_hall.TranslationActivity;

public class ShiguanFragment3 extends Fragment implements View.OnClickListener{
    private Context mContext;
    private View rootView;
    private ViewGroup mContentView;
    private Button lg_btn1;
    private Button lg_btn2;
    private Button lg_btn3;
    private Button lg_btn4;
    private Button lg_btn5;
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
        lg_btn1.setOnClickListener(this);
        lg_btn2.setOnClickListener(this);
        lg_btn3.setOnClickListener(this);
        lg_btn4.setOnClickListener(this);
        lg_btn5.setOnClickListener(this);
       return rootView;
    }
    public void initView(){
        lg_btn1=rootView.findViewById(R.id.English);
        lg_btn2=rootView.findViewById(R.id.French);
        lg_btn3=rootView.findViewById(R.id.Russian);
        lg_btn4=rootView.findViewById(R.id.Japan);
        lg_btn5=rootView.findViewById(R.id.Korean);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.English:
                Activity activity1=getActivity();
                Intent intent=new Intent(activity1, SearchActivity.class);
                intent.putExtra("language","english");
                startActivity(intent);
                break;
            case R.id.French:
                Activity activity2=getActivity();
                Intent intent1=new Intent(activity2,SearchActivity.class);
                intent1.putExtra("language","french");
                startActivity(intent1);
                break;
            case R.id.Japan:
                Activity activity3=getActivity();
                Intent intent2=new Intent(activity3,SearchActivity.class);
                intent2.putExtra("language","japanese");
                startActivity(intent2);
                break;

            case R.id.Russian:
                Activity activity4=getActivity();
                Intent intent3=new Intent(activity4,SearchActivity.class);
                intent3.putExtra("language","russian");
                startActivity(intent3);
                break;

            case R.id.Korean:
                Activity activity5=getActivity();
                Intent intent4=new Intent(activity5,SearchActivity.class);
                intent4.putExtra("language","korean");
                startActivity(intent4);
                break;

        }
    }
}
