package com.ldh.upgreen.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ldh.upgreen.Model.*;
import com.ldh.upgreen.Model.Response.DataResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    String macova = "192.168.1.173";
    String redmi = "192.168.100.212";
    String dhnn = "10.20.5.0";
String url = "http://"+dhnn+":3000/";
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
    @PUT("/user/update")
    Call<DataResponse> updateUser(@Header("token") String token,@Body User user);
    //field
    @GET("/field/get/all")
    Call<DataResponse> getAllField(@Header("token") String token);
    //
    @GET("/paper/get/papers/hot")
    Call<DataResponse> getHotPapers(@Header("token") String token);
    @GET("/paper/get/papers/startUp")
    Call<DataResponse> getStartUpPapers(@Header("token") String token);
    @GET("/paper/get/papers/contest")
    Call<DataResponse> getContestPapers(@Header("token") String token);
    @GET("/paper/get/id/{paperId}")
    Call<DataResponse> getPaperById(@Header("token") String token, @Path("paperId") String paperId);
    //project
    @GET("/project/get/all")
    Call<DataResponse> getAllProject(@Header("token") String token);
    @GET("/project/get/my")
    Call<DataResponse> getMyProjects(@Header("token") String token);
    @GET("/project/get/id/{projectId}")
    Call<DataResponse> getProjectById(@Header("token") String token,@Path("projectId") String projectId);
}
