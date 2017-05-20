package com.vlg.alex.weatherrestopen.Network;

import com.vlg.alex.weatherrestopen.Models.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Alex on 20.05.2017.
 */

public interface ApiService {

    @GET("/data/2.5/forecast/daily")
    Call<WeatherData> getData(@Query("q") String city,@Query("cnt") int days,@Query("units") String units, @Query("appid") String apiKey);
}