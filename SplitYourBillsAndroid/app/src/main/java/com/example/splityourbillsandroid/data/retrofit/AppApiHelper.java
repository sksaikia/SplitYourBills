package com.example.splityourbillsandroid.data.retrofit;



import com.example.splityourbillsandroid.data.models.DefaultResponse;
import com.example.splityourbillsandroid.data.models.authentication.LoginBody;
import com.example.splityourbillsandroid.data.models.authentication.RegisterBody;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AppApiHelper implements APIInterface{

    APIInterface api;

    @Inject
    public AppApiHelper(Retrofit retrofit) {
        api = retrofit.create(APIInterface.class);
    }


    @Override
    public Observable<Response<DefaultResponse>> registerUser(RegisterBody authBody) {
        return api.registerUser(authBody);
    }

    @Override
    public Observable<Response<DefaultResponse>> loginUser(LoginBody authBody) {
        return api.loginUser(authBody);
    }
}
