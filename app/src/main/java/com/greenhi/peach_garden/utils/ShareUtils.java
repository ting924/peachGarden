package com.greenhi.peach_garden.utils;

import android.content.Context;
import android.content.SharedPreferences;
public class ShareUtils {

    public static final String NAME = "config";

    //判断是否已登录
    public static final String HAVE_LOGIN = "haveLogin";

    //键 值
    public static void putBoolean(Context mContext, String key, boolean value) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    //键 默认值
    public static boolean getBoolean(Context mContext, String key, boolean defValue) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }
}
