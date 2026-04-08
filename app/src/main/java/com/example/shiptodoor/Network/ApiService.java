package com.example.shiptodoor.Network;

import com.example.shiptodoor.Models.LoginResponce;
import com.example.shiptodoor.Models.RegisterResponse;
import com.example.shiptodoor.Models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api/register")
    Call<RegisterResponse> register(@Body User user);

    @POST("api/login")
    Call<LoginResponce> login(@Body User user);
}
