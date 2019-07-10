package com.example.retrofit.network;

import com.example.retrofit.data.DataModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("json_parsing.php")
    Call<DataModel> getData();
}
