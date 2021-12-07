package com.example.weatherapi.data.local.converter;

import androidx.room.TypeConverter;

import com.example.weatherapi.data.models.Main;
import com.example.weatherapi.data.models.Wind;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class WindConverter {
    @TypeConverter
    public String fromMainString(Wind wind) {
        if (wind == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Wind>() {
        }.getType();
        return gson.toJson(wind, type);
    }

    @TypeConverter
    public Wind fromMainString(String mainString) {
        if (mainString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Wind>() {
        }.getType();
        return gson.fromJson(mainString, type);
    }
}


