package com.capstone.everykid.RetrofitAPI;

import com.capstone.everykid.Model.LoginRequest;
import com.capstone.everykid.Model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface initMyApi {
    String REGIST_URL = "http://10.0.2.2:8080/";

    @POST("/register/login")
    Call<LoginResponse> getLoginResponse(
            @Body LoginRequest loginRequest
            );
}