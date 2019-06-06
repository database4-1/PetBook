package com.example.petbook;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitCommnunication {

    @POST("/users/register")
    Call<JsonObject> userRegister(@Body JsonObject userData);

    @POST("/users/login")
    Call<JsonObject> userLogin(@Body JsonObject userData);

    @GET("/hospital/list")
    Call<JsonObject> hospitalList(@Query("search") String searchData);
}
