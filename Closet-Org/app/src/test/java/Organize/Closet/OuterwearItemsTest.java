package Organize.Closet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class OuterwearItemsTest {

  @Test
  void testGetters() {
    // Arrange
    OuterwearItems jacket =
        new OuterwearItems("Red", "Jacket", "Winter Jacket", "Casual", "Winter", "High");

    // Act & Assert
    assertEquals("Red", jacket.getColor());
    assertEquals("Jacket", jacket.getStyle());
    assertEquals("Winter Jacket", jacket.getName());
    assertEquals("Casual", jacket.getOccasion());
    assertEquals("Winter", jacket.getSeasonanlPurpose());
    assertEquals("High", jacket.getInsulationLevel());
    assertTrue(jacket.isWaterproof());
  }

  @Test
  void testDescribeItem() {
    // Arrange
    OuterwearItems jacket =
        new OuterwearItems("Blue", "Coat", "Winter Coat", "Formal", "Winter", "Medium");

    // Act
    String description = jacket.describeItem();

    // Assert
    String expectedDescription =
        "Style: Coat, Name: Winter Coat, Color: Blue. "
            + "Occasion: Formal, Seasonal Purpose: Winter. "
            + "Insulation Level: Medium, Waterproof: Yes.";
    assertEquals(expectedDescription, description);
  }

  @Test
  void testWaterproof() {
    // Arrange
    OuterwearItems jacket =
        new OuterwearItems("Green", "Parka", "Parka Jacket", "Outdoor", "Winter", "High");

    // Act & Assert
    assertTrue(jacket.isWaterproof(), "The jacket should be waterproof.");
  }
}
