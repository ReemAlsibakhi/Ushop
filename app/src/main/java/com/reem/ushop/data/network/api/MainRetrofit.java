package com.reem.ushop.data.network.api;

import android.util.ArrayMap;
import com.reem.ushop.pojo.Category;
import com.reem.ushop.pojo.ResponseGet;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MainRetrofit {

    @FormUrlEncoded
    @POST("misc/get_categories")
    public Call<ResponseGet<ArrayList<Category>>> getCats(@FieldMap ArrayMap<String, Object> params);



}
