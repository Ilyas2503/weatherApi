package com.example.weatherapi.ui.weatherFragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.weatherapi.R;
import com.example.weatherapi.base.BaseFragment;
import com.example.weatherapi.common.Resource;
import com.example.weatherapi.data.local.WeatherDao;
import com.example.weatherapi.data.models.Clouds;
import com.example.weatherapi.data.models.Main;
import com.example.weatherapi.data.models.Sys;
import com.example.weatherapi.data.models.Weather;
import com.example.weatherapi.data.models.Weather__1;
import com.example.weatherapi.data.models.Wind;
import com.example.weatherapi.databinding.FragmentWeatherBinding;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WeatherFragment extends BaseFragment<FragmentWeatherBinding> {

    private Main main;
    private Weather weather;
    private Wind wind;
    private Clouds clouds;
    private Sys sys;
    private NavController navController;
    private WeatherFragmentArgs args;
    private WeatherViewModel model;
    private ArrayList<Weather__1> weather__1 = new ArrayList<>();
    @Inject
    WeatherDao dao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity()
                .getSupportFragmentManager().findFragmentById(R.id.host_fragment);
        navController = navHostFragment.getNavController();
        if (getArguments()!= null){
            args = WeatherFragmentArgs.fromBundle(getArguments());
        }
    }

    @Override
    protected FragmentWeatherBinding bind() {
        return FragmentWeatherBinding.inflate(getLayoutInflater());
    }


    @Override
    protected void setupObserf() {
        model.tempLiveData.observe(getViewLifecycleOwner(), response -> {
            switch (response.status){
                case SUCCESS:
                    weather = response.data;
                    wind = response.data.getWind();
                    main = response.data.getMain();
                    sys = response.data.getSys();
                    weather__1 = (ArrayList<Weather__1>) response.data.getWeather();
                    binding.progress.setVisibility(View.GONE);
                    setWeather();
                    break;
                case ERROR:
                    Toast.makeText(requireContext(), "Нету интернета", Toast.LENGTH_SHORT).show();
                    binding.progress.setVisibility(View.GONE);
                    wind = dao.getWeather().getWind();
                    main = dao.getWeather().getMain();
                    sys = dao.getWeather().getSys();
                    weather = dao.getWeather();
                    weather__1 = (ArrayList<Weather__1>) dao.getWeather().getWeather();
                    setWeather();
                    break;
                case LOADING:
                    binding.progress.setVisibility(View.VISIBLE);
                    break;
            }
        });
    }

    @Override
    protected void setupUi() {
        binding.bishBtn.setOnClickListener(v -> navController.navigate(R.id.action_weatherFragment_to_mapFragment));
        model = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);
        model.fetchTemp(args.getLongitude(),args.getLatitude());
    }
    private void setWeather() {
        binding.sunnyTv.setText(weather__1.get(0).getMain());
        Glide.with(requireContext())
                .load("https://openweathermap.org/img/wn/" + weather__1.get(0).getIcon() + ".png")
                .override(100, 100)
                .into(binding.cloudIv);
        binding.gradTv.setText(String.valueOf((int) Math.round(main.getTempMax())));
        binding.kmHTv.setText((int) Math.round(wind.getSpeed()) + " m/ s");
        binding.bishBtn.setText(weather.getName());
        binding.numberTv.setText(String.valueOf((int) Math.round(main.getTemp())));
        binding.mBaaTv.setText(main.getPressure() + "mBar");
        binding.humidTv.setText(main.getHumidity() + "%");
        binding.grad1Iv.setText(String.valueOf((int) Math.round(main.getTempMin())));
    }
}