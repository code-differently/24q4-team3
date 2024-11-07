package Organize.Closet;

public interface Weather {
    void displayWeatherData(double latitude, double longitude);
    double getTemperature();
    String getCondition();
    int getHumidity();
    String getSeason();
}
