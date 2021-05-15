package com.myapplicationdev.android.weathery;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface WeatherAPI {


    @GET("weather?appid=2a0782217de9bdc9a15ecc6fada8eb53&units=metric")
    Call<WeatherData> getWeatherWithLocation(@Query("lat") double lat, @Query("lon") double lon);

    @GET("weather?appid=2a0782217de9bdc9a15ecc6fada8eb53&units=metric")
    Call<WeatherData> getWeatherWithCityName(@Query("q") String cityName);
}
