package com.aapii.codecamp.weatherview.service;

import com.aapii.codecamp.weatherview.model.WeatherModel;
import com.aapii.codecamp.weatherview.service.rest.Rest;

/**
 * A service that gets weather info.
 */
public class WeatherService {
  private static final String LOG_TAG = "WeatherService";

  private static final String API_KEY_SUFFIX = "&appid=";
  private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?";
  private static final String UNITS_SUFFIX = "&units=metric";

  private static String API = "";

  public static void setApyKey(String key) {
    API = key;
  }

  public static WeatherModel getWeather(double lat, double lon) {
    return Rest.getRestTemplate().getForObject(buildUrl(lat, lon), WeatherModel.class);
  }

  private static String buildUrl(double lat, double lon) {
    return API_URL + "lat=" + lat + "&" + "lon=" + lon + UNITS_SUFFIX + API_KEY_SUFFIX + API;
  }
}
