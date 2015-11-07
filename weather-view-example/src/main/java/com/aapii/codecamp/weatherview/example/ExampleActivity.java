package com.aapii.codecamp.weatherview.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.aapii.codecamp.weatherview.concurrent.BackgroundExecutor;
import com.aapii.codecamp.weatherview.model.WeatherModel;
import com.aapii.codecamp.weatherview.service.WeatherService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.netcetera.weather.R;
import com.stanfy.enroscar.goro.FutureObserver;

import java.util.concurrent.Callable;

public class ExampleActivity extends AppCompatActivity {

  private static final String LOG_TAG = "ExampleActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_example);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.example_menu, menu);
    BackgroundExecutor.dispatchAndReturn(new Callable<WeatherModel>() {
      @Override
      public WeatherModel call() throws Exception {
        return WeatherService.getWeather(53.5653f, 22.6333f);
      }
    }, new FutureObserver<WeatherModel>() {
      @Override
      public void onSuccess(WeatherModel weatherModel) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(weatherModel);
        Log.d(LOG_TAG, json);
      }

      @Override
      public void onError(Throwable throwable) {
        Log.e(LOG_TAG, "Failed to get weather.", throwable);
      }
    });
    return true;
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
}
