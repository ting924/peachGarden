package com.greenhi.peach_garden.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.greenhi.peach_garden.R;

public class SetFragment6 extends Fragment {
    private Context mContext;
    private View rootView;

    LinearLayout ll1,ll2,ll3,llCurrent;
    Button btn1,btn2;

    public static SetFragment6 newInstance(){
        Bundle args = new Bundle();
        SetFragment6 fragment = new SetFragment6();
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
            rootView = inflater.inflate(R.layout.set_fragment6, container, false);
        }
        initData();
        initView();
        return rootView;
    }

    private void initView() {
        ll1=rootView.findViewById(R.id.ll_fankui1);
        ll2=rootView.findViewById(R.id.ll_fankui2);
        ll3=rootView.findViewById(R.id.ll_fankui3);
        llCurrent = ll1;
        btn1=rootView.findViewById(R.id.btn1);
        btn2=rootView.findViewById(R.id.btn2);
    }

    private void initData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llCurrent.setVisibility(View.GONE);
                ll2.setVisibility(View.VISIBLE);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llCurrent.setVisibility(View.GONE);
                ll3.setVisibility(View.VISIBLE);
            }
        });
    }
}
