package com.myapplicationdev.android.weathery;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView city, temperature, condition, humidity, maxTemperature, minTemperature, pressure, wind;
    private ImageView imageView;
    private FloatingActionButton floatingActionButton;

    private LocationListener locationListener;
    private LocationManager locationManager;
    private Double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = findViewById(R.id.tvCity);
        temperature = findViewById(R.id.tvTemperature);
        condition = findViewById(R.id.tvCondition);
        humidity = findViewById(R.id.tvHumdity);
        maxTemperature = findViewById(R.id.tvMaxTemp);
        minTemperature = findViewById(R.id.tvMinTemp);
        pressure = findViewById(R.id.tvPressure);
        wind = findViewById(R.id.tvWind);
        imageView = findViewById(R.id.imageView);
        floatingActionButton = findViewById(R.id.floatingActionButton);


        floatingActionButton.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
            startActivity(intent);

        });

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(@NonNull Location location) {

                latitude = location.getLatitude();
                longitude = location.getLongitude();

                Log.i("Latitude: ", String.valueOf(latitude));
                Log.i("Longitude: ", String.valueOf(longitude));

                getWeatherData(latitude, longitude);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {

            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {

            }
        };


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 50, locationListener);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull @org.jetbrains.annotations.NotNull String[] permissions,
                                           @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1 && permissions.length > 0
                && ContextCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 50, locationListener);
        }


    }

    public void getWeatherData(double lat, double lon) {

        WeatherAPI weatherAPI = RetroFitWeather.getClient().create(WeatherAPI.class);
        Call<WeatherData> call = weatherAPI.getWeatherWithLocation(lat, lon);


        call.enqueue(new Callback<WeatherData>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NotNull Call<WeatherData> call, @NotNull Response<WeatherData> response) {

                // °C
                city.setText(response.body().getName() + " , " + response.body().getSys().getCountry());
                temperature.setText(response.body().getMain().getTemp() + " °C");
                condition.setText(response.body().getWeather().get(0).getDescription());
                humidity.setText(" : " + response.body().getMain().getHumidity() + "%");
                maxTemperature.setText(" : " + response.body().getMain().getTempMax() + " °C");
                maxTemperature.setText(" : " + response.body().getMain().getTempMin() + " °C");
                pressure.setText(" : " + response.body().getMain().getPressure());
                wind.setText(" : " + response.body().getWind().getSpeed());

                String iconCode = response.body().getWeather().get(0).getIcon();
                Picasso.get()
                        .load("https://openweathermap.org/img/wn/" + iconCode + "@2x.png")
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(imageView);


            }

            @Override
            public void onFailure(@NotNull Call<WeatherData> call, Throwable t) {

            }
        });
    }


}