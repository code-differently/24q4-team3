package Organize.Closet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class OutfitTest {
    
    @Test 
    public void testDescribeOutfit() {
        //Arrange 
        Outfit outfit = new Outfit();
        outfit.addClothing(shirt);
        outfit.addClothing(pants);
        outfit.addClothing(accesory);


        //Act
        String description = outfit.describe();

        //Assert
        assertEquals();
    }

    @Test 
    public void testIsInCloset() {
        // Arrange
        Outfit outfit = new Outfit();
        Closet closet = new Closet();
        closet.addOutfit(outfit);

        // Act
        boolean exists = closet.contains(outfit);

        // Assert
        assertTrue(exists);
    }

    @Test 
    public void testGetSeasonalPurpose() {
      // Arrange
        Clothing coat = new Coat("Red", "L", "Winter");
        Clothing scarf = new Scarf("Black", "Winter");

        Outfit outfit = new Outfit();
        outfit.addClothing(coat);
        outfit.addClothing(scarf);

        // Act
        String season = outfit.getSeasonalPurpose();

        // Assert
        assertEquals("Winter", season);
    }

    @Test 
    public void testGetStyle() {
        // Arrange
        Clothing tshirt = new TShirt("Blue", "M", "Casual");
        Clothing jeans = new Jeans("Black", "L", "Casual");

        Outfit outfit = new Outfit();
        outfit.addClothing(tshirt);
        outfit.addClothing(jeans);

        // Act
        String style = outfit.getStyle();

        // Assert
        assertEquals("Casual", style);
    }
}
