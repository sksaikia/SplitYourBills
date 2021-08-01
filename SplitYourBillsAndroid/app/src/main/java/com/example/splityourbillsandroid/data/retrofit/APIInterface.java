package com.example.splityourbillsandroid.data.retrofit;


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

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {

    //Authentication
    @POST("api/auth/signup")
    Observable<Response<DefaultResponse>> registerUser(@Body RegisterBody authBody);
    @POST("api/auth/signin")
    Observable<Response<JWTResponse>> loginUser(@Body LoginBody authBody);
    @GET("api/user/me")
    Observable<Response<ProfileResponse>> getProfile();






    //Spaces
    @GET("api/spaces/")
    Observable<Response<List<SpaceResponse>>> getSpacesByUserId();

    @GET("api/transactions/{spaceId}")
    Observable<Response<List<TransactionsResponse>>> getTransactionsBySpaceId(@Path("spaceId") long spaceId);

    @POST("api/spaces/add")
    Observable<Response<AddNewSpaceResponse>> addNewSpace(@Body SpaceBody spaceBody);

    @GET("api/spacemember/{spaceId}")
    Observable<Response<List<SpaceMembersResponse>>> getMembersBySpaceId(@Path("spaceId") long spaceId);

    @POST("api/spacemember/add")
    Observable<Response<DefaultResponse>> addMemberToSpaceOrInvite(@Body SpaceMembersBody spaceMembersBody);


    //Transactions
    @POST("api/transactions/add")
    Observable<Response<DefaultResponse>> addTransactions(@Body List<TransactionBody> transactionBodies);

}
