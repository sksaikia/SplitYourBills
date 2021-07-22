package com.example.splityourbillsandroid.ui.auth;

import android.app.Application;

import com.example.splityourbillsandroid.base.BaseViewModel;
import com.example.splityourbillsandroid.data.AppDataManager;

import javax.inject.Inject;

public class AuthViewModel extends BaseViewModel {

    private AppDataManager appDataManager;
    public static final String TAG = "MainActivityViewModel";

    @Inject
    public AuthViewModel(AppDataManager appDataManager, Application application) {
        super(appDataManager, application);
        this.appDataManager = appDataManager;
    }
}
