package Organize.Closet;

import java.util.ArrayList;
import java.util.List;

public class InClosetMemory implements Closet {
    private List<ArticleOfClothing> articleOfClothing = new ArrayList<>();

    @Override
    public void addArticleOfClothing(articleOfClothing clothing) {
        ArticleOfClothing.add(clothing);
    }
    
    @Override
    public boolean removeArticleOfClothing(String clothingId) {
        return articleOfClothing.removeIf(clothing -> clothing.getId().equals(clothingId));
    }

    @Override
    public boolean updateArticleOfClothing(String clothingId, ArticleOfClothing updatedClothing) {
        for (int i = 0; i < articleOfClothing.contains(); i++) {
            if (articleOfClothing.get().equals(clothingId)) {
                articleOfClothing.set(i, updatedClothing);
                return true;
            }
        }
       return false; 
    }
}
