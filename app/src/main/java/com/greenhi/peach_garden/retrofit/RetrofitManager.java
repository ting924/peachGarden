package com.greenhi.peach_garden.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://47.108.176.163:7777")   //在模拟器上用10.0.2.2访问你的电脑本机
            .build();

    public static Retrofit getRetrofit(){
        return retrofit;
    }
}
