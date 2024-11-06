package Organize.Closet;

public class Outfit implements Tops, Bottoms, Accessories, Weather {
    private String color;
    private String style;
    private String name;
    private String occasion;
    private String season;
    private boolean isInCloset;

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

    public Outfit () {
        this.isInCloset = false;
    }

    public void isInCloset(boolean status) {
        isInCloset = status;
        if (status) {
            System.out.println("Article of Clothing is in the closet.");
        } else {
            System.out.println("Article of Clothing is not in the closet.");
        }
    }

    public boolean checkCloset() {
        return isInCloset;
    }
}