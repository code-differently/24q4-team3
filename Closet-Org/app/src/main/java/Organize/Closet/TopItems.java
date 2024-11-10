package Organize.Closet;

public class TopItems implements Tops {
  private String name, color, style, occasion, seasonalPurpose;

  private boolean hasPocket;
  String getShirtLength, getNeckLine, getSleeveLength;
  private boolean hasStraps;

  public TopItems(
      String name,
      String color,
      String style,
      String occasion,
      String seasonalPurpose,
      boolean hasPocket,
      String getShirtLength,
      String getNeckLine,
      String getSleeveLength,
      boolean hasStraps) {
    this.name = name;
    this.color = color;
    this.style = style;
    this.occasion = occasion;
    this.seasonalPurpose = seasonalPurpose;
    this.hasPocket = hasPocket;
    this.getShirtLength = getShirtLength;
    this.getNeckLine = getNeckLine;
    this.getSleeveLength = getSleeveLength;
    this.hasStraps = hasStraps;
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
  public boolean hasPocket() {
    return hasPocket;
  }

  @Override
  public String getShirtLength() {
    return getShirtLength;
  }

  @Override
  public String getNeckLine() {
    return getNeckLine;
  }

  @Override
  public String getSleeveLength() {
    return getSleeveLength;
  }

  @Override
  public boolean hasStraps() {
    return hasStraps;
  }

  public String describeItem() {
    StringBuilder description = new StringBuilder();
    description.append(String.format("Style: %s, Name: %s, Color: %s. ", style, name, color));
    description.append(String.format("Occasion: %s, Seasonal: %s. ", occasion, seasonalPurpose));

    return description.toString();
  }
}
