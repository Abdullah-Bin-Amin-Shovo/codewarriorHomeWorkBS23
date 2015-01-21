package com.bs23.codewarrior.codewarriorfirstproject.service;

import com.bs23.codewarrior.codewarriorfirstproject.model.LoginResponse;
import com.bs23.codewarrior.codewarriorfirstproject.model.User;
import com.bs23.codewarrior.codewarriorfirstproject.model.UserProfile;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;

public interface AuthService {
    @POST("/api/account/register")
    void registerUser(@Body User user, Callback<User> cb);

    @FormUrlEncoded
    @POST("/token")
    void getAuthToken(@Field("username") String email
            , @Field("password") String password
            , @Field("grant_type") String grantType
            , Callback<LoginResponse> cb
    );

    @GET("/api/user/self")
    void getProfile(Callback<UserProfile> cb);

    @PUT("/api/user/self")
    void updateProfile(@Body UserProfile profile, Callback<Object> cb);

    @GET("/api/orders")
    void getOrders(Callback<List<Object>> cb);

}
