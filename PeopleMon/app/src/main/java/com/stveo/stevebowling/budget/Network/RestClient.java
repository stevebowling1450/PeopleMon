package com.stveo.stevebowling.budget.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stveo.stevebowling.budget.PeopleMonApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by stevebowling on 10/31/16.
 */

public class RestClient {
    private ApiSevrice apiSevrice;

    public RestClient(){
        GsonBuilder builder=new GsonBuilder();
        builder.setDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
        Gson gson=builder.create();

        HttpLoggingInterceptor log = new HttpLoggingInterceptor();
        log.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new SessionRequestInterceptor())
                .addInterceptor(log)
                .build();

        Retrofit restAdpter =new Retrofit.Builder()
                .baseUrl(PeopleMonApplication.API_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiSevrice = restAdpter.create(ApiSevrice.class);
    }

    public ApiSevrice getApiSevrice() {return apiSevrice;
    }



}
