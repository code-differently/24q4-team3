package Organize.Closet;

public class FullbodyItem implements Fullbody {
    private final String name;
    private final String color;
    private final String style;
    private final String occasion;
    private final String seasonalPurpose;

 
    private String material;
    private String sleeveLength;
    private String closureType;
    private final boolean hasPockets;
    private boolean isMachineWashable;

    public FullbodyItem(
        String name, 
        String color, 
        String style, 
        String occasion, 
        String seasonalPurpose, 
        String material, 
        String sleeveLength, 
        String closureType, 
        boolean hasPockets, 
        boolean isMachineWashable) {
    this.name = name;
    this.color = color;
    this.style = style;
    this.occasion = occasion;
    this.seasonalPurpose = seasonalPurpose;
    this.hasPockets = hasPockets;
  }

    public String getColor() {
        return color;
    }

    public String getStyle() {
        return style;
    }


    public String getName() {
        return name;
    }

    public String getOccasion() {
       return occasion;
    }

    public String seasonalPurpose() {
        return seasonalPurpose;
    }

    @Override
    public boolean hasPockets() {
        return hasPockets;
    }

    @Override
    public String getMaterial() {
        return material;
    }

    @Override
    public String getSleeveLength() {
        return sleeveLength;
    }

    @Override
    public String getClosureType() {
        return closureType;
    }

    @Override
    public boolean isMachineWashable() {
        return isMachineWashable;
    }   
}
