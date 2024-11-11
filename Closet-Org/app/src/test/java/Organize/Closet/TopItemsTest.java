package Organize.Closet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TopItemsTest {
  TopItems shirt;

  @BeforeEach
  public void testFirst() {
    shirt =
        new TopItems(
            "Blouse",
            "Pink",
            "Silk",
            "Professional",
            "Fall",
            false,
            "Long",
            "V-Neck with collar",
            "Long sleeve",
            true);
  }

  @Test
  public void testSeasonalPurpose() {
    assertEquals(
        "Fall",
        shirt.getSeasonalPurpose(),
        "getSeasonalPurpose should return the Season of the clothing item.");
  }

  @Test
  public void testGetColor() {
    assertEquals(
        "Pink", shirt.getColor(), "getColor() should return the color of the clothing item.");
  }

  @Test
  public void testGetStyle() {
    assertEquals(
        "Silk", shirt.getStyle(), "getStyle() should return the style of the clothing item.");
  }

  @Test
  public void testGetName() {
    assertEquals(
        "Blouse", shirt.getName(), "getName() should return the name of the clothing item.");
  }

  @Test
  public void testGetOccasion() {
    assertEquals(
        "Professional",
        shirt.getOccasion(),
        "getOccasion() should return the occasion of the clothing item.");
  }

  @Test
  public void testDescribeItem() {
    // Expected description format
    String expectedDescription =
        "Style: Silk, Name: Blouse, Color: Pink. Occasion: Professional, Seasonal: Fall. ";

    // Assert that describeItem() returns the expected description
    assertEquals(
        expectedDescription,
        shirt.describeItem(),
        "The description format should match the expected output.");
  }

  @Test
  public void testHasPocket() {
    assertFalse(shirt.hasPocket());
  }

  @Test
  public void testShirtLength() {
    assertEquals(
        "Long",
        shirt.getShirtLength(),
        "getShirtLength should return the Shirt length of the clothing item.");
  }

  @Test
  public void testNeckLine() {
    assertEquals(
        "V-Neck with collar",
        shirt.getNeckLine(),
        "neckLine should return the Neck Line of the clothing item.");
  }

  @Test
  public void testGetSleeveLength() {
    assertEquals(
        "Long sleeve",
        shirt.getSleeveLength(),
        "getSleeveLength should return the sleeve length of the clothing item.");
  }

  @Test
  public void testHasStraps() {
    assertEquals(
        true, shirt.hasStraps(), "hasStraps should return if the shirt has straps or not.");
  }
}
