package emv.weather.service;

import emv.weather.client.WeatherClient;
import emv.weather.dto.WeatherResponse;
import emv.weather.enums.WeatherMessageAlert;
import emv.weather.model.WeatherEntry;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

  private final WeatherClient weatherClient;
  private final ModelMapper modelMapper;

  private static final int COLD_DAY_TEMPERATURE = -5;
  private static final int COLD_ANOMALY_TEMPERATURE = -30;
  private static final int HEAT_ANOMALY_TEMPERATURE = 35;

  public WeatherService(WeatherClient weatherClient, ModelMapper modelMapper) {
    this.weatherClient = weatherClient;
    this.modelMapper = modelMapper;
  }

  private WeatherMessageAlert getWeatherAlert(List<WeatherEntry> weatherInfo) {
    int sumTemp = 0;
    for (WeatherEntry weatherInfoEntry : weatherInfo) {
      int temperature = weatherInfoEntry.getTemperature();

      if (temperature < COLD_ANOMALY_TEMPERATURE) {
        return WeatherMessageAlert.MESSAGE_COLD_ANOMALY;
      }

      if (temperature > HEAT_ANOMALY_TEMPERATURE) {
        return WeatherMessageAlert.MESSAGE_HEAT_ANOMALY;
      }

      sumTemp += temperature;
    }

    if (sumTemp / weatherInfo.size() < COLD_DAY_TEMPERATURE) {
      return WeatherMessageAlert.MESSAGE_COLD_DAYS;
    }

    return WeatherMessageAlert.MESSAGE_NORMAL;
  }

  public WeatherResponse getWeatherInfo(int days) {
    WeatherResponse weatherResponse = weatherClient.getWeatherForecast(days);

    WeatherMessageAlert weatherAlert =
        getWeatherAlert(
            weatherResponse.getWeatherInfo().stream()
                .map(weatherEntryDTO -> modelMapper.map(weatherEntryDTO, WeatherEntry.class))
                .toList());

    if (!weatherAlert.equals(WeatherMessageAlert.MESSAGE_NORMAL)) {
      weatherResponse.setWeatherAlert(weatherAlert.toString());
    }

    weatherResponse.setDays(days);

    return weatherResponse;
  }
}
