package com.example.splityourbillsandroid.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.splityourbillsandroid.R;
import com.example.splityourbillsandroid.ui.auth.AuthActivity;
import com.example.splityourbillsandroid.ui.auth.AuthViewModel;
import com.example.splityourbillsandroid.ui.main.MainActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class SplashActivity extends AppCompatActivity  {


    @Inject
    SplashViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        AndroidInjection.inject(this);


        String token =  viewModel.getAccessToken();

        System.out.println("Token : " + token);


        //TODO do a network call to check validity of the user token to the test path

        if (token==null || token.isEmpty()) {
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    /* Create an Intent that will start the Menu-Activity. */
                    Intent mainIntent = new Intent(SplashActivity.this, AuthActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
                }
            }, 1000);
        }else {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    /* Create an Intent that will start the Menu-Activity. */
                    Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
                }
            }, 1000);

        }
    }


}