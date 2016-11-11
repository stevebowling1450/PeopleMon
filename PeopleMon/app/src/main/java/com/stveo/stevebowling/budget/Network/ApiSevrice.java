package com.stveo.stevebowling.budget.Network;

import com.stveo.stevebowling.budget.Models.Account;
import com.stveo.stevebowling.budget.Models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by stevebowling on 10/31/16.
 */

public interface ApiSevrice {

    @FormUrlEncoded
    @POST("token")
    Call<Account>login(@Field(value = "grant_type", encoded = true) String grantType,
                       @Field(value = "username", encoded = true) String username,
                       @Field(value = "password", encoded = true) String password);

    @POST("api/Account/Register")
    Call<Void>register(@Body Account account);

    @POST("v1/User/CheckIn")
    Call<Void>checkin(@Body User checkin);

    @GET("v1/User/Nearby")
    Call<User[]>findNearby(@Query("radiusInMeters") Integer radiusInMeters);

   @POST("v1/User/Catch")
    Call<User>catchUser(@Body User caughtUserId);

    @POST("api/Account/UserInfo")
    Call<Account>editProfile(@Body Account update);

    @GET("v1/User/Caught")
    Call<User[]>caughtList();

    @GET("api/Account/UserInfo")
    Call<Account>myInfo();

}
