package com.giftedcat.picture.lib.selector;

import static com.example.easylib.R.string.mis_error_no_permission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.easylib.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片选择器
 * Created by nereo on 16/3/17.
 */
public class MultiImageSelector {

    public static final String EXTRA_RESULT = MultiImageSelectorActivity.EXTRA_RESULT;

    private boolean mShowCamera = true;
    private int mMaxCount = 9;
    private int mMode = MultiImageSelectorActivity.MODE_MULTI;
    private List<String> mOriginData;
    private static MultiImageSelector sSelector;

    @Deprecated
    private MultiImageSelector(Context context){

    }

    private MultiImageSelector(){}

    @Deprecated
    public static MultiImageSelector create(Context context){
        if(sSelector == null){
            sSelector = new MultiImageSelector(context);
        }
        return sSelector;
    }

    public static MultiImageSelector create(){
        if(sSelector == null){
            sSelector = new MultiImageSelector();
        }
        return sSelector;
    }

    public MultiImageSelector showCamera(boolean show){
        mShowCamera = show;
        return sSelector;
    }

    public MultiImageSelector count(int count){
        mMaxCount = count;
        return sSelector;
    }

    public MultiImageSelector single(){
        mMode = MultiImageSelectorActivity.MODE_SINGLE;
        return sSelector;
    }

    public MultiImageSelector multi(){
        mMode = MultiImageSelectorActivity.MODE_MULTI;
        return sSelector;
    }

    public MultiImageSelector origin(List<String> images){
        mOriginData = images;
        return sSelector;
    }

    public void start(Activity activity, int requestCode){
        final Context context = activity;
        activity.startActivityForResult(createIntent(context), requestCode);
    }

    public void start(Fragment fragment, int requestCode){
        final Context context = fragment.getContext();
        fragment.startActivityForResult(createIntent(context), requestCode);
    }

    private boolean hasPermission(Context context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            // Permission was added in API Level 16
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED){
                Log.d("permission1","pass");
                return true;
            }
            Log.d("permission1","nopass");
            return false;
        }
        return true;
    }

    private Intent createIntent(Context context){
        Intent intent = new Intent(context, MultiImageSelectorActivity.class);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, mShowCamera);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, mMaxCount);
        if(mOriginData != null){
            intent.putStringArrayListExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, (ArrayList<String>) mOriginData);
        }
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, mMode);
        return intent;
    }
}
