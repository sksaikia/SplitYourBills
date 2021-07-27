package com.example.splityourbillsandroid.data;

import android.content.Context;


import com.example.splityourbillsandroid.data.models.DefaultResponse;
import com.example.splityourbillsandroid.data.models.authentication.JWTResponse;
import com.example.splityourbillsandroid.data.models.authentication.LoginBody;
import com.example.splityourbillsandroid.data.models.authentication.RegisterBody;
import com.example.splityourbillsandroid.data.models.spaces.SpaceBody;
import com.example.splityourbillsandroid.data.models.spaces.AddNewSpaceResponse;
import com.example.splityourbillsandroid.data.models.spaces.SpaceMembersResponse;
import com.example.splityourbillsandroid.data.models.spaces.SpaceResponse;
import com.example.splityourbillsandroid.data.models.spaces.TransactionsResponse;
import com.example.splityourbillsandroid.data.prefs.AppPreferencesHelper;
import com.example.splityourbillsandroid.data.retrofit.AppApiHelper;
import com.example.splityourbillsandroid.di.scopes.ApplicationContext;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import retrofit2.Response;

@Singleton
public class AppDataManager implements AppDataManagerHelper{

    private Context context;
    private AppPreferencesHelper preferencesHelper;
    private AppApiHelper apiHelper;
    private static final String TAG = "AppDataManager";

    @Inject
    public AppDataManager(@ApplicationContext Context context, AppPreferencesHelper appPreferencesHelper
    , AppApiHelper apiHelper){
        this.context = context;
        this.preferencesHelper = appPreferencesHelper;
        this.apiHelper = apiHelper;
    }
    @Override
    public String getAccessToken() {
        return preferencesHelper.getAccessToken();
    }
    @Override
    public void setAccessToken(String token) {
        preferencesHelper.setAccessToken(token);
    }
    @Override
    public String getUserId() {
        return preferencesHelper.getUserId();
    }

    @Override
    public Observable<Response<DefaultResponse>> registerUser(RegisterBody authBody) {
        return apiHelper.registerUser(authBody);
    }

    @Override
    public Observable<Response<JWTResponse>> loginUser(LoginBody authBody) {
        return apiHelper.loginUser(authBody);
    }

    @Override
    public Observable<Response<List<SpaceResponse>>> getSpacesByUserId() {
        return apiHelper.getSpacesByUserId();
    }

    @Override
    public Observable<Response<List<TransactionsResponse>>> getTransactionsBySpaceId(long spaceId) {
        return apiHelper.getTransactionsBySpaceId(spaceId);
    }

    @Override
    public Observable<Response<AddNewSpaceResponse>> addNewSpace(SpaceBody spaceBody) {
        return apiHelper.addNewSpace(spaceBody);
    }

    @Override
    public Observable<Response<List<SpaceMembersResponse>>> getMembersBySpaceId(long spaceId) {
        return apiHelper.getMembersBySpaceId(spaceId);
    }

}
