package com.vlg.alex.weatherrestopen.Network;

import com.vlg.alex.weatherrestopen.Network.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alex on 20.05.2017.
 */

public class RetrofitClient {

    private final static  String BASE_URL = "http://api.openweathermap.org/";

    private static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiService getApiService(){
        return getRetrofitInstance().create(ApiService.class);
    }
}