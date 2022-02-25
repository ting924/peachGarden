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

public class SetFragment4 extends Fragment {
    private Context mContext;
    private View rootView;

    private TextView tvEnglish, tvChinese1, tvChinese2, tvJapanese, tvKorean, tvCurrent;

    public static SetFragment4 newInstance() {
        Bundle args = new Bundle();
        SetFragment4 fragment = new SetFragment4();
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
            rootView = inflater.inflate(R.layout.set_fragment4, container, false);
        }
        initData();
        initView();
        return rootView;
    }

    private void initView() {

        tvEnglish = rootView.findViewById(R.id.tv_english);
        tvChinese1 = rootView.findViewById(R.id.tv_chinese1);
        tvChinese2 = rootView.findViewById(R.id.tv_chinese2);
        tvJapanese = rootView.findViewById(R.id.tv_japanese);
        tvKorean = rootView.findViewById(R.id.tv_korean);
        tvChinese2.setSelected(true);
        tvCurrent = tvChinese2;

    }

    private void initData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTab(0);
            }
        });
        tvChinese1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTab(1);
            }
        });
        tvChinese2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTab(2);
            }
        });
        tvJapanese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTab(3);
            }
        });
        tvKorean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTab(4);
            }
        });
    }

    private void changeTab(int position) {
        tvCurrent.setSelected(false);
        switch (position) {
            case 0:
                tvEnglish.setSelected(true);
                tvCurrent = tvEnglish;
                break;
            case 1:
                tvChinese1.setSelected(true);
                tvCurrent = tvChinese1;
                break;
            case 2:
                tvChinese2.setSelected(true);
                tvCurrent = tvChinese2;
                break;
            case 3:
                tvJapanese.setSelected(true);
                tvCurrent = tvJapanese;
                break;
            case 4:
                tvKorean.setSelected(true);
                tvCurrent = tvKorean;
                break;
        }
    }
}
