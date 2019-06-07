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

    @POST("/users/modifyinfo")
    Call<JsonObject> modifyInfo(@Body JsonObject userData);

    @GET("/users/appointment/list")
    Call<JsonObject> appointmentList(@Query("userID") String userID);


    @GET("/pets/list")
    Call<JsonObject> petsList(@Query("userID") String userID);

    @POST("/pets/register")
    Call<JsonObject> petRegister(@Body JsonObject petDate);

    @GET("/pets/avgcost")
    Call<JsonObject> petAvgCost(@Query("species") String species);


    @GET("/hospital/list")
    Call<JsonObject> hospitalList(@Query("search") String searchData);

    @POST("/hospital/appointment")
    Call<JsonObject> hospitalAppointment(@Body AppointmentData reserveData);


    @GET("/hospital/count")
    Call<JsonObject> hospitalCount(@Query("hospitalID") int hospitalID);


}
