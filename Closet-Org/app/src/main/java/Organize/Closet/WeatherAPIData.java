package Organize.Closet;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class WeatherAPIData implements Weather {
  private String season;
  private double temperature;
  private String condition;
  private int humidity;

  public WeatherAPIData(double temperature, String condition, int humidty, String season) {
    this.temperature = temperature;
    this.condition = condition;
    this.humidity = humidty;
    this.season = season;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    try {
      String city;
      do {
        System.out.println("===================================================");
        System.out.print("Enter City (Say No to Quit): ");
        city = scanner.nextLine();

        if (city.equalsIgnoreCase("No")) break;

        JSONObject cityLocationData = getLocationData(city);
        if (cityLocationData == null) {
          System.out.println("Location data could not be retrieved.");
          continue;
        }
        double latitude = (double) cityLocationData.get("latitude");
        double longitude = (double) cityLocationData.get("longitude");

        displayWeatherData(latitude, longitude);
      } while (!city.equalsIgnoreCase("No"));

    } catch (Exception e) {
      e.printStackTrace();
    } finally {

      scanner.close();
    }
  }

  static JSONObject getLocationData(String city) {
    city = city.replaceAll(" ", "+");
    String urlString =
        "https://geocoding-api.open-meteo.com/v1/search?name="
            + city
            + "&count=1&language=en&format=json";

    try {
      HttpURLConnection apiConnection = fetchApiResponse(urlString);
      if (apiConnection.getResponseCode() != 200) {
        System.out.println("Error: Could not connect to API");
        return null;
      }

      String jsonResponse = readApiResponse(apiConnection);
      JSONParser parser = new JSONParser();
      JSONObject resultsJsonObj = (JSONObject) parser.parse(jsonResponse);

      JSONArray locationData = (JSONArray) resultsJsonObj.get("results");
      return (JSONObject) locationData.get(0);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  static void displayWeatherData(double latitude, double longitude) {
    try {
      String url =
          "https://api.open-meteo.com/v1/forecast?latitude="
              + latitude
              + "&longitude="
              + longitude
              + "&hourly=temperature_2m,relative_humidity_2m,precipitation,wind_speed_10m&temperature_unit=fahrenheit&wind_speed_unit=mph&precipitation_unit=inch&timeformat=unixtime";
      HttpURLConnection apiConnection = fetchApiResponse(url);

      if (apiConnection.getResponseCode() != 200) {
        System.out.println("Error: Could not connect to API");
        return;
      }

      String jsonResponse = readApiResponse(apiConnection);
      JSONParser parser = new JSONParser();
      JSONObject jsonObject = (JSONObject) parser.parse(jsonResponse);

      JSONObject hourlyData = (JSONObject) jsonObject.get("hourly");

      System.out.println("Latitude: " + latitude + ", Longitude: " + longitude);
      System.out.println("Current Temperature (°C): " + hourlyData.get("temperature_2m"));
      System.out.println("Humidity (%): " + hourlyData.get("relative_humidity_2m"));
      System.out.println("Precipitation: " + hourlyData.get("precipitation"));
      System.out.println("Wind Speed (m/s): " + hourlyData.get("wind_speed_10m"));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static String readApiResponse(HttpURLConnection apiConnection) {
    try (Scanner scanner = new Scanner(apiConnection.getInputStream())) {
      StringBuilder resultJson = new StringBuilder();
      while (scanner.hasNext()) {
        resultJson.append(scanner.nextLine());
      }
      return resultJson.toString();

    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static HttpURLConnection fetchApiResponse(String urlString) {
    try {
      URL url = new URL(urlString);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      return conn;

    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public double getTemperature() {
    return temperature;
  }

  @Override
  public String getCondition() {
    return condition;
  }

  @Override
  public int getHumidity() {
    return humidity;
  }

  @Override
  public String getSeason() {
    return season;
  }
}
