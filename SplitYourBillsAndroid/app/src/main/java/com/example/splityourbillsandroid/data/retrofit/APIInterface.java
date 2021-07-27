package com.example.splityourbillsandroid.data.retrofit;


import com.example.splityourbillsandroid.data.models.DefaultResponse;
import com.example.splityourbillsandroid.data.models.authentication.JWTResponse;
import com.example.splityourbillsandroid.data.models.authentication.LoginBody;
import com.example.splityourbillsandroid.data.models.authentication.RegisterBody;
import com.example.splityourbillsandroid.data.models.spaces.SpaceBody;
import com.example.splityourbillsandroid.data.models.spaces.AddNewSpaceResponse;
import com.example.splityourbillsandroid.data.models.spaces.SpaceMembersResponse;
import com.example.splityourbillsandroid.data.models.spaces.SpaceResponse;
import com.example.splityourbillsandroid.data.models.spaces.TransactionsResponse;

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







    //Spaces
    @GET("api/spaces/")
    Observable<Response<List<SpaceResponse>>> getSpacesByUserId();

    @GET("api/transactions/{spaceId}")
    Observable<Response<List<TransactionsResponse>>> getTransactionsBySpaceId(@Path("spaceId") long spaceId);

    @POST("api/spaces/add")
    Observable<Response<AddNewSpaceResponse>> addNewSpace(@Body SpaceBody spaceBody);

    @GET("api/spacemember/{spaceId}")
    Observable<Response<List<SpaceMembersResponse>>> getMembersBySpaceId(@Path("spaceId") long spaceId);


}
