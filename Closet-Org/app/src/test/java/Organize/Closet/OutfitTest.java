package Organize.Closet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import Organize.Closet.exceptions.TemperatureException;
import org.junit.jupiter.api.Test;

public class OutfitTest {

  @Test
  public void testColdTemperature() {
    try {
      BottomItems pants = new BottomItems("Jeans", null, null, null, null, null, false, null);
      TopItems shirt =
          new TopItems("T-Shirt", null, null, null, null, false, null, null, null, false);
      Outfit myOutfit = new Outfit(pants, shirt);

      double coldTemperature = 50;
      myOutfit.checkTemperature(coldTemperature);

      fail("TemperatureException should have been thrown for cold weather");
    } catch (TemperatureException e) {

      assertEquals("Aren't you cold?? Get a jacket!!", e.getMessage());
    }
  }

  @Test
  public void testOutfitDescriptionWithAccessoriesAndOuterwear() {

    BottomItems pants = new BottomItems("Jeans", null, null, null, null, null, false, null);
    TopItems shirt =
        new TopItems("T-Shirt", null, null, null, null, false, null, null, null, false);
    AccessoriesItems hat =
        new AccessoriesItems("Cap", "Casual", "Baseball Cap", "Casual", "Spring", "Headwear");
    OuterwearItems jacket =
        new OuterwearItems("Leather", "Jacket", "Leather Jacket", "Casual", "Fall", "Medium");

    Outfit myOutfit = new Outfit(pants, shirt);
    myOutfit.setAccessories(hat);
    myOutfit.setOuterwear(jacket);

    String outfitDescription = myOutfit.describeOutfit();

    assertTrue(outfitDescription.contains("It is accessorized with a Baseball Cap."));
    assertTrue(outfitDescription.contains("For outerwear, there's a Leather Jacket."));
  }

  @Test
  public void testGetSetPants() {
    BottomItems pants = new BottomItems("Jeans", null, null, null, null, null, false, null);
    TopItems shirt =
        new TopItems("T-Shirt", null, null, null, null, false, null, null, null, false);
    Outfit myOutfit = new Outfit(pants, shirt);

    BottomItems newPants = new BottomItems("Chinos", null, null, null, null, null, false, null);
    myOutfit.setPants(newPants);

    assertEquals("Chinos", myOutfit.getPants().getName());
  }

  @Test
  public void testGetSetShirt() {
    BottomItems pants = new BottomItems("Jeans", null, null, null, null, null, false, null);
    TopItems shirt =
        new TopItems("T-Shirt", null, null, null, null, false, null, null, null, false);
    Outfit myOutfit = new Outfit(pants, shirt);

    TopItems newShirt =
        new TopItems("Polo", null, null, null, null, false, null, null, null, false);
    myOutfit.setTop(newShirt);

    assertEquals("Polo", myOutfit.geShirt().getName());
  }

  @Test
  public void testGetSetFullBodyItem() {
    Fullbody fullBody =
        new FullbodyItem("Jumpsuit", null, null, null, null, null, null, null, false, false);
    Outfit myOutfit = new Outfit(fullBody);

    Fullbody newFullBody =
        new FullbodyItem("Overalls", null, null, null, null, null, null, null, false, false);
    myOutfit.setFullBody((FullbodyItem) newFullBody);

    assertEquals("Overalls", myOutfit.getFullBody().getName());
  }

  @Test
  public void testDescribeOutfitWithoutAccessoriesAndOuterwear() {
    BottomItems pants = new BottomItems("Jeans", null, null, null, null, null, false, null);
    TopItems shirt =
        new TopItems("T-Shirt", null, null, null, null, false, null, null, null, false);
    Outfit myOutfit = new Outfit(pants, shirt);

    String description = myOutfit.describeOutfit();

    assertTrue(description.contains("The outfit for today is a T-Shirt paired with Jeans."));
    assertFalse(description.contains("It is accessorized with"));
    assertFalse(description.contains("For outerwear, there's"));
  }
}
