package com.example.splityourbillsandroid.di.modules.app;

import com.example.splityourbillsandroid.di.modules.auth.main.AuthActivityFragmentModule;
import com.example.splityourbillsandroid.di.modules.auth.main.AuthActivityModule;
import com.example.splityourbillsandroid.di.modules.main.MainActivityFragmentModule;
import com.example.splityourbillsandroid.di.modules.main.MainActivityModule;
import com.example.splityourbillsandroid.di.scopes.PerActivity;
import com.example.splityourbillsandroid.ui.auth.AuthActivity;
import com.example.splityourbillsandroid.ui.main.MainActivity;

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

}
