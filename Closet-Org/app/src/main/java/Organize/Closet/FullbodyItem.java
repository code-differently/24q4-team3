package Organize.Closet;

public class FullbodyItem implements Fullbody{
    private String name;
    private String color;
    private String style;
    private String occasion;
    private String seasonalPurpose;

 
    private String material;
    private String sleeveLength;
    private String closureType;
    private boolean hasPockets;
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
}
