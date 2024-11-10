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
    switch (season.toLowerCase()) {
        case "winter":
            condition = "snowy";
            break;
        case "summer":
            condition = "sunny";
            break;
        case "spring":
        case "fall":
            condition = "rainy";  
            break;
        default:
            condition = "clear";  
    }

    if (temperature < 0) {
        condition = "snowy";
    } else if (temperature > 30) {
        condition = "sunny";
    }
    switch (condition.toLowerCase()) {
        case "rainy":
            return !(item instanceof AccessoriesItems && item.getName().equalsIgnoreCase("Sunglasses"));
        case "snowy":
            return item instanceof OuterwearItems || item instanceof FullbodyItem || item.getStyle().equalsIgnoreCase("Warm");
        case "sunny":
            return !(item.getStyle().equalsIgnoreCase("Heavy") || item.getStyle().equalsIgnoreCase("Warm"));
        default:
            return true;
    }
    }
}
