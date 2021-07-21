package com.example.splityourbillsandroid.di.components;

import android.app.Application;

import com.example.splityourbillsandroid.base.BaseApplication;
import com.example.splityourbillsandroid.di.modules.app.ActivityBuildersModule;
import com.example.splityourbillsandroid.di.modules.app.AppModule;
import com.example.splityourbillsandroid.di.modules.app.RetrofitModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = { ActivityBuildersModule.class
        ,AndroidInjectionModule.class,
        AppModule.class, RetrofitModule.class})
public interface AppComponent {

    void inject(BaseApplication application);

    @Component.Builder
    interface Builder {
        AppComponent build();

        @BindsInstance
        Builder application(Application application);
    }

}
