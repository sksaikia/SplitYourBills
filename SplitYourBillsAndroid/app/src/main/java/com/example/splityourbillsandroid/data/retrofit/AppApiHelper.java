package com.example.splityourbillsandroid.data.retrofit;



import com.example.splityourbillsandroid.data.models.DefaultResponse;
import com.example.splityourbillsandroid.data.models.authentication.response.JWTResponse;
import com.example.splityourbillsandroid.data.models.authentication.body.LoginBody;
import com.example.splityourbillsandroid.data.models.authentication.body.RegisterBody;
import com.example.splityourbillsandroid.data.models.spaces.body.SpaceBody;
import com.example.splityourbillsandroid.data.models.spaces.body.SpaceMembersBody;
import com.example.splityourbillsandroid.data.models.spaces.response.AddNewSpaceResponse;
import com.example.splityourbillsandroid.data.models.spaces.response.SpaceMembersResponse;
import com.example.splityourbillsandroid.data.models.spaces.response.SpaceResponse;
import com.example.splityourbillsandroid.data.models.transactions.TransactionsResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AppApiHelper implements APIInterface{

    APIInterface api;

    @Inject
    public AppApiHelper(Retrofit retrofit) {
        api = retrofit.create(APIInterface.class);
    }


    @Override
    public Observable<Response<DefaultResponse>> registerUser(RegisterBody authBody) {
        return api.registerUser(authBody);
    }

    @Override
    public Observable<Response<JWTResponse>> loginUser(LoginBody authBody) {
        return api.loginUser(authBody);
    }

    @Override
    public Observable<Response<List<SpaceResponse>>> getSpacesByUserId() {
        return api.getSpacesByUserId();
    }

    @Override
    public Observable<Response<List<TransactionsResponse>>> getTransactionsBySpaceId(long spaceId) {
        return api.getTransactionsBySpaceId(spaceId);
    }

    @Override
    public Observable<Response<AddNewSpaceResponse>> addNewSpace(SpaceBody spaceBody) {
        return api.addNewSpace(spaceBody);
    }

    @Override
    public Observable<Response<List<SpaceMembersResponse>>> getMembersBySpaceId(long spaceId) {
        return api.getMembersBySpaceId(spaceId);
    }

    @Override
    public Observable<Response<DefaultResponse>> addMemberToSpaceOrInvite(SpaceMembersBody spaceMembersBody) {
        return api.addMemberToSpaceOrInvite(spaceMembersBody);
    }


}
