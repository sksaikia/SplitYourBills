package com.example.splityourbillsandroid.data.retrofit;


import com.example.splityourbillsandroid.data.models.DefaultResponse;
import com.example.splityourbillsandroid.data.models.authentication.LoginBody;
import com.example.splityourbillsandroid.data.models.authentication.RegisterBody;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {

    //Registration
    @POST("api/auth/signup")
    Observable<Response<DefaultResponse>> registerUser(@Body RegisterBody authBody);

    //Registration
    @POST("api/auth/signin")
    Observable<Response<DefaultResponse>> loginUser(@Body LoginBody authBody);

}
