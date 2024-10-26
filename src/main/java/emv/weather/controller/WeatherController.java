package emv.weather.controller;

import emv.weather.service.WeatherService;

import java.text.MessageFormat;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
public class WeatherController {

  private final WeatherService weatherService;

  private static final String ERROR_DAY_VALUE_MESSAGE =
      "Days must be more {0} or less than {1}: {2}.";
  private static final String ERROR_NO_DAY_SPECIFIED =
      "Please specify days parameter to get forecast.";
  private static final int MIN_DAYS_TO_COUNT_TEMP = 1;
  private static final int MAX_DAYS_TO_COUNT_TEMP = 300;

  public WeatherController(WeatherService weatherService) {
    this.weatherService = weatherService;
  }

  @GetMapping("/info")
  public ResponseEntity<?> getForecast(@RequestParam(required = false) Optional<Integer> days) {
    if (days.isPresent()) {
      int requiredDays = days.get();
      if (requiredDays < MIN_DAYS_TO_COUNT_TEMP || requiredDays > MAX_DAYS_TO_COUNT_TEMP) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(
                MessageFormat.format(
                    ERROR_DAY_VALUE_MESSAGE,
                    MIN_DAYS_TO_COUNT_TEMP,
                    MAX_DAYS_TO_COUNT_TEMP,
                    requiredDays));
      }
      return ResponseEntity.ok(weatherService.getWeatherInfo(requiredDays));
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_NO_DAY_SPECIFIED);
    }
  }
}
