package com.example.splityourbillsandroid.ui.auth;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.splityourbillsandroid.base.BaseViewModel;
import com.example.splityourbillsandroid.data.AppDataManager;
import com.example.splityourbillsandroid.data.models.DefaultResponse;
import com.example.splityourbillsandroid.data.models.authentication.response.JWTResponse;
import com.example.splityourbillsandroid.data.models.authentication.body.LoginBody;
import com.example.splityourbillsandroid.data.models.authentication.body.RegisterBody;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class AuthViewModel extends BaseViewModel {

    private AppDataManager appDataManager;
    public static final String TAG = "MainActivityViewModel";


    private MutableLiveData<Integer> statusRegister;
    private MutableLiveData<Integer> statusLogin;

    public LiveData<Integer> getStatusRegister(){
        if (statusRegister==null)
            statusRegister = new MutableLiveData<>();
        return statusRegister;
    }
    public LiveData<Integer> getStatusLogin(){
        if (statusLogin==null)
            statusLogin = new MutableLiveData<>();
        return statusLogin;
    }




    @Inject
    public AuthViewModel(AppDataManager appDataManager, Application application) {
        super(appDataManager, application);
        this.appDataManager = appDataManager;
    }

    public void registerNewUser(RegisterBody authBody){
        if (statusRegister==null)
            statusRegister = new MutableLiveData<>();

        appDataManager.registerUser(authBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<DefaultResponse>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(@NonNull Response<DefaultResponse> defaultResponseResponse) {

                if (defaultResponseResponse.code()==201){
                    //log in here
                 //   login(new LoginBody(authBody.getEmail(),authBody.getPassword()));
                }

                statusRegister.setValue(defaultResponseResponse.code());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                statusRegister.setValue(500);
            }

            @Override
            public void onComplete() {

            }
        });



    }


    public void login(LoginBody loginBody){
        if (statusLogin==null)
            statusLogin = new MutableLiveData<>();
        appDataManager.loginUser(loginBody).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<JWTResponse>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(@NonNull Response<JWTResponse> defaultResponseResponse) {
                        statusLogin.setValue(defaultResponseResponse.code());
                        Log.d(TAG, "onNext: response login : " + defaultResponseResponse.body());
                        Log.d(TAG, "onNext: response login : " + defaultResponseResponse.code());

                        if (defaultResponseResponse.code()==200){
                            appDataManager.setAccessToken(defaultResponseResponse.body().getAccessToken());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        statusLogin.setValue(500);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }




}
