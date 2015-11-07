package com.aapii.codecamp.weatherview.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.aapii.codecamp.weatherview.R;
import com.aapii.codecamp.weatherview.concurrent.BackgroundExecutor;
import com.aapii.codecamp.weatherview.model.WeatherModel;
import com.aapii.codecamp.weatherview.service.LocationService;
import com.aapii.codecamp.weatherview.service.WeatherService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stanfy.enroscar.goro.FutureObserver;

import java.io.IOException;
import java.util.concurrent.Callable;

import pl.droidsonroids.gif.GifDrawable;

/**
 * A weather layout.
 */
public class WeatherFrameLayout extends FrameLayout implements LocationListener {

  private static final String LOG_TAG = "WeatherFrameLayout";
  private boolean isOverlayAdded;
  private GifDrawable drawable;
  private View overlayView;

  public WeatherFrameLayout(Context context) {
    super(context);
    isOverlayAdded = false;
  }

  public WeatherFrameLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    isOverlayAdded = false;
  }

  public WeatherFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    isOverlayAdded = false;
    LocationService.subscribe(getContext(), this);
  }

  @Override
  protected void dispatchDraw(Canvas canvas) {
    super.dispatchDraw(canvas);
    if (!isOverlayAdded) {
      isOverlayAdded = true;
      overlayView = new View(getContext());
      overlayView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
          ViewGroup.LayoutParams.MATCH_PARENT));
      try {
        drawable = new GifDrawable(getContext().getResources(), R.raw.giphy);
        overlayView.setBackgroundDrawable(drawable);
        this.addView(overlayView);
      } catch (IOException e) {
        Log.e(LOG_TAG, "Failed to load gif");
      }
    }
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
      public void onSuccess(final WeatherModel weatherModel) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final String json = gson.toJson(weatherModel);
        Log.d(LOG_TAG, json);

        post(new Runnable() {
          @Override
          public void run() {
            try {
              drawable = new GifDrawable(getContext().getResources(), R.raw.rain);
              overlayView.setBackgroundDrawable(drawable);
            } catch (IOException e) {
              Log.e(LOG_TAG, "Failed to load gif");
            }
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
