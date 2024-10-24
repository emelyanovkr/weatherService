package emv.weather.dto;

import java.time.LocalDate;

public class WeatherEntryDTO {

  private LocalDate date;
  private int temperature;

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public int getTemperature() {
    return temperature;
  }

  public void setTemperature(int temperature) {
    this.temperature = temperature;
  }
}
