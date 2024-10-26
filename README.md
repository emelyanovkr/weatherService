## Weather Service

### To simplify:
 + Used main git branch only (no another branch)
 + Used only temperature field for weather information, however: additional fields like day/night temperature,
   humidity, pressure, wind speed, sunrise, sunset, "feels like" might be used
 + Temperature format - only Celsius
 + In wireMock there are responses only for test cases: normal temperature, cold, cold anomaly, heat anomaly.
 + Straight url wiremock.forecast.api = /api/forecast?days= used for api with no builders
 + Used RESTTemplate instead of WebFlux

### Getting started
1. Clone repository
2. Create .env file and specify environment variables to use:
   - WIREMOCK_BASE_URL (for example: http://wiremock:8080, please specify wiremock as domain name)
   - WIREMOCK_FORECAST_API (for example: /api/forecast?days=)
3. If you would like to rebuild app, use gradle command to create bootJar with all dependencies:
   ```.\gradlew bootJar```  
   *for unix systems use backslash and please make gradlew executable: chmod 755 gradlew*
4. Use docker compose command to start both services:
   ```docker-compose up -d --build```
   - -d - to launch service in detached state
   - --build - to rebuild app using dockerfile
5. Once everything started, please import Postman Collection `WeatherService.json` 
   to communicate with service (there are specified requests for you to test).

If you would like to edit mappings of mock server, please refer to /wiremock/mappings folder.  
If you would like to edit responses, please refer to /wiremock/__files folder.  
For more info: https://wiremock.org/docs/stubbing/
