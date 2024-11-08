package Organize.Closet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.ArrayList;

class OutfitSuggesterTest {

    private Closet closet;
    private Weather weather;
    private OutfitSuggester suggester;

    @BeforeEach
    void setUp() {
        // Mock Closet and Weather
        closet = mock(Closet.class);
        weather = mock(Weather.class);
        suggester = new OutfitSuggester(closet, weather);
    }

    @Test
    void testSuggestOutfitForSunnyWeather() {
        // Set up weather conditions
        when(weather.getSeason()).thenReturn("Summer");
        when(weather.getCondition()).thenReturn("Sunny");
        when(weather.getTemperature()).thenReturn(85.0);

        // Define sample clothing items in closet
        ArticleOfClothing tShirt = createMockClothing("T-shirt", "Blue", "Summer", "V-Neck", "Everyday");
        ArticleOfClothing jacket = createMockClothing("Jacket", "Black", "Winter", "Heavy", "Outdoor");
        
        when(closet.getArticleOfClothingBySeasonalPurpose("Summer")).thenReturn(List.of(tShirt, jacket));

        List<ArticleOfClothing> outfit = suggester.suggestOutfit();

        // Assert that only lightweight, summer-appropriate clothing is selected
        assertTrue(outfit.contains(tShirt));
        assertFalse(outfit.contains(jacket));
    }

    @Test
    void testSuggestOutfitForRainyWeather() {
        // Set up weather conditions
        when(weather.getSeason()).thenReturn("Spring");
        when(weather.getCondition()).thenReturn("Rainy");
        when(weather.getTemperature()).thenReturn(60.0);

        // Define sample clothing items in closet
        ArticleOfClothing rainJacket = createMockClothing("Rain Jacket", "Black", "Spring", "Waterproof", "Outdoor");
        ArticleOfClothing sunglasses = createMockClothing("Sunglasses", "Black", "Summer", "Wire Frame", "Outdoor");

        when(closet.getArticleOfClothingBySeasonalPurpose("Spring")).thenReturn(List.of(rainJacket, sunglasses));

        List<ArticleOfClothing> outfit = suggester.suggestOutfit();

        // Assert that rain-appropriate clothing is selected and sunglasses are excluded
        assertTrue(outfit.contains(rainJacket));
        assertFalse(outfit.contains(sunglasses));
    }

    @Test
    void testSuggestOutfitForSnowyWeather() {
        // Set up weather conditions
        when(weather.getSeason()).thenReturn("Winter");
        when(weather.getCondition()).thenReturn("Snowy");
        when(weather.getTemperature()).thenReturn(30.0);

        // Define sample clothing items in closet
        ArticleOfClothing winterCoat = createMockClothing("Winter Coat", "Blue", "Winter", "Warm", "Outdoor");
        ArticleOfClothing tShirt = createMockClothing("T-shirt", "Red", "Summer", "Casual", "Indoor");

        when(closet.getArticleOfClothingBySeasonalPurpose("Winter")).thenReturn(List.of(winterCoat, tShirt));

        List<ArticleOfClothing> outfit = suggester.suggestOutfit();

        
        assertTrue(outfit.contains(winterCoat));
        assertFalse(outfit.contains(tShirt));
    }

   
    private ArticleOfClothing createMockClothing(String name, String color, String season, String style, String occasion) {
        ArticleOfClothing clothing = mock(ArticleOfClothing.class);
        when(clothing.getName()).thenReturn(name);
        when(clothing.getColor()).thenReturn(color);
        when(clothing.getSeasonanlPurpose()).thenReturn(season);
        when(clothing.getStyle()).thenReturn(style);
        when(clothing.getOccasion()).thenReturn(occasion);
        return clothing;
    }
}