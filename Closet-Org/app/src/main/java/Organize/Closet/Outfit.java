package Organize.Closet;

import Organize.Closet.exceptions.TemperatureException;

public class Outfit {
    private BottomItems pants;
    private TopItems shirt;
    private FullbodyItem fullBody;
    private AccessoriesItems accessories;
    private OuterwearItems outerwear;

    public Outfit(BottomItems pants, TopItems shirt) {
        this.pants = pants;
        this.fullBody = null;
        this.shirt = shirt;
    }

    public Outfit(FullbodyItem fullBody) {
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

      public FullbodyItem getFullBody() {
        return fullBody;
      }

      public void setFullBody(FullbodyItem fullBody) {
        this.fullBody = fullBody;
    }

    public AccessoriesItems setAccessories(AccessoriesItems hat) {
      return hat;
    }

    public OuterwearItems setOuterwear(OuterwearItems jacket) {
      return jacket;
    }

    public void checkTemperature(double temperature) throws TemperatureException {
        if (temperature < 60) {
            throw new TemperatureException(temperature); 
        } else if (temperature > 85) {
            throw new TemperatureException(temperature); 
        }
    }

    public String describeOutfit() {
        StringBuilder description = new StringBuilder();
        
        if (fullBody != null) {
            description.append("The outfit for today is a full-body ").append(fullBody.getName()).append(". ");
        } else {
            description.append("The outfit for today is a ").append(shirt.getName()).append(" paired with ").append(pants.getName()).append(". ");
        }

        if (accessories != null) {
            description.append("It is accessorized with a ").append(accessories.getName()).append(". ");
        }

        if (outerwear != null) {
            description.append("For outerwear, there's a ").append(outerwear.getName()).append(". ");
        }

        return description.toString();
    }


  }

