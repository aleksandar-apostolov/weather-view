package com.aapii.codecamp.weatherview.example;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.aapii.codecamp.weatherview.concurrent.BackgroundExecutor;
import com.aapii.codecamp.weatherview.model.WeatherModel;
import com.aapii.codecamp.weatherview.service.LocationService;
import com.aapii.codecamp.weatherview.service.WeatherService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.netcetera.weather.R;
import com.stanfy.enroscar.goro.FutureObserver;

import java.util.concurrent.Callable;

public class ExampleActivity extends AppCompatActivity implements LocationListener {

  private static final String LOG_TAG = "ExampleActivity";


  private static final String API_KEY = "3ac4c8e628d0cb6cedd2ded241cd73b1";
  private TextView textView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_example);
    textView = (TextView) findViewById(R.id.weatherText);
    WeatherService.setApyKey(API_KEY);
    LocationService.setup(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.example_menu, menu);
    return true;
  }

  @Override
  protected void onResume() {
    super.onResume();
    LocationService.subscribe(this, this);
  }

  @Override
  protected void onPause() {
    super.onPause();
    LocationService.remove(this);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onLocationChanged(final Location location) {
    BackgroundExecutor.dispatchAndReturn(new Callable<WeatherModel>() {
      @Override
      public WeatherModel call() throws Exception {
        return WeatherService.getWeather(location.getLatitude(), location.getLongitude());
      }
    }, new FutureObserver<WeatherModel>() {
      @Override
      public void onSuccess(WeatherModel weatherModel) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final String json = gson.toJson(weatherModel);
        Log.d(LOG_TAG, json);

        runOnUiThread(new Runnable() {
          @Override
          public void run() {
            textView.setText(json);
          }
        });
      }

      @Override
      public void onError(Throwable throwable) {
        Log.e(LOG_TAG, "Failed to get weather.", throwable);
      }
    });
  }

  @Override
  public void onStatusChanged(String provider, int status, Bundle extras) {

  }

  @Override
  public void onProviderEnabled(String provider) {

  }

  @Override
  public void onProviderDisabled(String provider) {

  }
}
