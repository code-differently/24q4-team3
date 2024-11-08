package Organize.Closet;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WeatherAPIDataTest {
    
    private WeatherAPIData weatherData;

    @BeforeEach
    public void setUp() {
        weatherData = new WeatherAPIData(72.0, false, 60, "spring");
    }

    @Test
    public void testGetTemperature() {
        assertEquals(72.0, weatherData.getTemperature());
    }

    @Test
    public void testGetCondition() {
        assertFalse(weatherData.getCondition());
    }

    @Test
    public void testGetHumidity() {
        assertEquals(60, weatherData.getHumidity());
    }

    @Test
    public void testGetSeason() {
        assertEquals("spring", weatherData.getSeason());
    }
}
