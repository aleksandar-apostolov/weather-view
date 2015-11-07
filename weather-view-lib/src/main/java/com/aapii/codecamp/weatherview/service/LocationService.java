package com.aapii.codecamp.weatherview.service;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.aapii.codecamp.weatherview.concurrent.BackgroundExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static android.support.v4.content.PermissionChecker.checkSelfPermission;

/**
 * Location service.
 */
public class LocationService {

  private static final String LOG_TAG = "LocationService";

  public static final int LOCATION_CHANGED = 0;
  public static final int STATUS_CHANGED = 1;
  public static final int PROVIDER_ENABLED = 2;
  public static final int PROVIDER_DISABLED = 3;

  private static List<LocationListener> subscribers = new ArrayList<>();
  private static LocationManager locationManager;
  private static LocationListener locationListener = new LocationListener() {
    @Override
    public void onLocationChanged(Location location) {
      bulkNotify(LOCATION_CHANGED, subscribers, location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
      bulkNotify(STATUS_CHANGED, subscribers, provider, status, extras);
    }

    @Override
    public void onProviderEnabled(String provider) {
      bulkNotify(PROVIDER_ENABLED, subscribers, provider);
    }

    @Override
    public void onProviderDisabled(String provider) {
      bulkNotify(PROVIDER_DISABLED, subscribers, provider);
    }
  };

  private static void bulkNotify(final int locationChanged, final List<LocationListener> subscribers,
      final Location location) {
    if (subscribers != null && subscribers.size() > 0) {
      BackgroundExecutor.dispatch(new Callable<Void>() {
        @Override
        public Void call() throws Exception {
          for (LocationListener subscriber : subscribers) {
            subscriber.onLocationChanged(location);
          }

          return null;
        }
      });
    }
  }

  private static void bulkNotify(int statusChanged, final List<LocationListener> subscribers, final String provider,
      final int status, final Bundle extras) {
    if (subscribers != null && subscribers.size() > 0) {
      BackgroundExecutor.dispatch(new Callable<Void>() {
        @Override
        public Void call() throws Exception {
          for (LocationListener subscriber : subscribers) {
            subscriber.onStatusChanged(provider, status, extras);
          }

          return null;
        }
      });
    }
  }

  private static void bulkNotify(final int providerDisabled, final List<LocationListener> subscribers,
      final String provider) {
    if (subscribers != null && subscribers.size() > 0) {
      BackgroundExecutor.dispatch(new Callable<Void>() {
        @Override
        public Void call() throws Exception {
          for (LocationListener subscriber : subscribers) {
            if (PROVIDER_DISABLED == providerDisabled) {
              subscriber.onProviderDisabled(provider);
            } else if (PROVIDER_ENABLED == providerDisabled) {
              subscriber.onProviderEnabled(provider);
            }
          }

          return null;
        }
      });
    }
  }

  public static void setup(Context context) {
    locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

    if (checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        && checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
        == PackageManager.PERMISSION_GRANTED) {
      locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
    }
  }

  public static void subscribe(Context context, LocationListener listener) {
    Log.d(LOG_TAG, "Subscribed new listener");
    subscribers.add(listener);
    if (checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        && checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
        == PackageManager.PERMISSION_GRANTED) {
      Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
      listener.onLocationChanged(location);
    }

  }

  public static void unsubscribe(LocationListener listener) {
    Log.d(LOG_TAG, "Unsubscribe a listener,");
    subscribers.remove(listener);
  }
}
