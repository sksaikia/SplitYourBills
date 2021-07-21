package com.example.splityourbillsandroid.di.modules.main;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModelProviders;


import com.example.splityourbillsandroid.base.ViewModelFactory;
import com.example.splityourbillsandroid.data.AppDataManager;
import com.example.splityourbillsandroid.di.scopes.ActivityContext;
import com.example.splityourbillsandroid.di.scopes.PerActivity;
import com.example.splityourbillsandroid.ui.main.MainActivity;
import com.example.splityourbillsandroid.ui.main.MainViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class MainActivityModule {

    @Provides
    @PerActivity
    @ActivityContext
    static Context provideMainActivityModule(MainActivity activity){
        return activity;
    }


    @Provides
    @PerActivity
    static MainViewModel provideMainViewModel(@ActivityContext Context context, Application application, AppDataManager appDataManager){
        MainViewModel vm = new MainViewModel(appDataManager, application);
        ViewModelFactory<MainViewModel> factory = new ViewModelFactory<>(vm,appDataManager,application);
        return ViewModelProviders.of((MainActivity) context,factory).get(MainViewModel.class);
    }
}
