package com.myapplicationdev.android.weathery;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private TextView city, temperature, condition, humidity, maxTemperature, minTemperature, pressure, wind;
    private ImageView imageView;
    private FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = findViewById(R.id.tvCity);
        temperature = findViewById(R.id.tvTemperature);
        condition = findViewById(R.id.tvCondition);
        humidity = findViewById(R.id.tvHumidity);
        maxTemperature = findViewById(R.id.tvMaxTemperature);
        minTemperature = findViewById(R.id.tvMinTemperature);
        pressure = findViewById(R.id.tvPressure);
        wind = findViewById(R.id.tvWind);
        imageView = findViewById(R.id.imageView);
        floatingActionButton = findViewById(R.id.floatingActionButton);


        floatingActionButton.setOnClickListener(v -> {


        });

    }
}