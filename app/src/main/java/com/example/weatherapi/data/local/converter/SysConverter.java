package com.example.weatherapi.data.local.converter;

import androidx.room.TypeConverter;

import com.example.weatherapi.data.models.Clouds;
import com.example.weatherapi.data.models.Main;
import com.example.weatherapi.data.models.Sys;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class SysConverter {
    @TypeConverter
    public String fromMainString(Sys sys) {
        if (sys == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Sys>() {}.getType();
        return gson.toJson(sys, type);
    }
    @TypeConverter
    public Sys fromMainString(String mainString){
        if (mainString == null) {
        return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Sys>() {}.getType();
        return gson.fromJson(mainString,type);
        }
    }


