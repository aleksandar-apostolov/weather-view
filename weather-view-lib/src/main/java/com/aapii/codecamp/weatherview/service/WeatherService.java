package com.aapii.codecamp.weatherview.service;

import com.aapii.codecamp.weatherview.model.WeatherModel;
import com.aapii.codecamp.weatherview.service.rest.Rest;

/**
 * A service that gets weather info.
 */
public class WeatherService {
  private static final String LOG_TAG = "WeatherService";

  private static final String API_KEY = "3ac4c8e628d0cb6cedd2ded241cd73b1";
  private static final String API_KEY_SUFFIX = "&APPID=" + API_KEY;
  private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?";
  private static final String UNITS_SUFFIX = "&units=metric";

  public static WeatherModel getWeather(float lat, float lon) {
    return Rest.getRestTemplate().getForObject(buildUrl(lat, lon), WeatherModel.class);
  }

  private static String buildUrl(float lat, float lon) {
    return API_URL + "lat=" + lat + "&" + "lon=" + lon + UNITS_SUFFIX + API_KEY_SUFFIX;
  }
}
