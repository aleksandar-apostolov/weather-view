package com.aapii.codecamp.weatherview.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aapii.codecamp.weatherview.service.LocationService;
import com.aapii.codecamp.weatherview.service.WeatherService;
import com.aapii.codecamp.weatherview.ui.WeatherFrameLayout;
import com.netcetera.weather.R;

public class ExampleActivity extends AppCompatActivity {

  private static final String LOG_TAG = "ExampleActivity";


  private static final String API_KEY = "3ac4c8e628d0cb6cedd2ded241cd73b1";
  private WeatherFrameLayout weatherLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_example);
    WeatherService.setApyKey(API_KEY);
    weatherLayout = (WeatherFrameLayout) findViewById(R.id.weatherLayout);
    LocationService.setup(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    LocationService.subscribe(this, weatherLayout);
  }

  @Override
  protected void onPause() {
    super.onPause();
    LocationService.remove(weatherLayout);
  }
}
