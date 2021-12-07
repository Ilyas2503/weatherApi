package com.example.weatherapi.data.remote;

import com.example.weatherapi.data.models.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface WeatherApi {

    @GET("weather?")
    Call<Weather> getTemp(
            @Query("lat") String lat,
            @Query("lon") String lon,
            @Query("appid") String appId,
            @Query("units") String metric
    );

}
