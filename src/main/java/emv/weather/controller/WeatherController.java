package emv.weather.controller;

import emv.weather.dto.WeatherEntryDTO;
import emv.weather.dto.WeatherResponse;
import emv.weather.enums.WeatherMessageAlert;
import emv.weather.model.WeatherEntry;
import emv.weather.service.WeatherService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/weather")
public class WeatherController {

  private final WeatherService weatherService;
  private final ModelMapper modelMapper;
  private static final String ERROR_DAY_VALUE_MESSAGE = "Days must be more 1 or less than 30";
  private static final String ERROR_NO_DAY_SPECIFIED = "Please specify date count to get forecast";
  private static final int MIN_DAYS_TO_COUNT_TEMP = 1;
  private static final int MAX_DAYS_TO_COUNT_TEMP = 30;

  public WeatherController(WeatherService weatherService, ModelMapper modelMapper) {
    this.weatherService = weatherService;
    this.modelMapper = modelMapper;
  }

  @GetMapping("/info")
  public ResponseEntity<?> getForecast(@RequestParam(required = false) Optional<Integer> days) {
    if (days.isPresent()) {
      int requiredDays = days.get();
      if (requiredDays < MIN_DAYS_TO_COUNT_TEMP || requiredDays > MAX_DAYS_TO_COUNT_TEMP) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ERROR_DAY_VALUE_MESSAGE + ": " + requiredDays);
      }
      List<WeatherEntry> acquiredWeatherInfo = weatherService.getWeatherInfo(requiredDays);
      WeatherMessageAlert weatherAlert = weatherService.getWeatherAlert(acquiredWeatherInfo);
      WeatherResponse weatherResponse = new WeatherResponse();
      weatherResponse.setWeatherInfo(
          acquiredWeatherInfo.stream()
              .map(weatherEntry -> modelMapper.map(weatherEntry, WeatherEntryDTO.class))
              .toList());

      if (!weatherAlert.equals(WeatherMessageAlert.MESSAGE_NORMAL)) {
        weatherResponse.setWeatherAlert(weatherAlert.getMessage());
      }
      weatherResponse.setDays(requiredDays);

      return ResponseEntity.ok(weatherResponse);
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_NO_DAY_SPECIFIED);
    }
  }
}
