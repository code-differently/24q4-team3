package Organize.Closet;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;

class InClosetMemoryTest {

    private InClosetMemory closet;
    private ArticleOfClothing tops;
    private ArticleOfClothing bottoms;
    private ArticleOfClothing accessories;
    private ArticleOfClothing outerwear;
    private ArticleOfClothing fullbody;
   
    @BeforeEach
    void setUp() {
        closet = new InClosetMemory();
        
        tops = new ArticleOfClothing() {
            @Override public String getName() { return "Blouse"; }
            @Override public String getColor() { return "Blue"; }
            @Override public String getSeasonalPurpose() { return "Summer"; }
            @Override public String getStyle() { return "V-Neck"; }
            @Override public String getOccasion() { return "Everyday Casual"; }
        };

        bottoms = new ArticleOfClothing() {
            @Override public String getName() { return "Dress Pants"; }
            @Override public String getColor() { return "Black"; }
            @Override public String getSeasonalPurpose() { return "Winter"; }
            @Override public String getStyle() { return "Long"; }
            @Override public String getOccasion() { return "Work"; }
        };

        accessories = new ArticleOfClothing() {
            @Override public String getName() { return "HandBag"; }
            @Override public String getColor() { return "Pink"; }
            @Override public String getSeasonalPurpose() { return "Summer"; }
            @Override public String getStyle() { return "Wicker"; }
            @Override public String getOccasion() { return "Everyday Casual"; }
        };

        outerwear = new ArticleOfClothing() {
            @Override public String getName() { return "Blazer"; }
            @Override public String getColor() { return "Gray"; }
            @Override public String getSeasonalPurpose() { return "Winter"; }
            @Override public String getStyle() { return "Mandarin Collar"; }
            @Override public String getOccasion() { return "Work"; }
        };

        fullbody = new ArticleOfClothing() {
            @Override public String getName() { return "Dress"; }
            @Override public String getColor() { return "Yellow"; }
            @Override public String getSeasonalPurpose() { return "Summer"; }
            @Override public String getStyle() { return "Sundress"; }
            @Override public String getOccasion() { return "Party"; }
        };

        closet.addArticleOfClothing(tops);
        closet.addArticleOfClothing(bottoms);
        closet.addArticleOfClothing(accessories);
        closet.addArticleOfClothing(outerwear);
        closet.addArticleOfClothing(fullbody);
    }

    @Test
    void testAddArticleOfClothing() {
        ArticleOfClothing outerwear = new ArticleOfClothing("Hoodie", "Black", "Winter", "PullOver", "Outdoor");
        closet.addArticleOfClothing(outerwear);
        assertTrue(closet.getArticleOfClothing().contains(outerwear));
    }

    @Test
    void testAddNullArticleOfClothing() {
        assertThrows(IllegalArgumentException.class, () -> closet.addArticleOfClothing(null));
    }

    @Test
    void testRemoveArticleOfClothing() {
        assertTrue(closet.removeArticleOfClothing("Dress"));
        assertFalse(closet.getArticleOfClothing().contains(tops));
    }

    @Test
    void testRemoveNonExistentArticleOfClothing() {
        assertFalse(closet.removeArticleOfClothing("NonExistent"));
    }

    @Test
    void testUpdateArticleOfClothing() {
        ArticleOfClothing updatedFullBody = new ArticleOfClothing("Dress", "Red", "Backless", "Party", "Spring");
        assertTrue(closet.updateArticleOfClothing("Dress", updatedFullBody));
        List<ArticleOfClothing> allClothing = closet.getArticleOfClothing();
        assertTrue(allClothing.contains(updatedFullBody));
        assertEquals("Red", allClothing.get(allClothing.indexOf(updatedFullBody)).getColor());
    }

    @Test
    void testUpdateNonExistentArticleOfClothing() {
        ArticleOfClothing newClothing = new ArticleOfClothing("NonExistent", "White", "V-Neck", "Formal", "Winter");
        assertFalse(closet.updateArticleOfClothing("NonExistent", newClothing));
    }

    @Test
    void testGetAllArticleOfClothings() {
        List<ArticleOfClothing> allClothing = closet.getAllArticleOfClothings();
        assertEquals(2, allClothing);
        assertTrue(allClothing.contains(tops));
        assertTrue(allClothing.contains(bottoms));
    }

    @Test
    void testGetArticleOfClothingBySeasonalPurpose() {
        List<ArticleOfClothing> winterClothing = closet.getArticleOfClothingBySeasonalPurpose("Winter");
        assertEquals(1, winterClothing);
        assertTrue(winterClothing.contains(bottoms));
    }

    @Test
    void testGetArticleOfClothingByStyle() {
        List<ArticleOfClothing> vNeckClothing = closet.getArticleOfClothingByStyle("V-Neck");
        assertEquals(1, vNeckClothing);
        assertTrue(vNeckClothing.contains(tops));
    }

    @Test
    void testGetArticleOfClothingByColor() {
        List<ArticleOfClothing> blueClothing = closet.getArticleOfClothingByColor("Blue");
        assertEquals(1, blueClothing);
        assertTrue(blueClothing.contains(tops));
    }

    @Test
    void testGetArticleOfClothingByOccasion() {
        List<ArticleOfClothing> everydayCasualClothing = closet.getArticleOfClothingByOccasion("Everyday Casual");
        assertEquals(1, everydayCasualClothing);
        assertTrue(everydayCasualClothing.contains(accessories));
    }

    @Test
    void testGetArticleOfClothingByName() {
        List<ArticleOfClothing> namedClothing = closet.getArticleOfClothingByName("Blazer");
        assertEquals(1, namedClothing);
        assertTrue(namedClothing.contains(outerwear));
    }
}
