package com.example.weatherapi.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.weatherapi.common.Resource;
import com.example.weatherapi.data.local.WeatherDao;
import com.example.weatherapi.data.models.Weather;
import com.example.weatherapi.data.remote.WeatherApi;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {
    private WeatherApi api;
    private WeatherDao dao;

    @Inject
    public MainRepository(WeatherApi api,WeatherDao dao) {
        this.api = api;
        this.dao = dao;
    }
    public MutableLiveData <Resource<Weather>> getTemp(String lon, String lat){
        MutableLiveData <Resource<Weather>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getTemp(lat,lon, "3e73fbd5612554a6d35c7508d32740c1","metric").enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(Resource.success(response.body()));
                    dao.insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage(), null));

            }
        });
        return liveData;
    }


}
