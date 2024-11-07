package Organize.Closet;

public class Outfit {
    private BottomItems pants;
    private TopItems shirt;
    private Fullbody fullBody;
    // private Weather weather;  

    public Outfit(BottomItems pants, TopItems shirt) {
        this.pants = pants;
        this.fullBody = null;
        this.shirt = shirt;
        // this.weather = weather;
    }

    public Outfit(Fullbody fullBody) {
      this.fullBody = fullBody;
      this.pants = null;
      this.shirt = null;
    }

    public BottomItems getPants() {
        return pants;
      }

    public void setPants(BottomItems pants) {
        this.pants = pants;
      }

      public TopItems geShirt() {
        return shirt;
      }

      public void setTop(TopItems shirt) {
        this.shirt = shirt;
      }

      public Fullbody getFullBody() {
        return fullBody;
      }

      public String describeOutfit() {
        if (fullBody != null) {
          return "The outfit for today is a full-body " + fullBody.getName() + ".";
      } else {
        return "The outfit for today is a " + shirt.getName() + " paired with " + pants.getName() + ".";
        // ", perfect for " + weather.getCondition() + ".";
      }
  }
}
