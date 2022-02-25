package com.greenhi.peach_garden.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.greenhi.peach_garden.R;

public class SetFragment3 extends Fragment {
    private Context mContext;
    private View rootView;

    public static SetFragment3 newInstance(){
        Bundle args = new Bundle();
        SetFragment3 fragment = new SetFragment3();
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
            rootView = inflater.inflate(R.layout.set_fragment3, container, false);
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
