package com.example.splityourbillsandroid.data;

import android.content.Context;


import com.example.splityourbillsandroid.data.models.DefaultResponse;
import com.example.splityourbillsandroid.data.models.authentication.response.JWTResponse;
import com.example.splityourbillsandroid.data.models.authentication.body.LoginBody;
import com.example.splityourbillsandroid.data.models.authentication.body.RegisterBody;
import com.example.splityourbillsandroid.data.models.authentication.response.ProfileResponse;
import com.example.splityourbillsandroid.data.models.spaces.body.SpaceBody;
import com.example.splityourbillsandroid.data.models.spaces.body.SpaceMembersBody;
import com.example.splityourbillsandroid.data.models.spaces.response.AddNewSpaceResponse;
import com.example.splityourbillsandroid.data.models.spaces.response.SpaceMembersResponse;
import com.example.splityourbillsandroid.data.models.spaces.response.SpaceResponse;
import com.example.splityourbillsandroid.data.models.transactions.TransactionBody;
import com.example.splityourbillsandroid.data.models.transactions.TransactionsResponse;
import com.example.splityourbillsandroid.data.prefs.AppPreferencesHelper;
import com.example.splityourbillsandroid.data.retrofit.AppApiHelper;
import com.example.splityourbillsandroid.di.scopes.ApplicationContext;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import retrofit2.Response;

@Singleton
public class AppDataManager implements AppDataManagerHelper{

    private Context context;
    private AppPreferencesHelper preferencesHelper;
    private AppApiHelper apiHelper;
    private static final String TAG = "AppDataManager";

    @Inject
    public AppDataManager(@ApplicationContext Context context, AppPreferencesHelper appPreferencesHelper
    , AppApiHelper apiHelper){
        this.context = context;
        this.preferencesHelper = appPreferencesHelper;
        this.apiHelper = apiHelper;
    }
    @Override
    public String getAccessToken() {
        return preferencesHelper.getAccessToken();
    }
    @Override
    public void setAccessToken(String token) {
        preferencesHelper.setAccessToken(token);
    }
    @Override
    public String getUserId() {
        return preferencesHelper.getUserId();
    }

    @Override
    public Observable<Response<DefaultResponse>> registerUser(RegisterBody authBody) {
        return apiHelper.registerUser(authBody);
    }

    @Override
    public Observable<Response<JWTResponse>> loginUser(LoginBody authBody) {
        return apiHelper.loginUser(authBody);
    }

    @Override
    public Observable<Response<ProfileResponse>> getProfile() {
        return apiHelper.getProfile();
    }

    @Override
    public Observable<Response<List<SpaceResponse>>> getSpacesByUserId() {
        return apiHelper.getSpacesByUserId();
    }

    @Override
    public Observable<Response<List<TransactionsResponse>>> getTransactionsBySpaceId(long spaceId) {
        return apiHelper.getTransactionsBySpaceId(spaceId);
    }

    @Override
    public Observable<Response<AddNewSpaceResponse>> addNewSpace(SpaceBody spaceBody) {
        return apiHelper.addNewSpace(spaceBody);
    }

    @Override
    public Observable<Response<List<SpaceMembersResponse>>> getMembersBySpaceId(long spaceId) {
        return apiHelper.getMembersBySpaceId(spaceId);
    }

    @Override
    public Observable<Response<DefaultResponse>> addMemberToSpaceOrInvite(SpaceMembersBody spaceMembersBody) {
        return apiHelper.addMemberToSpaceOrInvite(spaceMembersBody);
    }

    @Override
    public Observable<Response<DefaultResponse>> addTransactions(List<TransactionBody> transactionBodies) {
        return apiHelper.addTransactions(transactionBodies);
    }


}
