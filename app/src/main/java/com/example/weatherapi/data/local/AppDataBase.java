package com.example.weatherapi.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.weatherapi.data.local.converter.MainConverter;
import com.example.weatherapi.data.models.Weather;

@Database(entities = {Weather.class}, version = 1)
@TypeConverters({MainConverter.class})
public abstract class AppDataBase extends RoomDatabase {
    public abstract WeatherDao weatherDao();
}
