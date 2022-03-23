package com.greenhi.peach_garden.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenhi.peach_garden.R;

public class ShisheFragment3 extends Fragment {

    private Context mContext;
    private View rootView;

    public static ShisheFragment3 newInstance(){
        Bundle args = new Bundle();
        ShisheFragment3 fragment = new ShisheFragment3();
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
            rootView = inflater.inflate(R.layout.shishe_fragment3, container, false);
        }
        initData();
        initView();
        return rootView;
    }

    private void initView() {

    }

    private void initData() {

    }
}