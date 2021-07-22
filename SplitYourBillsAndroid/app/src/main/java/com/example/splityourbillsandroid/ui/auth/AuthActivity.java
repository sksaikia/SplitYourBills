package com.example.splityourbillsandroid.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.splityourbillsandroid.R;
import com.example.splityourbillsandroid.ui.auth.login.LoginFragment;
import com.example.splityourbillsandroid.ui.auth.register.SignupFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class AuthActivity extends AppCompatActivity  implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    FragmentManager fragmentManager;

    @Inject
    LoginFragment loginFragment;

    @Inject
    SignupFragment signupFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);


        AndroidInjection.inject(this);

        fragmentManager = getSupportFragmentManager();


        initFrag(loginFragment);

    }


        private void initFrag(Fragment fragment) {

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.frame_layout_main, fragment);
        ft.commit();


    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

}