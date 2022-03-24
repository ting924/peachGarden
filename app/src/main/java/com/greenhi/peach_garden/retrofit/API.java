package com.greenhi.peach_garden.retrofit;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface API {

    @Multipart
    @POST("/dynamic/uploadImgs")
    Call<PostResult> postDynamicImgs(@Part List<MultipartBody.Part> parts,@PartMap Map<String,Integer> params);

}
