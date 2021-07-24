package com.example.splityourbillsandroid.di.modules.app;

import com.example.splityourbillsandroid.di.modules.auth.AuthActivityFragmentModule;
import com.example.splityourbillsandroid.di.modules.auth.AuthActivityModule;
import com.example.splityourbillsandroid.di.modules.main.MainActivityFragmentModule;
import com.example.splityourbillsandroid.di.modules.main.MainActivityModule;
import com.example.splityourbillsandroid.di.modules.splash.SplashActivityModule;
import com.example.splityourbillsandroid.di.scopes.PerActivity;
import com.example.splityourbillsandroid.ui.auth.AuthActivity;
import com.example.splityourbillsandroid.ui.main.MainActivity;
import com.example.splityourbillsandroid.ui.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = {MainActivityModule.class, MainActivityFragmentModule.class})
    @PerActivity
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {AuthActivityModule.class, AuthActivityFragmentModule.class})
    @PerActivity
    abstract AuthActivity bindAuthActivity();

    @ContributesAndroidInjector(modules = {SplashActivityModule.class})
    @PerActivity
    abstract SplashActivity bindSplashActivity();

}
