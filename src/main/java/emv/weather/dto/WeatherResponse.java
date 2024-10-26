package emv.weather.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WeatherResponse {
  private int days;

  @JsonProperty("forecast")
  private List<WeatherEntryDTO> weatherInfo;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String weatherAlert;

  public int getDays() {
    return days;
  }

  public void setDays(int days) {
    this.days = days;
  }

  public List<WeatherEntryDTO> getWeatherInfo() {
    return weatherInfo;
  }

  public void setWeatherInfo(List<WeatherEntryDTO> weatherInfo) {
    this.weatherInfo = weatherInfo;
  }

  public String getWeatherAlert()
  {
    return weatherAlert;
  }

  public void setWeatherAlert(String weatherAlert)
  {
    this.weatherAlert = weatherAlert;
  }
}
