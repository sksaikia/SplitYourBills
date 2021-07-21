package com.example.splityourbillsandroid.data;

import android.content.Context;


import com.example.splityourbillsandroid.data.prefs.AppPreferencesHelper;
import com.example.splityourbillsandroid.data.retrofit.AppApiHelper;
import com.example.splityourbillsandroid.di.scopes.ApplicationContext;

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

}
