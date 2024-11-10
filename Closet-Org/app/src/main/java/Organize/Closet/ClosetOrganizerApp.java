package Organize.Closet;

import java.util.List;

public class ClosetOrganizerApp {

    public static void main(String[] args) {
       
        Closet closet = new InClosetMemory();
        Weather weather = new WeatherAPIData(0, null, 0, null); 
        
       
        closet.addArticleOfClothing(new TopItems("Shirt", "Blue", "Polo", "Everyday Casual", "Summer", false, null, null, null, false));
        closet.addArticleOfClothing(new OuterwearItems("Black", "Poncho", "Rain-Jacket", "Outdoor", "Rainy Weather", null));
        closet.addArticleOfClothing(new AccessoriesItems("Pink", "Plastic Frame", "Sunglasses", "Outdoor", "Summer", null));
        closet.addArticleOfClothing(new BottomItems("Jeans", "Blue", "Capri", "Everyday Casual", "Summer", null, false, null));
        closet.addArticleOfClothing(new FullbodyItem("Romper", "White", "Sleeveless", "Everyday Casual", "Summer", null, null, null, false, false));
        
       
        OutfitSuggester outfitSuggester = new OutfitSuggester(closet, weather);

      
        List<ArticleOfClothing> suggestedOutfit = outfitSuggester.suggestOutfit();

        System.out.println("Suggested outfit based on today's weather:");
        suggestedOutfit.forEach(item -> System.out.println(item.getName() + " - " + item.getStyle()));
    }
}