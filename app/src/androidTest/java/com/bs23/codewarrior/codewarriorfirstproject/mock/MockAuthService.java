package com.bs23.codewarrior.codewarriorfirstproject.mock;

import com.bs23.codewarrior.codewarriorfirstproject.model.LoginResponse;
import com.bs23.codewarrior.codewarriorfirstproject.model.User;
import com.bs23.codewarrior.codewarriorfirstproject.model.UserProfile;
import com.bs23.codewarrior.codewarriorfirstproject.service.AuthService;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;

public class MockAuthService implements AuthService {
    @Override
    public void registerUser(@Body User user, Callback<User> cb) {
        cb.success(user, null);
    }

    @Override
    public void getAuthToken(@Field("username") String email, @Field("password") String password, @Field("grant_type") String grantType, Callback<LoginResponse> cb) {
    }

    @Override
    public void getProfile(Callback<UserProfile> cb) {

    }

    @Override
    public void updateProfile(@Body UserProfile profile, Callback<Object> cb) {

    }

    @Override
    public void getOrders(Callback<List<Object>> cb) {

    }
}
