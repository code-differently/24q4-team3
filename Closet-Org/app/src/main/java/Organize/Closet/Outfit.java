package Organize.Closet;

public class Outfit {
    private BottomItems pants;
    // private Weather weather;  

    public Outfit(BottomItems pants) {
        this.pants = pants;
        // this.weather = weather;
    }

    public BottomItems getPants() {
        return pants;
      }

    public void setPants(BottomItems pants) {
        this.pants = pants;
      }

      public String describeOutfit() {
        return "The outfit for today is " + pants.getName();
        // ", perfect for " + weather.getCondition() + ".";
      }
}
