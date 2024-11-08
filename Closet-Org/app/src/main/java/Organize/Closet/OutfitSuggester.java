package Organize.Closet;

import java.util.List;
import java.util.stream.Collectors;

public class OutfitSuggester {

    private Closet closet;        
    private Weather weather;      

    public OutfitSuggester(Closet closet, Weather weather) {
        this.closet = closet;
        this.weather = weather;
    }

    public List<ArticleOfClothing> suggestOutfit() {
        String season = weather.getSeason();   
        double temperature = weather.getTemperature();
      

        
        List<ArticleOfClothing> seasonalClothing = closet.getArticleOfClothingBySeasonalPurpose(season);

        
        return seasonalClothing.stream()
                .filter(item -> isSuitableForWeather(item, season, temperature))
                .collect(Collectors.toList());
    }

    
    private boolean isSuitableForWeather(ArticleOfClothing item, String season, double temperature) {
        String condition;
                switch (condition.toLowerCase()) {
            case "rainy":
                return !(item instanceof Accessories && item.getName().equalsIgnoreCase("Sunglasses"));
            case "snowy":
                return item instanceof Outerwear || item instanceof Fullbody || item.getStyle().equalsIgnoreCase("Warm");
            case "sunny":
                return !(item.getStyle().equalsIgnoreCase("Heavy") || item.getStyle().equalsIgnoreCase("Warm"));
            default:
                return true;
        }
    }
}
