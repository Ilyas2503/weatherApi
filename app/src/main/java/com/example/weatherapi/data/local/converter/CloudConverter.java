package com.example.weatherapi.data.local.converter;

import androidx.room.TypeConverter;

import com.example.weatherapi.data.models.Clouds;
import com.example.weatherapi.data.models.Main;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class CloudConverter {
    @TypeConverter
    public String fromMainString(Clouds clouds) {
        if (clouds == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Clouds>() {}.getType();
        return gson.toJson(clouds, type);
    }
    @TypeConverter
    public Clouds fromMainString(String clouds){
        if (clouds == null) {
        return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Clouds>() {}.getType();
        return gson.fromJson(clouds,type);
        }
    }


