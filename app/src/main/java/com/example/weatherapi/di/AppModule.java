package com.example.weatherapi.di;

import android.content.Context;

import com.example.weatherapi.data.local.AppDataBase;
import com.example.weatherapi.data.local.RoomClient;
import com.example.weatherapi.data.local.WeatherDao;
import com.example.weatherapi.data.remote.RetrofitClient;
import com.example.weatherapi.data.remote.WeatherApi;
import com.example.weatherapi.data.repository.MainRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn({SingletonComponent.class})
public abstract class AppModule {

    @Provides
    public static WeatherApi provideApi(){
        return new RetrofitClient().provideApi();
    }
    @Provides
    public static MainRepository provideMainRepository(WeatherApi api, WeatherDao dao){
        return new MainRepository(api,dao);
    }
    @Provides
    public static AppDataBase provideApp(@ApplicationContext Context context){
        return new RoomClient().provideDataBase(context);
    }
    @Provides
    public static WeatherDao provideRoom(AppDataBase dataBase){
        return dataBase.weatherDao();
    }


}
