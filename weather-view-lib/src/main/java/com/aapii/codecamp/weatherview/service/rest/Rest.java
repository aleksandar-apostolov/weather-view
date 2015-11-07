package com.aapii.codecamp.weatherview.service.rest;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Rest utilities.
 */
public class Rest {

  private static final RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

  // Configure the rest template.
  static {

    // interceptors
    List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
    restTemplate.setInterceptors(interceptors);

    // message converters
    List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
    messageConverters.add(new StringHttpMessageConverter());
    messageConverters.add(new FormHttpMessageConverter());
    messageConverters.add(new GsonHttpMessageConverter());
    messageConverters.add(new ResourceHttpMessageConverter());
    messageConverters.add(new ByteArrayHttpMessageConverter());
    restTemplate.setMessageConverters(messageConverters);
  }

  /**
   * Get the rest template.
   *
   * @return the rest template.
   */
  public static RestTemplate getRestTemplate() {
    return restTemplate;
  }
}
