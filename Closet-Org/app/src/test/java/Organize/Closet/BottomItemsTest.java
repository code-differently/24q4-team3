package Organize.Closet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BottomItemsTest {
  BottomItems jeans;

  @BeforeEach
  public void testFirst() {
    jeans =
        new BottomItems(
            "jean", "black", "Denim shorts", "casual", "summer", "mid-rise", true, "shorts");
  }

  @Test
  public void testHasPockets() {
    assertTrue(jeans.hasPockets());
  }

  @Test
  public void testSeasonalPurpose() {
    assertEquals(
        "summer",
        jeans.seasonalPurpose(),
        "getSeasinalPurpose should return the Season of the clothing item.");
  }

  @Test
  public void testGetColor() {
    assertEquals(
        "black", jeans.getColor(), "getColor() should return the color of the clothing item.");
  }

  @Test
  public void testGetStyle() {
    assertEquals(
        "Denim shorts",
        jeans.getStyle(),
        "getStyle() should return the style of the clothing item.");
  }

  @Test
  public void testGetName() {
    assertEquals("jean", jeans.getName(), "getName() should return the name of the clothing item.");
  }

  @Test
  public void testGetOccasion() {
    assertEquals(
        "casual",
        jeans.getOccasion(),
        "getOccasion() should return the occasion of the clothing item.");
  }

  @Test
  public void testGetWaistLine() {
    assertEquals(
        "mid-rise",
        jeans.getWaistLine(),
        "getWaistLine() should return the waist line of the clothing item.");
  }

  @Test
  public void testBottomLength() {
    assertEquals(
        "shorts",
        jeans.bottomLength(),
        "bottomLength() should return the bottoms lengths of the clothing item.");
  }

  @Test
  public void testDescribeItem() {
    // Expected description format
    String expectedDescription =
        "Style: Denim shorts, Name: jean, Color: black. Occasion: casual, Seasonal: summer. ";

    // Assert that describeItem() returns the expected description
    assertEquals(
        expectedDescription,
        jeans.describeItem(),
        "The description format should match the expected output.");
  }
}
