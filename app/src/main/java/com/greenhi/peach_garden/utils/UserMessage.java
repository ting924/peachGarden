package com.greenhi.peach_garden.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class UserMessage {
    //保存用户信息
    public static boolean saveUserInfo (Context context, int id){
        SharedPreferences sp = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("id",id);
        editor.apply();
        return true;
    }

    //读取用户信息
    public static int getUserInfo(Context context){
        SharedPreferences sp = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        int id = sp.getInt("id",0);
        return id;
    }

}
