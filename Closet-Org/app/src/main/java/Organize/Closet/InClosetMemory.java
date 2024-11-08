package Organize.Closet;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class InClosetMemory implements Closet {
    private List<ArticleOfClothing> articleOfClothing = new ArrayList<>();
        private Object clothingName;
    
        
        @Override
        public boolean removeArticleOfClothing(String clothing) {
            return articleOfClothing.removeIf(item -> item.getName().equals(clothingName));
    }

    @Override
    public boolean updateArticleOfClothing(String clothingName, ArticleOfClothing updatedClothing) {
        ListIterator<ArticleOfClothing> iterator = articleOfClothing.listIterator();

        while (iterator.hasNext()) {
            ArticleOfClothing item = iterator.next();
            if (item.getName().equals(clothingName)) {
                iterator.set(updatedClothing);
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
        return articleOfClothing.stream()
                            .filter(clothing -> clothing.getSeason().equalsIgnoreCase(season))
                            .collect(Collectors.toList());
    }

    @Override
    public List<ArticleOfClothing> getArticleOfClothingByStyle(String style) {
        return articleOfClothing.stream()
                            .filter(clothing -> clothing.getStyle().equalsIgnoreCase(style))
                            .collect(Collectors.toList());
    }

    @Override
    public List<ArticleOfClothing> getArticleOfClothingByColor(String color) {
        return articleOfClothing.stream()
                            .filter(clothing -> clothing.getColor().equalsIgnoreCase(color))
                            .collect(Collectors.toList());
    }
 
    @Override
    public List<ArticleOfClothing> getArticleOfClothingByOccasion(String occasion) {
        return articleOfClothing.stream()
                            .filter(clothing -> clothing.getOccasion().equalsIgnoreCase(occasion))
                            .collect(Collectors.toList());
    }

    @Override
    public List<ArticleOfClothing> getArticleOfClothingByName(String name) {
        return articleOfClothing.stream()
                            .filter(clothing -> clothing.getName().equalsIgnoreCase(name))
                            .collect(Collectors.toList());
    }

    public List<ArticleOfClothing> getArticleOfClothing() {
        return articleOfClothing;
    }

    public void setArticleOfClothing(List<ArticleOfClothing> articleOfClothing) {
        this.articleOfClothing = articleOfClothing;
    }

    @Override
    public void addArticleOfClothing(ArticleOfClothing clothing) {
        if (clothing != null) {
            articleOfClothing.add(clothing);
        } else {
            throw new IllegalArgumentException("Cannot add a null article of clothing");
        }
        
    }


}
