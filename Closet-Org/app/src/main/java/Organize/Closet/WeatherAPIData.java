package Organize.Closet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherAPIData implements Weather {
    public static void main(String[] args) {
        try {
            WeatherAPIData weatherAPI = new WeatherAPIData();
            Scanner scanner = new Scanner(System.in);
            String city;
            do {
                // Retrieve user input
                System.out.println("===================================================");
                System.out.print("Enter City (Say No to Quit): ");
                city = scanner.nextLine();

                if (city.equalsIgnoreCase("No")) break;

                // Get location data
                JSONObject cityLocationData = getLocationData(city);
                if (cityLocationData == null) {
                    System.out.println("Location data could not be retrieved.");
                    continue;
                }
                double latitude = (double) cityLocationData.get("latitude");
                double longitude = (double) cityLocationData.get("longitude");

                weatherAPI.displayWeatherData(latitude, longitude);
            } while (!city.equalsIgnoreCase("No"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static JSONObject getLocationData(String city) {
        city = city.replaceAll(" ", "+");
        String urlString = "https://geocoding-api.open-meteo.com/v1/search?name=" + city + "&count=1&language=en&format=json";

        try {
            // 1. Fetch the API response based on API Link
            HttpURLConnection apiConnection = fetchApiResponse(urlString);

            // check for response status
            if (apiConnection.getResponseCode() != 200) {
                System.out.println("Error: Could not connect to API");
                return null;
            }

            // 2. Read the response
            String jsonResponse = readApiResponse(apiConnection);

            // 3. Parse the string into a JSON Object
            JSONParser parser = new JSONParser();
            JSONObject resultsJsonObj = (JSONObject) parser.parse(jsonResponse);

            // 4. Retrieve Location Data
            JSONArray locationData = (JSONArray) resultsJsonObj.get("results");
            return (JSONObject) locationData.get(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void displayWeatherData(double latitude, double longitude) {
        try {
            // 1. Fetch the API response based on API Link
            String url = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude + 
                    "&hourly=temperature_2m,relative_humidity_2m,precipitation,wind_speed_10m&temperature_unit=fahrenheit&wind_speed_unit=mph&precipitation_unit=inch&timeformat=unixtime";
            HttpURLConnection apiConnection = fetchApiResponse(url);

            // check for response status
            if (apiConnection.getResponseCode() != 200) {
                System.out.println("Error: Could not connect to API");
                return;
            }

            // 2. Read the response
            String jsonResponse = readApiResponse(apiConnection);

            // 3. Parse the string into a JSON Object
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonResponse);
            JSONObject currentWeatherJson = (JSONObject) jsonObject.get("current");

            // 4. Extract and display the data
            String time = (String) currentWeatherJson.get("time");
            System.out.println("Current Time: " + time);

            double temperature = (double) currentWeatherJson.get("temperature_2m");
            System.out.println("Current Temperature (C): " + temperature);

            long relativeHumidity = (long) currentWeatherJson.get("relative_humidity_2m");
            System.out.println("Relative Humidity: " + relativeHumidity);

            double windSpeed = (double) currentWeatherJson.get("wind_speed_10m");
            System.out.println("Wind Speed: " + windSpeed);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String readApiResponse(HttpURLConnection apiConnection) {
        try (Scanner scanner = new Scanner(apiConnection.getInputStream())) {
            StringBuilder resultJson = new StringBuilder();

            // Loop through each line in the response and append it to the StringBuilder
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

    // Implement the Weather interface methods as needed
    @Override
    public double getTemperature() {
        // Implement temperature retrieval logic
        return 0;
    }

    @Override
    public String getCondition() {
        // Implement weather condition retrieval logic
        return "";
    }

    @Override
    public int getHumidity() {
        // Implement humidity retrieval logic
        return 0;
    }

    @Override
    public String getSeason() {
        // Implement season retrieval logic
        return "";
    }
}
