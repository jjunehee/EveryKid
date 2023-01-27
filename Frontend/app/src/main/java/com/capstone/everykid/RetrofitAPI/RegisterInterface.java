package com.capstone.everykid.RetrofitAPI;

import com.capstone.everykid.View.Activity.ChildData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RegisterInterface
{
    String REGIST_URL = "http://10.0.2.2:8080/";

    @FormUrlEncoded
    @POST("/register/parent")
    Call<String> getParentRegist(
            @Field("PID") String id,
            @Field("PPHONE") String phone,
            @Field("PNAME") String username,
            @Field("PPWD") String password,
            @Field("PEMAIL") String email,
            @Field("KKID") String kindergarten,
            @Field("TNAME") String tname
    );

    @FormUrlEncoded
    @POST("/register/teacher")
    Call<String> getTeacherRegist(
            @Field("TID") String id,
            @Field("TPHONE") String phone,
            @Field("TNAME") String username,
            @Field("TPWD") String password,
            @Field("TEMAIL") String email,
            @Field("KKID") String kindergarten
    );

    @FormUrlEncoded
    @POST("child/child")
    Call<String> setChildData(
            @Field("PKID") String pkid,
            @Field("CNAME") String name,
<<<<<<< HEAD
            @Field("CAGE") String age
=======
            @Field("CAGE") String age,
            @Field("CIMG") String img
>>>>>>> 77b65ff8e852b6338b288b0944a443f47c37751e
    );
    @FormUrlEncoded
    @POST("/child/delete")
    Call<String> deleteChild(@Field("PKID") String pkid);
}