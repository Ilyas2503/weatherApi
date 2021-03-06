package com.example.weatherapi.data.remote;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private OkHttpClient provadeOkHttp() {
        return new OkHttpClient.Builder()
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS).addInterceptor(provadeMetod()).build();
    }
    private Interceptor provadeMetod() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    }
    private Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory
            .create()).client(provadeOkHttp())
            .baseUrl("https://api.openweathermap.org/data/2.5/").build();

    public WeatherApi provideApi(){
        return retrofit.create(WeatherApi.class);
    }
}
