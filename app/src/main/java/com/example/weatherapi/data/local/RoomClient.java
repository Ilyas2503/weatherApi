package com.example.weatherapi.data.local;

import android.content.Context;

import androidx.room.Room;

public class RoomClient {
    public AppDataBase provideDataBase(Context context) {
        return Room.databaseBuilder(
                context, AppDataBase.class, "weather_database"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }
}
