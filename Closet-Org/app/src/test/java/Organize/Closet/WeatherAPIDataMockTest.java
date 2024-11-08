package Organize.Closet;

import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class WeatherAPIDataMockTest {

    private WeatherAPIData weatherAPI;

    @BeforeEach
    public void setUp() {
        weatherAPI = new WeatherAPIData(70.0, false, 60, "summer");
    }

    @Test
    public void testGetLocationData() throws Exception {
        // Mock the API response for location data
        String mockJsonResponse = "{\"results\":[{\"latitude\":40.7128,\"longitude\":-74.0060}]}";
        HttpURLConnection mockConnection = mock(HttpURLConnection.class);

        InputStream inputStream = new ByteArrayInputStream(mockJsonResponse.getBytes());
        when(mockConnection.getInputStream()).thenReturn(inputStream);

        JSONObject locationData = WeatherAPIData.getLocationData("New York");

        assertNotNull(locationData);
        assertEquals(40.7128, locationData.get("latitude"));
        assertEquals(-74.0060, locationData.get("longitude"));
    }

    @Test
    public void testDisplayWeatherData() throws Exception {
        // Mock API response
        String mockJsonResponse = "{\"current\":{\"temperature_2m\":75,\"relative_humidity_2m\":45,\"wind_speed_10m\":10}}";
        HttpURLConnection mockConnection = mock(HttpURLConnection.class);

        InputStream inputStream = new ByteArrayInputStream(mockJsonResponse.getBytes());
        when(mockConnection.getInputStream()).thenReturn(inputStream);
        
        // Simulate displayWeatherData by setting latitude and longitude
        weatherAPI.displayWeatherData(40.7128, -74.0060);

        // Verify that expected data is retrieved
        assertEquals(75.0, weatherAPI.getTemperature());
        assertEquals(45, weatherAPI.getHumidity());
    }
}

