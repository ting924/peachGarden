package com.greenhi.peach_garden.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.giftedcat.picture.lib.selector.MultiImageSelector;
import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.adapter.NineGridAdapterCZ;
import com.greenhi.peach_garden.utils.OnAddPicturesListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ShizhaiFragment3 extends Fragment {
    private Context mContext;
    private View rootView;

    private static final int REQUEST_IMAGE = 2;
    private int maxNum = 9;

    Unbinder unbinder;

    @BindView(R.id.sz_cz_images)
    RecyclerView sz_cz_images;

    NineGridAdapterCZ adapter;
    List<String> mSelectList;

    public static ShizhaiFragment3 newInstance() {
        ShizhaiFragment3 fragment = new ShizhaiFragment3();
        Bundle args = new Bundle();
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
            rootView = inflater.inflate(R.layout.shizhai_fragment3, container, false);
        }
        unbinder=ButterKnife.bind(this,rootView);
        mSelectList = new ArrayList<>();
        initView();
        return rootView;
    }

    private void initView() {
        sz_cz_images.setLayoutManager(new GridLayoutManager(mContext,3));
        adapter = new NineGridAdapterCZ(mContext, mSelectList, sz_cz_images);
        adapter.setMaxSize(maxNum);
        sz_cz_images.setAdapter(adapter);
        adapter.setOnAddPicturesListener(new OnAddPicturesListener() {
            @Override
            public void onAdd() {
                pickImage();
            }
        });
    }



    private void pickImage() {
        MultiImageSelector selector = MultiImageSelector.create(mContext);
        selector.showCamera(true);
        selector.count(maxNum);
        selector.multi();
        selector.origin(mSelectList);
        selector.start(this,REQUEST_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                List<String> select = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                mSelectList.clear();
                mSelectList.addAll(select);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}