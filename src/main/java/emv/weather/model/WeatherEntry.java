package emv.weather.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "temperatures")
public class WeatherEntry {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "record_date", nullable = false)
  private LocalDate recordDate;

  @Column(nullable = false)
  private Integer temperature;

  public WeatherEntry() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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
