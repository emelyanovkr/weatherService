package emv.weather.exception;

public record ExceptionResponse(String timestamp, String message, int statusCode) {}
