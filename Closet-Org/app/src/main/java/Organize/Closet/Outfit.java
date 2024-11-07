package Organize.Closet;

import java.util.ArrayList;
import java.util.List;

public class Outfit implements Tops, Bottoms, Accessories, Weather {
    private String color;
    private String style;
    private String name;
    private String occasion;
    private String seasonalPurpose;
    private boolean isInCloset;

    private final List<Outfit> clothingItems;

    public Outfit() {
        this.isInCloset = false;
        this.clothingItems = new ArrayList<>();
    }

    public void addClothing(Outfit clothing) {
        clothingItems.add(clothing);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getStyle() {
        return style;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getOccasion() {
        return occasion;
    }

    @Override
    public String seasonalPurpose() {
        return seasonalPurpose;
    }

    public void isInCloset(boolean status) {
        isInCloset = status;
    }

    public boolean checkCloset() {
        return isInCloset;
    }

    public String describeOutfit() {
        StringBuilder description = new StringBuilder();
        description.append("This outfit is for ").append(occasion)
                .append(" occasions. It is ").append(color).append(" in color.\n");
        
        for (Outfit item : clothingItems) {
            description.append(item.describeOutfit()).append("\n");
        }
        
        return description.toString();
    }
}