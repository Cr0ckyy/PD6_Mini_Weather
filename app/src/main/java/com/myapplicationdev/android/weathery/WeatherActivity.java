package com.myapplicationdev.android.weathery;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherActivity extends AppCompatActivity {

    private TextView weatherCity, weatherTemperature, weatherCondition, weatherHumidity, weatherMaxTemperature, weatherMinTemperature, weatherPressure, weatherWind;
    private ImageView weatherImageView;
    private Button weatherSearchButton;
    private EditText weatherEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);


        weatherCity = findViewById(R.id.tvWeatherCity);
        weatherTemperature = findViewById(R.id.tvWeatherTemperature);
        weatherCondition = findViewById(R.id.tvWeatherCondition);
        weatherHumidity = findViewById(R.id.tvWeatherHumidity);
        weatherMaxTemperature = findViewById(R.id.tvWeatherMaxTemperature);
        weatherMinTemperature = findViewById(R.id.tvWeatherMinTemperature);
        weatherPressure = findViewById(R.id.tvWeatherPressure);
        weatherWind = findViewById(R.id.tvWeatherWind);
        weatherImageView = findViewById(R.id.tvWeatherImageView);
        weatherSearchButton = findViewById(R.id.weatherSearchButton);
        weatherEditText = findViewById(R.id.weatherEditText);


        weatherSearchButton.setOnClickListener(v -> {

            String cityName = weatherEditText.getText().toString();

            getWeatherData(cityName);
            weatherEditText.setText("");
        });

    }

    public void getWeatherData(String cityName) {

        WeatherAPI weatherAPI = RetroFitWeather.getClient().create(WeatherAPI.class);
        Call<WeatherData> call = weatherAPI.getWeatherWithCityName(cityName);


        call.enqueue(new Callback<WeatherData>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {


                if (response.isSuccessful()) {
                    // °C
                    weatherCity.setText(response.body().getName() + " , " + response.body().getSys().getCountry());
                    weatherTemperature.setText(response.body().getMain().getTemp() + " °C");
                    weatherCondition.setText(response.body().getWeather().get(0).getDescription());
                    weatherHumidity.setText(" : " + response.body().getMain().getHumidity() + "%");
                    weatherMaxTemperature.setText(" : " + response.body().getMain().getTempMax() + " °C");
                    weatherMinTemperature.setText(" : " + response.body().getMain().getTempMin() + " °C");
                    weatherPressure.setText(" : " + response.body().getMain().getPressure());
                    weatherWind.setText(" : " + response.body().getWind().getSpeed());

                    String iconCode = response.body().getWeather().get(0).getIcon();
                    Picasso.get()
                            .load("https://openweathermap.org/img/wn/" + iconCode + "@2x.png")
                            .placeholder(R.drawable.ic_launcher_background)
                            .into(weatherImageView);
                } else {
                    String toastMSg = "The city you have searched is not found , please try again";

                    Toast.makeText(WeatherActivity.this, toastMSg, Toast.LENGTH_SHORT).show();


                }


            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {

            }
        });
    }
}