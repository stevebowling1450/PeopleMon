package com.stveo.stevebowling.budget.Network;

import com.stveo.stevebowling.budget.Models.Account;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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

}
