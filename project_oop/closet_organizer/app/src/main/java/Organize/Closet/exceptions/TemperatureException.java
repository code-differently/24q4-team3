package Organize.Closet.exceptions;

public class TemperatureException extends Exception {
  private final double temperature;

  public TemperatureException(double temperature) {
    super();
    this.temperature = temperature;
  }

  @Override
  public String getMessage() {
    if (temperature < 60) {
      return "Aren't you cold?? Get a jacket!!";
    } else {
      return "It's hot! Maybe try something less covering to survive the heat.";
    }
  }
}
