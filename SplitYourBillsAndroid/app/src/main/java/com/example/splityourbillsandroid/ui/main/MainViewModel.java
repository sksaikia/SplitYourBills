package com.example.splityourbillsandroid.ui.main;

import android.app.Application;

import com.example.splityourbillsandroid.base.BaseViewModel;
import com.example.splityourbillsandroid.data.AppDataManager;

import javax.inject.Inject;

public class MainViewModel extends BaseViewModel {

    private AppDataManager appDataManager;
    public static final String TAG = "MainActivityViewModel";

    @Inject
    public MainViewModel(AppDataManager appDataManager, Application application) {
        super(appDataManager, application);
        this.appDataManager = appDataManager;
    }
}
