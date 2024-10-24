package emv.weather.enums;

public enum WeatherMessageAlert {
  MESSAGE_NORMAL("Everything is normal"),
  MESSAGE_COLD_DAYS("There are a really cold days coming."),
  MESSAGE_COLD_ANOMALY("Winter is coming..."),
  MESSAGE_HEAT_ANOMALY("It's gonna be too hot, be cool");

  private final String message;

  WeatherMessageAlert(String message)
  {
    this.message = message;
  }

  public String getMessage()
  {
    return message;
  }
}
