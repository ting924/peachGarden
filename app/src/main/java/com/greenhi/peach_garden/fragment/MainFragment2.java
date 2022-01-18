package com.greenhi.peach_garden.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.greenhi.peach_garden.R;


public class MainFragment2 extends Fragment {

    private Context mContext;
    private View rootView;
    private ViewGroup mContentView;

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
        return rootView;
    }

    private void initData() {

    }

    private void initView() {
        mContentView = rootView.findViewById(R.id.main_fra2);
    }

}
