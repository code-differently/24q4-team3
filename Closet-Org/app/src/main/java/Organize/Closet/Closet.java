package Organize.Closet;

import java.util.List;

public interface Closet {
    void addArticleOfClothing(ArticleOfClothing clothing);
    boolean removeArticleOfClothing(String clothing);
    boolean updateArticleOfClothing(String clothing, ArticleOfClothing updatedclothing);
    List<ArticleOfClothing> getAllArticleOfClothings();
    List<ArticleOfClothing> getArticleOfClothingBySeason(String season);
    List<ArticleOfClothing> getArticleOfClothingByStyle(String style);
    List<ArticleOfClothing> getArticleOfClothingByColor(String color);
    List<ArticleOfClothing> getArticleOfClothingByName(String name);
    List<ArticleOfClothing> getArticleOfClothingByOccasion(String occasion);
}
