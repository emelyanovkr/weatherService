package emv.weather.client;

import emv.weather.dto.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherClient {

  @Value("${wiremock.forecast.api}")
  private String forecastAPIUrl;

  @Value("${wiremock.url}")
  private String wiremockUrl;

  private final RestTemplate restTemplate;

  public WeatherClient(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public WeatherResponse getWeatherForecast(int days) {
    String URL = wiremockUrl + forecastAPIUrl + days;
    return restTemplate.getForObject(URL, WeatherResponse.class);
  }
}
