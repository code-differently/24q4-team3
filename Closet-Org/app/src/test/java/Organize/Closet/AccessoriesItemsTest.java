package Organize.Closet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccessoriesItemsTest {

  private AccessoriesItems accessory;

  @BeforeEach
  public void setUp() {
    accessory = new AccessoriesItems("Red", "Casual", "Scarf", "Casual", "Winter", "Scarf");
  }

  @Test
  public void testGetColor() {
    assertEquals("Red", accessory.getColor());
  }

  @Test
  public void testGetStyle() {
    assertEquals("Casual", accessory.getStyle());
  }

  @Test
  public void testGetName() {
    assertEquals("Scarf", accessory.getName());
  }

  @Test
  public void testGetOccasion() {
    assertEquals("Casual", accessory.getOccassion());
  }

  @Test
  public void testGetSeasonalPurpose() {
    assertEquals("Winter", accessory.seasonalPurpose());
  }

  @Test
  public void testGetAccessoryType() {
    assertEquals("Scarf", accessory.getAccessoryType());
  }
}
