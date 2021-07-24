package com.example.splityourbillsandroid.ui.splash;

import android.app.Application;

import com.example.splityourbillsandroid.base.BaseViewModel;
import com.example.splityourbillsandroid.data.AppDataManager;

import javax.inject.Inject;

public class SplashViewModel extends BaseViewModel {

    private AppDataManager appDataManager;

    @Inject
    public SplashViewModel(AppDataManager appDataManager, Application application) {
        super(appDataManager, application);
        this.appDataManager = appDataManager;
    }

    public String getAccessToken(){
        if (appDataManager.getAccessToken()==null || appDataManager.getAccessToken()=="")
            return "";
        else
            return appDataManager.getAccessToken();
    }

}
