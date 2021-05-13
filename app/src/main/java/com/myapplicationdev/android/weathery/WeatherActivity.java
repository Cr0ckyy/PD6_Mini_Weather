package com.myapplicationdev.android.weathery;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
        weatherSearchButton = findViewById(R.id.tvweatherSearchButton);
        weatherEditText = findViewById(R.id.tvWeatherEditText);


        weatherSearchButton.setOnClickListener(v -> {


        });

    }
}