package com.greenhi.peach_garden.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.greenhi.peach_garden.R;

public class SetFragment5 extends Fragment {

    private Context mContext;
    private View rootView;

    private TextView tvOpen, tvClose, tvCurrent, tvSwitch;

    public static SetFragment5 newInstance() {
        Bundle args = new Bundle();
        SetFragment5 fragment = new SetFragment5();
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
            rootView = inflater.inflate(R.layout.set_fragment5, container, false);
        }
        initData();
        initView();
        return rootView;
    }

    private void initView() {
        tvOpen = rootView.findViewById(R.id.tv_open);
        tvClose = rootView.findViewById(R.id.tv_close);
        tvSwitch = getActivity().findViewById(R.id.tv_notice_switch);
        tvClose.setSelected(true);
        tvCurrent = tvClose;
    }

    private void initData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvSwitch.setText("已开启");
                changeTab(0);
            }
        });
        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvSwitch.setText("未开启");
                changeTab(1);
            }
        });
    }

    private void changeTab(int position) {
        tvCurrent.setSelected(false);
        switch (position) {
            case 0:
                tvOpen.setSelected(true);
                tvCurrent = tvOpen;
                break;
            case 1:
                tvClose.setSelected(true);
                tvCurrent = tvClose;
                break;
        }
    }
}
