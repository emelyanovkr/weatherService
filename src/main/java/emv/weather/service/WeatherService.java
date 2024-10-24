package emv.weather.service;

import emv.weather.enums.WeatherMessageAlert;
import emv.weather.model.WeatherEntry;
import emv.weather.repository.WeatherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class WeatherService {
  private final WeatherRepository weatherRepository;
  private static final int COLD_DAY_TEMPERATURE = -5;
  private static final int COLD_ANOMALY_TEMPERATURE = -30;
  private static final int HEAT_ANOMALY_TEMPERATURE = 35;

  public WeatherService(WeatherRepository weatherRepository) {
    this.weatherRepository = weatherRepository;
  }

  public WeatherMessageAlert getWeatherAlert(List<WeatherEntry> weatherInfo) {
    for (WeatherEntry weatherInfoEntry : weatherInfo) {
      int temperature = weatherInfoEntry.getTemperature();

      if (temperature < COLD_ANOMALY_TEMPERATURE) {
        return WeatherMessageAlert.MESSAGE_COLD_ANOMALY;
      }

      if (temperature < COLD_DAY_TEMPERATURE) {
        return WeatherMessageAlert.MESSAGE_COLD_DAYS;
      }

      if (temperature > HEAT_ANOMALY_TEMPERATURE) {
        return WeatherMessageAlert.MESSAGE_HEAT_ANOMALY;
      }
    }
    return WeatherMessageAlert.MESSAGE_NORMAL;
  }

  @Transactional(readOnly = true)
  public List<WeatherEntry> getWeatherInfo(int days) {
    return weatherRepository.findByRecordDateBetween(
        LocalDate.now(), LocalDate.now().plusDays(days - 1));
  }
}
