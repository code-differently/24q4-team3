package Organize.Closet;

public class AccessoriesItems implements Accessories {
  private final String color;
  private final String style;
  private final String name;
  private final String occasion;
  private final String seasonalPurpose;
  private final String accessoryType;

  // Constructor
  public AccessoriesItems(
      String color,
      String style,
      String name,
      String occasion,
      String seasonalPurpose,
      String accessoryType) {
    this.color = color;
    this.style = style;
    this.name = name;
    this.occasion = occasion;
    this.seasonalPurpose = seasonalPurpose;
    this.accessoryType = accessoryType;
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
  public String getOccassion() {
    return occasion;
  }

  @Override
  public String seasonalPurpose() {
    return seasonalPurpose;
  }

  @Override
  public String getAccessoryType() {
    return accessoryType;
  }
}
