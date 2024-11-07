package Organize.Closet;

public class BottomItems implements Bottoms {
  private String name;
  private String color;
  private String style;
  private String occasion;
  private String seasonalPurpose;

  private String waistLine;
  private boolean hasPockets;
  private String bottomLength;

  public BottomItems(
      String name,
      String color,
      String style,
      String occasion,
      String seasonalPurpose,
      String waistLine,
      boolean hasPockets,
      String bottomLength) {
    this.name = name;
    this.color = color;
    this.style = style;
    this.occasion = occasion;
    this.seasonalPurpose = seasonalPurpose;
    this.bottomLength = bottomLength;
    this.waistLine = waistLine;
    this.hasPockets = hasPockets;
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

  @Override
  public String bottomLength() {
    return bottomLength;
  }

  @Override
  public String getWaistLine() {
    return waistLine;
  }

  @Override
  public boolean hasPockets() {
    return hasPockets;
  }

  public String describeItem() {
    StringBuilder description = new StringBuilder();
    description.append(String.format("Style: %s, Name: %s, Color: %s. ", style, name, color));
    description.append(String.format("Occasion: %s, Seasonal: %s. ", occasion, seasonalPurpose));
    // description.append(String.format("Name: %s. ", name));

    return description.toString();
  }
}
