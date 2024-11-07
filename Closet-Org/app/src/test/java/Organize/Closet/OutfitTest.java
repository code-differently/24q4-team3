package Organize.Closet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OutfitTest {
    private BottomItems jeans;
    private Outfit outfit;

    @BeforeEach
    public void setUp() {    
        jeans = new BottomItems("Jeans", "blue", null, null, null, null, false, null);
        outfit = new Outfit(jeans);
    }

    
    @Test
    void testGetPants() {
        assertEquals("Jeans", outfit.getPants().getName());
    }

    @Test
    void testSetPants() {
       
        BottomItems shorts = new BottomItems("Shorts", null, null, null, null, null, false, null);
        outfit.setPants(shorts);

        assertEquals("Shorts", outfit.getPants().getName());
    }

    @Test
    void testDescribeOutfit() {
       
        String description = outfit.describeOutfit();
        assertEquals("The outfit for today is Jeans", description);
    }
}
