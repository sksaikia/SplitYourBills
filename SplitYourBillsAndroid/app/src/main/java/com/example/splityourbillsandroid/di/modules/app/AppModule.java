package com.example.splityourbillsandroid.di.modules.app;

import android.app.Application;
import android.content.Context;

import com.example.splityourbillsandroid.data.prefs.PreferencesInfo;
import com.example.splityourbillsandroid.di.scopes.ApplicationContext;
import com.example.splityourbillsandroid.utils.Constants;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class AppModule {

    @Binds
    @Singleton
    @ApplicationContext
    abstract Context provideContext(Application application);

    @Provides
    @Singleton
    @PreferencesInfo
    static String providePrefFileName(){
        return Constants.PREF_FILE_NAME;
    }


}
