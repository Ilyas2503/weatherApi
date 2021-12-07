package com.example.weatherapi.data.local.converter;

import androidx.room.TypeConverter;

import com.example.weatherapi.data.models.Main;
import com.example.weatherapi.data.models.Weather;
import com.example.weatherapi.data.models.Weather__1;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Weather1Converter {
    @TypeConverter
    public String fromMainString(List<Weather__1> weather__1) {
        if (weather__1 == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Weather__1>>() {}.getType();
        return gson.toJson(weather__1, type);
    }
    @TypeConverter
    public List<Weather__1> fromMainString(String mainString){
        if (mainString == null) {
        return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Weather__1>>() {}.getType();
        return gson.fromJson(mainString,type);
        }
    }


