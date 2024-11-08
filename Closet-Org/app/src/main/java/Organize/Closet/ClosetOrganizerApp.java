package Organize.Closet;

import java.util.List;

public class ClosetOrganizerApp {

    public static void main(String[] args) {
       
        Closet closet = new InClosetMemory();
        Weather weather = new WeatherAPIData(); 
        
       
        closet.addArticleOfClothing(new Tops("Shirt", "Blue", "Summer", "Polo", "Everyday Casual"));
        closet.addArticleOfClothing(new Outerwear("Rain Jacket", "Black", "Spring", "Poncho", "Outdoor"));
        closet.addArticleOfClothing(new Accessories("Sunglasses", "Black", "Summer", "Plastic Frame", "Outdoor"));
        closet.addArticleOfClothing(new Bottoms("Jeans", "Blue", "Summer", "Capri", "Everyday Casual"));
        closet.addArticleOfClothing(new FullBody("Romper", "White", "Summer", "Sleeveless", "Everyday Casual"));
        
       
        OutfitSuggester outfitSuggester = new OutfitSuggester(closet, weather);

      
        List<ArticleOfClothing> suggestedOutfit = outfitSuggester.suggestOutfit();

        System.out.println("Suggested outfit based on today's weather:");
        suggestedOutfit.forEach(item -> System.out.println(item.getName() + " - " + item.getStyle()));
    }
}