package Organize.Closet;

public class OuterwearItems implements OuterWear {
  private final String color;
  private final String style;
  private final String name;
  private final String occasion;
  private final String seasonalPurpose;
  private final String insulationLevel;

  // Constructor
  public OuterwearItems(
      String color,
      String style,
      String name,
      String occasion,
      String seasonalPurpose,
      String insulationLevel) {
    this.color = color;
    this.style = style;
    this.name = name;
    this.occasion = occasion;
    this.seasonalPurpose = seasonalPurpose;
    this.insulationLevel = insulationLevel;
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
  public String getSeasonalPurpose() {
    return seasonalPurpose;
  }

  @Override
  public String getInsulationLevel() {
    return insulationLevel;
  }

  @Override
  public boolean isWaterproof() {
    return true;
  }

  public String describeItem() {
    StringBuilder description = new StringBuilder();
    description.append(String.format("Style: %s, Name: %s, Color: %s. ", style, name, color));
    description.append(
        String.format("Occasion: %s, Seasonal Purpose: %s. ", occasion, seasonalPurpose));
    description.append(
        String.format(
            "Insulation Level: %s, Waterproof: %s.",
            insulationLevel, isWaterproof() ? "Yes" : "No"));
    return description.toString();
  }
}
