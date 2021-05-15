package com.myapplicationdev.android.weathery;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitWeather {

    private static Retrofit retrofit;

    public static Retrofit getClient() {


        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.openweatherma.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}
