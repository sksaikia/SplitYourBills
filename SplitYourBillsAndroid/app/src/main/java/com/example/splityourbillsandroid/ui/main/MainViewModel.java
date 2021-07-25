package com.example.splityourbillsandroid.ui.main;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.splityourbillsandroid.base.BaseViewModel;
import com.example.splityourbillsandroid.data.AppDataManager;
import com.example.splityourbillsandroid.data.models.spaces.SpaceResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MainViewModel extends BaseViewModel {

    private AppDataManager appDataManager;
    public static final String TAG = "MainActivityViewModel";

    @Inject
    public MainViewModel(AppDataManager appDataManager, Application application) {
        super(appDataManager, application);
        this.appDataManager = appDataManager;
    }

    private MutableLiveData<List<SpaceResponse>> spaceResponse;




    private MutableLiveData<Integer> spaceResponseStatus;



    public LiveData<Integer> getStatusSpaceResponse(){
        if (spaceResponseStatus==null)
            spaceResponseStatus = new MutableLiveData<>();
        return spaceResponseStatus;
    }
    public LiveData<List<SpaceResponse>> getSpaceResponse(){
        if (spaceResponse==null)
            spaceResponse = new MutableLiveData<>();
        return spaceResponse;
    }




    public void getSpaceById(){
        if (spaceResponseStatus==null)
            spaceResponseStatus = new MutableLiveData<>();
        if (spaceResponse==null)
            spaceResponse = new MutableLiveData<>();

        appDataManager.getSpacesByUserId().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<List<SpaceResponse>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(@NonNull Response<List<SpaceResponse>> listResponse) {
                        spaceResponseStatus.setValue(listResponse.code());
                        if (listResponse.code()==200){
                            spaceResponse.setValue(listResponse.body());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        spaceResponseStatus.setValue(500);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public String getAccessToken(){
        Log.d(TAG, "getAccessToken: " + appDataManager.getAccessToken());
        if (appDataManager.getAccessToken()==null || appDataManager.getAccessToken()=="")
            return "";
        else
            return appDataManager.getAccessToken();
    }



}
