package com.example.weatherapi.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.weatherapi.data.models.Weather;

@Dao


public interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Weather weather);
    @Query("SELECT * FROM weather")
    Weather getWeather();
}
