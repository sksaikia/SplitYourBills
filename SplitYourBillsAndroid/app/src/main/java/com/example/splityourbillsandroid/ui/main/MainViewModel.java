package com.example.splityourbillsandroid.ui.main;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.splityourbillsandroid.base.BaseViewModel;
import com.example.splityourbillsandroid.data.AppDataManager;
import com.example.splityourbillsandroid.data.models.DefaultResponse;
import com.example.splityourbillsandroid.data.models.authentication.response.ProfileResponse;
import com.example.splityourbillsandroid.data.models.spaces.body.SpaceBody;
import com.example.splityourbillsandroid.data.models.spaces.body.SpaceMembersBody;
import com.example.splityourbillsandroid.data.models.spaces.response.AddNewSpaceResponse;
import com.example.splityourbillsandroid.data.models.spaces.response.SpaceMembersResponse;
import com.example.splityourbillsandroid.data.models.spaces.response.SpaceResponse;
import com.example.splityourbillsandroid.data.models.transactions.TransactionBody;
import com.example.splityourbillsandroid.data.models.transactions.TransactionsResponse;

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
    private MutableLiveData<List<TransactionsResponse>> transacrionResponse;
    private MutableLiveData<AddNewSpaceResponse> spaceDetails;
    private MutableLiveData<List<SpaceMembersResponse>> spaceMembers;
    private MutableLiveData<ProfileResponse> profileResponse;



    private MutableLiveData<Integer> spaceResponseStatus;
    private MutableLiveData<Integer> transactionResponseStatus;
    private MutableLiveData<Integer> createSpaceStatus;
    private MutableLiveData<Integer> spaceMembersStatus;
    private MutableLiveData<Integer> addSpaceMembersStatus;
    private MutableLiveData<Long> transactionAmount;
    private MutableLiveData<Long> userId;
    private MutableLiveData<Integer> saveTransactionResponse;

    public MutableLiveData<Long> getTransactionAmount() {
        if (transactionAmount==null)
            transactionAmount = new MutableLiveData<>();
        return transactionAmount;
    }

    public void setTransactionAmount(long amount) {
        if (transactionAmount==null)
            transactionAmount = new MutableLiveData<>();
        transactionAmount.setValue(amount);
    }

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
    public LiveData<List<TransactionsResponse>> getTransactionResponse(){
        if (transacrionResponse==null)
            transacrionResponse = new MutableLiveData<>();
        return transacrionResponse;
    }
    public LiveData<Integer> getTransactionResponseStatus(){
        if (transactionResponseStatus==null)
            transactionResponseStatus = new MutableLiveData<>();
        return transactionResponseStatus;
    }
    public LiveData<Integer> createStatusSpace(){
        if (createSpaceStatus==null)
            createSpaceStatus = new MutableLiveData<>();
        return createSpaceStatus;
    }
    public LiveData<AddNewSpaceResponse> getSpaceDetails(){
        if (spaceDetails==null)
            spaceDetails = new MutableLiveData<>();
        return spaceDetails;
    }
    public LiveData<Integer> getSpaceMembersStatus(){
        if (spaceMembersStatus==null)
            spaceMembersStatus = new MutableLiveData<>();
        return spaceMembersStatus;
    }
    public LiveData<List<SpaceMembersResponse>> getSpaceMembers(){
        if (spaceMembers==null)
            spaceMembers = new MutableLiveData<>();
        return spaceMembers;
    }
    public LiveData<Integer> addSpaceMembersStatus(){
        if (addSpaceMembersStatus==null)
            addSpaceMembersStatus = new MutableLiveData<>();
        return addSpaceMembersStatus;
    }
    public MutableLiveData<ProfileResponse> getProfileResponse() {
        if (profileResponse==null)
            profileResponse = new MutableLiveData<>();
        return profileResponse;
    }
    public MutableLiveData<Long> getPersonId(){
        if (userId==null) {
            userId = new MutableLiveData<>();
            userId.setValue(Long.valueOf(0));
        }
        return userId;
    }
    public void setPersonId(long personId){
        if (userId==null)
            userId = new MutableLiveData<>();
        userId.setValue(personId);

    }
    public MutableLiveData<Integer> getResponseForTXNSave(){
        if (saveTransactionResponse==null)
            saveTransactionResponse = new MutableLiveData<>();
        return saveTransactionResponse;
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

    public void getTransactionBySpaceId(long spaceId){
        if (transacrionResponse==null)
            transacrionResponse = new MutableLiveData<>();
        if (transactionResponseStatus==null)
            transactionResponseStatus = new MutableLiveData<>();

        appDataManager.getTransactionsBySpaceId(spaceId).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<List<TransactionsResponse>>>() {
            @Override
             public void onSubscribe(@NonNull Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(@NonNull Response<List<TransactionsResponse>> listResponse) {
                transactionResponseStatus.setValue(listResponse.code());
                if (listResponse.code()==200){
                    transacrionResponse.setValue(listResponse.body());
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                transactionResponseStatus.setValue(500);
            }

            @Override
            public void onComplete() {

            }
        });

    }

    public void createANewSpace(SpaceBody spaceBody){
        if (createSpaceStatus==null)
            createSpaceStatus = new MutableLiveData<>();
        if (spaceDetails==null)
            spaceDetails = new MutableLiveData<>();
        appDataManager.addNewSpace(spaceBody).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<AddNewSpaceResponse>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(@NonNull Response<AddNewSpaceResponse> defaultResponseResponse) {
                        if (defaultResponseResponse.code()==201) {
                            createSpaceStatus.setValue(201);
                            long spaceId = defaultResponseResponse.body().getSpaceId();
                            String spaceName = defaultResponseResponse.body().getSpaceName();
                            AddNewSpaceResponse response = new AddNewSpaceResponse(spaceId,spaceName);
                            spaceDetails.setValue(response);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        createSpaceStatus.setValue(500);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setSpaceDetails(AddNewSpaceResponse response){
        if (spaceDetails==null)
            spaceDetails = new MutableLiveData<>();
        spaceDetails.setValue(response);

    }

    public void getSpaceMembersBySpaceId(long spaceId){
        if (spaceMembersStatus==null)
            spaceMembersStatus = new MutableLiveData<>();
        if (spaceMembers==null)
            spaceMembers = new MutableLiveData<>();

        appDataManager.getMembersBySpaceId(spaceId).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<List<SpaceMembersResponse>>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(@NonNull Response<List<SpaceMembersResponse>> listResponse) {
                spaceMembersStatus.setValue(listResponse.code());
                if (listResponse.code()==200){
                    spaceMembers.setValue(listResponse.body());
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                spaceMembersStatus.setValue(500);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void addNewMemberInSpace(SpaceMembersBody spaceMembersBody){
        if (addSpaceMembersStatus==null)
            addSpaceMembersStatus = new MutableLiveData<>();
        appDataManager.addMemberToSpaceOrInvite(spaceMembersBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<DefaultResponse>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(@NonNull Response<DefaultResponse> defaultResponseResponse) {
                addSpaceMembersStatus.setValue(defaultResponseResponse.code());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                addSpaceMembersStatus.setValue(500);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void getProfileDetails(){
        if (profileResponse==null)
            profileResponse = new MutableLiveData<>();

        appDataManager.getProfile().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ProfileResponse>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(@NonNull Response<ProfileResponse> profileResponseResponse) {
                        profileResponse.setValue(profileResponseResponse.body());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void resetSpaceMembersStatus() {
        addSpaceMembersStatus = new MutableLiveData<>();
    }

    public void addTransaction(List<TransactionBody> list){
        if (saveTransactionResponse==null)
            saveTransactionResponse = new MutableLiveData<>();
        appDataManager.addTransactions(list).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<DefaultResponse>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(@NonNull Response<DefaultResponse> defaultResponseResponse) {
                        if (defaultResponseResponse.code()==200){
                            saveTransactionResponse.setValue(200);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        saveTransactionResponse.setValue(500);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void logout(){
        appDataManager.setAccessToken("");
    }


}
