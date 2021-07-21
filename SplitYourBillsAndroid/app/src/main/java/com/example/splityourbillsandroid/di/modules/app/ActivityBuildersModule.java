package com.example.splityourbillsandroid.di.modules.app;

import com.example.splityourbillsandroid.di.modules.main.MainActivityFragmentModule;
import com.example.splityourbillsandroid.di.modules.main.MainActivityModule;
import com.example.splityourbillsandroid.di.scopes.PerActivity;
import com.example.splityourbillsandroid.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = {MainActivityModule.class, MainActivityFragmentModule.class})
    @PerActivity
    abstract MainActivity bindMainActivity();



}
