package com.aapii.codecamp.weatherview.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * WeatherModel model.
 */
public class WeatherModel {

  private Coordinates coord;
  private List<Weather> weather;
  private String base;
  private Main main;
  private Wind wind;
  private Clouds clouds;
  private Rain rain;
  private Snow snow;
  private long dt;
  private System system;
  private String name;
  private int cod;

  /**
   * Constructor.
   */
  public WeatherModel() {
    // Empty
  }

  /**
   * Constructor.
   *
   * @param coord coordinates
   * @param weather weather info
   * @param base base info
   * @param main main info
   * @param wind wind info
   * @param clouds clouds info
   * @param rain rain info
   * @param snow snow info
   * @param dt date time  info
   * @param system the system info
   * @param name city name
   * @param cod internal
   */
  public WeatherModel(Coordinates coord, List<Weather> weather, String base, Main main, Wind wind, Clouds clouds,
      Rain rain, Snow snow, long dt, System system, String name, int cod) {
    this.coord = coord;
    this.weather = weather;
    this.base = base;
    this.main = main;
    this.wind = wind;
    this.clouds = clouds;
    this.rain = rain;
    this.snow = snow;
    this.dt = dt;
    this.system = system;
    this.name = name;
    this.cod = cod;
  }

  public Coordinates getCoord() {
    return coord;
  }

  public void setCoord(Coordinates coord) {
    this.coord = coord;
  }

  public List<Weather> getWeather() {
    return weather;
  }

  public void setWeather(List<Weather> weather) {
    this.weather = weather;
  }

  public String getBase() {
    return base;
  }

  public void setBase(String base) {
    this.base = base;
  }

  public Main getMain() {
    return main;
  }

  public void setMain(Main main) {
    this.main = main;
  }

  public Wind getWind() {
    return wind;
  }

  public void setWind(Wind wind) {
    this.wind = wind;
  }

  public Clouds getClouds() {
    return clouds;
  }

  public void setClouds(Clouds clouds) {
    this.clouds = clouds;
  }

  public Rain getRain() {
    return rain;
  }

  public void setRain(Rain rain) {
    this.rain = rain;
  }

  public Snow getSnow() {
    return snow;
  }

  public void setSnow(Snow snow) {
    this.snow = snow;
  }

  public long getDt() {
    return dt;
  }

  public void setDt(long dt) {
    this.dt = dt;
  }

  public System getSystem() {
    return system;
  }

  public void setSystem(System system) {
    this.system = system;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCod() {
    return cod;
  }

  public void setCod(int cod) {
    this.cod = cod;
  }

  public class System {
    private int type;
    private int id;
    private float message;
    private String country;
    private long sunrise;
    private long sunset;

    /**
     * Constructor.
     */
    public System() {
      // Empty
    }

    /**
     * Constructor.
     *
     * @param type system type.
     * @param id system id
     * @param message system message
     * @param country the country code
     * @param sunrise sunrise time
     * @param sunset sunset time.
     */
    public System(int type, int id, float message, String country, long sunrise, long sunset) {
      this.type = type;
      this.id = id;
      this.message = message;
      this.country = country;
      this.sunrise = sunrise;
      this.sunset = sunset;
    }
  }

  /**
   * Snow info.
   */
  public class Snow extends Rain {
    /**
     * Constructor.
     */
    public Snow() {
      // Empty
    }

    /**
     * {@inheritDoc}.
     */
    public Snow(int three) {
      super(three);
    }
  }

  /**
   * Rain info.
   */
  public class Rain {
    @SerializedName("3h")
    private float three;

    /**
     * Constructor.
     */
    public Rain() {
      // Empty
    }

    /**
     * Create rain info.
     *
     * @param three volume in the last three hours.
     */
    public Rain(float three) {
      this.three = three;
    }

    public float getThree() {
      return three;
    }

    public void setThree(float three) {
      this.three = three;
    }
  }

  /**
   * Clouds info.
   */
  public class Clouds {
    private int all;

    /**
     * Constructor.
     */
    public Clouds() {
      // Empty
    }

    /**
     * Cloudiness.
     *
     * @param all percents of clouds.
     */
    public Clouds(int all) {
      this.all = all;
    }

    public int getAll() {
      return all;
    }

    public void setAll(int all) {
      this.all = all;
    }
  }

  /**
   * Wind info.
   */
  public class Wind {
    private float speed;
    private float deg;

    /**
     * Constructor.
     */
    public Wind() {
      // Empty
    }

    /**
     * Create wind.
     *
     * @param speed the wind speed.
     * @param deg the angle in degrees
     */
    public Wind(float speed, float deg) {
      this.speed = speed;
      this.deg = deg;
    }

    public float getSpeed() {
      return speed;
    }

    public void setSpeed(float speed) {
      this.speed = speed;
    }

    public float getDeg() {
      return deg;
    }

    public void setDeg(float deg) {
      this.deg = deg;
    }
  }

  /**
   * Main weather info.
   */
  public class Main {
    private float temp;
    private float pressure;
    private int humidity;
    private float temp_min;
    private float temp_max;
    private float sea_level;
    private float grnd_level;


    /**
     * Constructor.
     */
    public Main() {
      // Empty
    }

    /**
     * Constructor.
     *
     * @param temp the current
     * @param pressure the pressure.
     * @param humidity the humidity
     * @param temp_min the min temperature
     * @param temp_max the max temperature.
     * @param sea_level pressure at sea level.
     * @param grnd_level pressure at ground level.
     */
    public Main(float temp, float pressure, int humidity, float temp_min, float temp_max, float sea_level,
        float grnd_level) {
      this.temp = temp;
      this.pressure = pressure;
      this.humidity = humidity;
      this.temp_min = temp_min;
      this.temp_max = temp_max;
      this.sea_level = sea_level;
      this.grnd_level = grnd_level;
    }

    public float getTemp() {
      return temp;
    }

    public void setTemp(float temp) {
      this.temp = temp;
    }

    public float getPressure() {
      return pressure;
    }

    public void setPressure(float pressure) {
      this.pressure = pressure;
    }

    public int getHumidity() {
      return humidity;
    }

    public void setHumidity(int humidity) {
      this.humidity = humidity;
    }

    public float getTemp_min() {
      return temp_min;
    }

    public void setTemp_min(float temp_min) {
      this.temp_min = temp_min;
    }

    public float getTemp_max() {
      return temp_max;
    }

    public void setTemp_max(float temp_max) {
      this.temp_max = temp_max;
    }

    public float getSea_level() {
      return sea_level;
    }

    public void setSea_level(float sea_level) {
      this.sea_level = sea_level;
    }

    public float getGrnd_level() {
      return grnd_level;
    }

    public void setGrnd_level(float grnd_level) {
      this.grnd_level = grnd_level;
    }
  }


  /**
   * Weather info.
   */
  public class Weather {
    private int id;
    private String main;
    private String description;
    private String icon;

    /**
     * Constructor.
     */
    public Weather() {
      // Empty
    }

    /**
     * Constructor.
     *
     * @param id the id.
     * @param main the main weather e.g. 'Clouds'
     * @param description description e.g. 'broken clouds'.
     * @param icon the icon.
     */
    public Weather(int id, String main, String description, String icon) {
      this.id = id;
      this.main = main;
      this.description = description;
      this.icon = icon;
    }

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getMain() {
      return main;
    }

    public void setMain(String main) {
      this.main = main;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public String getIcon() {
      return icon;
    }

    public void setIcon(String icon) {
      this.icon = icon;
    }
  }


  /**
   * Coordinates model.
   */
  public class Coordinates {
    private float lon;
    private float lat;

    /**
     * Constructor.
     */
    public Coordinates() {
      // Empty
    }

    /**
     * Constructor.
     *
     * @param lon longitude
     * @param lat latitude.
     */
    public Coordinates(float lon, float lat) {
      this.lon = lon;
      this.lat = lat;
    }

    public float getLon() {
      return lon;
    }

    public void setLon(float lon) {
      this.lon = lon;
    }

    public float getLat() {
      return lat;
    }

    public void setLat(float lat) {
      this.lat = lat;
    }
  }
}
