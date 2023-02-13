package com.ldh.upgreen.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ldh.upgreen.Model.Otp;
import com.ldh.upgreen.Model.Response.DataResponse;
import com.ldh.upgreen.Model.User;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    String macova = "192.168.1.173";
    String url = "http://"+macova+":3000/";
    ApiService apiService = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create(gson)).build().create(ApiService.class);

    //sign up
    @POST("/user/signUp")
    Call<DataResponse> signUp(@Body User user);
    @POST("/user/signUp/verify")
    Call<DataResponse> signUpVerify(@Body Otp otp);
    //sign in
    @POST("/user/signIn")
    Call<DataResponse> signIn(@Body User user);
    @GET("user/get")
    Call<DataResponse> getUser(@Header("token") String token);
    //user

    //field
    @GET("/field/get/all")
    Call<DataResponse> getAllField(@Header("token") String token);

}
