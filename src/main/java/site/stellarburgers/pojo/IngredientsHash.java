package site.stellarburgers.pojo;

import lombok.Data;

import java.util.List;

@Data
public class IngredientsHash {
    private final List<String> ingredients;

    public IngredientsHash(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}