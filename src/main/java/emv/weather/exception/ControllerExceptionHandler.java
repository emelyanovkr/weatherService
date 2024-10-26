package emv.weather.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ControllerExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);
  private static final String ERROR_NOT_FOUND_MESSAGE = "No forecast for this days found.";
  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

  @ExceptionHandler(HttpClientErrorException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ExceptionResponse handleRestClientException(final HttpClientErrorException e) {
    LOGGER.error(e.getMessage(), e);

    return new ExceptionResponse(
        LocalDateTime.now().format(formatter),
        ERROR_NOT_FOUND_MESSAGE,
        HttpStatus.NOT_FOUND.value());
  }
}
