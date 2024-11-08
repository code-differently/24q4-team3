package Organize.Closet;

import java.util.List;

public interface Closet {
    void addArticleOfClothing(String clothingId);
    boolean removeArticleOfClothing(String clothingId);
    boolean updateArticleOfClothing(String clothingId, ArticleOfClothing updatedclothing);
    List<ArticleOfClothing> getAllArticleOfClothings();
    List<ArticleOfClothing> getArticleOfClothingBySeason(String season);
    List<ArticleOfClothing> getArticleOfClothingByDesign(String design);
    List<ArticleOfClothing> getArticleOfClothingByColor(String color);
    List<ArticleOfClothing> getArticleOfClothingByName(String name);
    List<ArticleOfClothing> getArticleOfClothingByOccasion(String occasion);
}
