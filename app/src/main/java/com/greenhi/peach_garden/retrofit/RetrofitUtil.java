package com.greenhi.peach_garden.retrofit;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RetrofitUtil {
    public static  List<MultipartBody.Part> createPartWithParams(List<String> urls){
        List<MultipartBody.Part> parts = new ArrayList<>();
        for(int i=0;i<urls.size()-1;i++){
            String url = urls.get(i);
            System.out.println("url-->"+url);
            File file = new File(url);
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("imgs",file.getName(),requestBody);
            parts.add(part);
        }
        return parts;
    }

}
