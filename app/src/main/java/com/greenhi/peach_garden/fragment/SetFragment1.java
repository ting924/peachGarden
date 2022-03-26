package com.greenhi.peach_garden.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.activity.LoginActivity;
import com.greenhi.peach_garden.activity.MainActivity;
import com.greenhi.peach_garden.utils.ActivityCollectorUtil;
import com.greenhi.peach_garden.utils.ShareUtils;

public class SetFragment1 extends Fragment {

    private Context mContext;
    private View rootView;

    private Button btn1,btn2;

    public static SetFragment1 newInstance(){
        Bundle args = new Bundle();
        SetFragment1 fragment = new SetFragment1();
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
            rootView = inflater.inflate(R.layout.set_fragment1, container, false);
        }
        initData();
        initView();
        return rootView;
    }

    private void initView() {

        btn1 = rootView.findViewById(R.id.set_tab1_btn1);
        btn2 = rootView.findViewById(R.id.set_tab1_btn2);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"编辑资料",Toast.LENGTH_SHORT).show();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(mContext,"退出账号",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(mContext);
                alertdialogbuilder.setMessage("您确认要退出账号吗");
                alertdialogbuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ShareUtils.putBoolean(mContext, ShareUtils.HAVE_LOGIN, false);
                        startActivity(new Intent(mContext, LoginActivity.class));
                        ActivityCollectorUtil.finishAllActivity();
                    }
                });
                alertdialogbuilder.setNeutralButton("取消", null);
                final AlertDialog alertdialog1 = alertdialogbuilder.create();
                alertdialog1.show();
            }
        });
    }

    private void initData() {

    }
}