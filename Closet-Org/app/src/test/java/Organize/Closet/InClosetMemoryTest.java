package Organize.Closet;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class InClosetMemoryTest {

    private InClosetMemory closet;
    private ArticleOfClothing topItems;
    private ArticleOfClothing bottomItems;
    private ArticleOfClothing accessoriesItems;
    private ArticleOfClothing outerwearItems;
    private ArticleOfClothing fullbodyItem;
   
    @BeforeEach
    void setUp() {
        closet = new InClosetMemory();
        
        topItems = new ArticleOfClothing() {
            @Override public String getName() { return "Blouse"; }
            @Override public String getColor() { return "Blue"; }
            @Override public String getSeasonalPurpose() { return "Summer"; }
            @Override public String getStyle() { return "V-Neck"; }
            @Override public String getOccasion() { return "Everyday Casual"; }
        };

        bottomItems = new ArticleOfClothing() {
            @Override public String getName() { return "Dress Pants"; }
            @Override public String getColor() { return "Black"; }
            @Override public String getSeasonalPurpose() { return "Winter"; }
            @Override public String getStyle() { return "Long"; }
            @Override public String getOccasion() { return "Work"; }
        };

        accessoriesItems = new ArticleOfClothing() {
            @Override public String getName() { return "HandBag"; }
            @Override public String getColor() { return "Pink"; }
            @Override public String getSeasonalPurpose() { return "Summer"; }
            @Override public String getStyle() { return "Wicker"; }
            @Override public String getOccasion() { return "Everyday Casual"; }
        };

        outerwearItems = new ArticleOfClothing() {
            @Override public String getName() { return "Blazer"; }
            @Override public String getColor() { return "Gray"; }
            @Override public String getSeasonalPurpose() { return "Winter"; }
            @Override public String getStyle() { return "Mandarin Collar"; }
            @Override public String getOccasion() { return "Work"; }
        };

        fullbodyItem = new ArticleOfClothing() {
            @Override public String getName() { return "Dress"; }
            @Override public String getColor() { return "Yellow"; }
            @Override public String getSeasonalPurpose() { return "Summer"; }
            @Override public String getStyle() { return "Sundress"; }
            @Override public String getOccasion() { return "Party"; }
        };

        closet.addArticleOfClothing(fullbodyItem);
        closet.addArticleOfClothing(topItems);
        closet.addArticleOfClothing(bottomItems);
        closet.addArticleOfClothing(accessoriesItems);
        closet.addArticleOfClothing(outerwearItems);
    }

    @Test
    void testAddArticleOfClothing() {
        ArticleOfClothing outerwearItem = new ArticleOfClothing() {
            @Override public String getName() { return "Hoodie"; }
            @Override public String getColor() { return "Black"; }
            @Override public String getSeasonalPurpose() { return "Winter"; }
            @Override public String getStyle() { return "PullOver"; }
            @Override public String getOccasion() { return "Outdoor"; }
        };
        closet.addArticleOfClothing(outerwearItem);
        assertTrue(closet.getArticleOfClothing().contains(outerwearItem));
    }

    
    @Test
    void testRemoveArticleOfClothing() {
        assertTrue(closet.removeArticleOfClothing("Dress"));
        assertFalse(closet.getArticleOfClothing().contains(fullbodyItem));
    }

    @Test
    void testRemoveNonExistentArticleOfClothing() {
        assertFalse(closet.removeArticleOfClothing("NonExistent"));
    }

    @Test
    void testUpdateArticleOfClothing() {
        ArticleOfClothing updatedFullBodyItem = new ArticleOfClothing() {
            @Override public String getName() { return "Dress"; }
            @Override public String getColor() { return "Red"; }
            @Override public String getSeasonalPurpose() { return "Spring"; }
            @Override public String getStyle() { return "Backless"; }
            @Override public String getOccasion() { return "Party"; }
        };
        assertTrue(closet.updateArticleOfClothing("Dress", updatedFullBodyItem));
        List<ArticleOfClothing> allClothing = closet.getArticleOfClothing();
        assertTrue(allClothing.contains(updatedFullBodyItem));
        assertEquals("Red", allClothing.get(allClothing.indexOf(updatedFullBodyItem)).getColor());
    }

    @Test
    void testUpdateNonExistentArticleOfClothing() {
        ArticleOfClothing newClothing = new ArticleOfClothing() {
            @Override public String getName() { return "NonExistent"; }
            @Override public String getColor() { return "White"; }
            @Override public String getSeasonalPurpose() { return "Winter"; }
            @Override public String getStyle() { return "V-Neck"; }
            @Override public String getOccasion() { return "Formal"; }
        };
        assertFalse(closet.updateArticleOfClothing("NonExistent", newClothing));
    }

    @Test
    void testGetAllArticleOfClothings() {
        List<ArticleOfClothing> allClothing = closet.getAllArticleOfClothings();
        assertEquals(5, allClothing.size());
        assertTrue(allClothing.contains(topItems));
        assertTrue(allClothing.contains(bottomItems));
    }

    @Test
    void testGetArticleOfClothingBySeasonalPurpose() {
        List<ArticleOfClothing> winterClothing = closet.getArticleOfClothingBySeasonalPurpose("Winter");
        assertEquals(2, winterClothing.size());
        assertTrue(winterClothing.contains(bottomItems));
        assertTrue(winterClothing.contains(outerwearItems));
    }

    @Test
    void testGetArticleOfClothingByStyle() {
        List<ArticleOfClothing> vNeckClothing = closet.getArticleOfClothingByStyle("V-Neck");
        assertEquals(1, vNeckClothing.size());
        assertTrue(vNeckClothing.contains(topItems));
    }

    @Test
    void testGetArticleOfClothingByColor() {
        List<ArticleOfClothing> blueClothing = closet.getArticleOfClothingByColor("Blue");
        assertEquals(1, blueClothing.size());
        assertTrue(blueClothing.contains(topItems));
    }

    @Test
    void testGetArticleOfClothingByOccasion() {
        List<ArticleOfClothing> everydayCasualClothing = closet.getArticleOfClothingByOccasion("Everyday Casual");
        assertEquals(2, everydayCasualClothing.size());
        assertTrue(everydayCasualClothing.contains(topItems));
        assertTrue(everydayCasualClothing.contains(accessoriesItems));
    }

    @Test
    void testGetArticleOfClothingByName() {
        List<ArticleOfClothing> namedClothing = closet.getArticleOfClothingByName("Blazer");
        assertEquals(1, namedClothing.size());
        assertTrue(namedClothing.contains(outerwearItems));
    }
}
