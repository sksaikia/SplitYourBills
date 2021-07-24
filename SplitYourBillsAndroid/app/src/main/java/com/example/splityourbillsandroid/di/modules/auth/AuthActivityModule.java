package com.example.splityourbillsandroid.di.modules.auth;

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

import dagger.Module;
import dagger.Provides;

@Module
public abstract class AuthActivityModule {

    @Provides
    @PerActivity
    @ActivityContext
    static Context provideAuthActivityModule(AuthActivity activity){
        return activity;
    }


    @Provides
    @PerActivity
    static AuthViewModel provideAuthViewModel(@ActivityContext Context context, Application application, AppDataManager appDataManager){
        AuthViewModel vm = new AuthViewModel(appDataManager, application);
        ViewModelFactory<AuthViewModel> factory = new ViewModelFactory<>(vm,appDataManager,application);
        return ViewModelProviders.of((AuthActivity) context,factory).get(AuthViewModel.class);
    }

}
