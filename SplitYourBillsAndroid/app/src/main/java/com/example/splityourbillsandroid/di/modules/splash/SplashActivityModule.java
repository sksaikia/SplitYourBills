package com.example.splityourbillsandroid.di.modules.splash;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModelProviders;

import com.example.splityourbillsandroid.base.ViewModelFactory;
import com.example.splityourbillsandroid.data.AppDataManager;
import com.example.splityourbillsandroid.di.scopes.ActivityContext;
import com.example.splityourbillsandroid.di.scopes.PerActivity;
import com.example.splityourbillsandroid.ui.auth.AuthActivity;
import com.example.splityourbillsandroid.ui.auth.AuthViewModel;
import com.example.splityourbillsandroid.ui.main.MainActivity;
import com.example.splityourbillsandroid.ui.main.MainViewModel;
import com.example.splityourbillsandroid.ui.splash.SplashActivity;
import com.example.splityourbillsandroid.ui.splash.SplashViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class SplashActivityModule {

    @Provides
    @PerActivity
    @ActivityContext
    static Context provideSplashActivityModule(SplashActivity activity){
        return activity;
    }


    @Provides
    @PerActivity
    static SplashViewModel provideSplashViewModel(@ActivityContext Context context, Application application, AppDataManager appDataManager){
        SplashViewModel vm = new SplashViewModel(appDataManager, application);
        ViewModelFactory<SplashViewModel> factory = new ViewModelFactory<>(vm,appDataManager,application);
        return ViewModelProviders.of((SplashActivity) context,factory).get(SplashViewModel.class);
    }

}
