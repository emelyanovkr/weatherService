package emv.weather.model;

import java.time.LocalDate;

public class WeatherEntry {
  
  private LocalDate recordDate;

  private Integer temperature;

  public WeatherEntry() {}
  
  public LocalDate getRecordDate() {
    return recordDate;
  }

  public void setRecordDate(LocalDate recordDate) {
    this.recordDate = recordDate;
  }

  public Integer getTemperature() {
    return temperature;
  }

  public void setTemperature(Integer temperature) {
    this.temperature = temperature;
  }
}
