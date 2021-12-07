package com.example.weatherapi.ui.weatherFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapi.common.Resource;
import com.example.weatherapi.data.models.Weather;
import com.example.weatherapi.data.repository.MainRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WeatherViewModel extends ViewModel {

    private MainRepository repository;
    public LiveData<Resource<Weather>> tempLiveData;

    @Inject
    public WeatherViewModel(MainRepository repository) {
        this.repository = repository;
    }
    public void fetchTemp(String lat,String lon){
        tempLiveData = repository.getTemp(lat, lon);
    }
}
