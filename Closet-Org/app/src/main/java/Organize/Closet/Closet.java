package Organize.Closet;

import java.util.List;

public interface Closet {
    FullbodyItem addArticleOfClothing(FullbodyItem clothing);
    boolean removeArticleOfClothing(String clothing);
    boolean updateArticleOfClothing(String clothing, ArticleOfClothing updatedclothing);
    List<ArticleOfClothing> getAllArticleOfClothings();
    List<ArticleOfClothing> getArticleOfClothingBySeasonalPurpose(String season);
    List<ArticleOfClothing> getArticleOfClothingByStyle(String style);
    List<ArticleOfClothing> getArticleOfClothingByColor(String color);
    List<ArticleOfClothing> getArticleOfClothingByName(String name);
    List<ArticleOfClothing> getArticleOfClothingByOccasion(String occasion);
    TopItems addArticleOfClothing(TopItems topItems);
    OuterwearItems addArticleOfClothing(OuterwearItems outerwearItems);
    AccessoriesItems addArticleOfClothing(AccessoriesItems accessoriesItems);
    BottomItems addArticleOfClothing(BottomItems bottomItems);
}
