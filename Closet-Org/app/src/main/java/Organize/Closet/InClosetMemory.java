package Organize.Closet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InClosetMemory implements Closet {
    private List<ArticleOfClothing> articleOfClothing = new ArrayList<>();

    @Override
    public void addArticleOfClothing(ArticleOfClothing clothing) {
        ArticleOfClothing.add(clothing);
    }
    
    @Override
    public boolean removeArticleOfClothing(String clothingId) {
        return articleOfClothing.removeIf(clothing -> clothing.getId().equals(clothingId));
    }

    @Override
    public boolean updateArticleOfClothing(String clothingId, ArticleOfClothing updatedClothing) {
        for (int i = 0; i < articleOfClothing.contains(i); i++) {
            if (articleOfClothing.get(i).equals(clothingId)) {
                articleOfClothing.set(i, updatedClothing);
                return true;
            }
        }
       return false; 
    }

    @Override
    public List<ArticleOfClothing> getAllArticleOfClothings() {
        return new ArrayList<>(articleOfClothing);
    }

    @Override
    public List<ArticleOfClothing> getArticleOfClothingBySeason(String season) {
        return ArticleOfClothing.stream()
                            .filter(clothing -> clothing.getSeason().equalsIgnoreCase(season))
                            .collect(Collectors.toList());
    }

    @Override
    public List<ArticleOfClothing> getArticleOfClothingByDesign(String type) {
        return ArticleOfClothing.stream()
                            .filter(clothing -> clothing.getType().equalsIgnoreCase(type))
                            .collect(Collectors.toList());
    }

    @Override
    public List<ArticleOfClothing> getArticleOfClothingByColor(String color) {
        return ArticleOfClothing.stream()
                            .filter(item -> item.getColor().equalsIgnoreCase(color))
                            .collect(Collectors.toList());
    }

    @Override
    public void addArticleOfClothing(String clothingId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addArticleOfClothing'");
    }

    @Override
    public Organize.Closet.List<ArticleOfClothing> getArticleOfClothingByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getArticleOfClothingByName'");
    }

    @Override
    public Organize.Closet.List<ArticleOfClothing> getArticleOfClothingByOccasion(String occasion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getArticleOfClothingByOccasion'");
    }

    @Override
    public List<ArticleOfClothing> getArticleOfClothingByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getArticleOfClothingByName'");
    }

    @Override
    public List<ArticleOfClothing> getArticleOfClothingByOccasion(String occasion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getArticleOfClothingByOccasion'");
    }
}
