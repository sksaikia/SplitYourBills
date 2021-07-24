package com.example.splityourbillsandroid.di.modules.auth;



import com.example.splityourbillsandroid.ui.auth.login.LoginFragment;
import com.example.splityourbillsandroid.ui.auth.register.SignupFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AuthActivityFragmentModule {


    @ContributesAndroidInjector
    abstract LoginFragment bindLoginFragment();
    @ContributesAndroidInjector
    abstract SignupFragment bindSignUpFragment();

}
