package site.stellarburgers.generator;


import site.stellarburgers.pojo.CreateOrder;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderGenerator {

    final static String INCORRECT_INGREDIENT_HASH = "abc";
    final static String INGREDIENTS_URL = "https://example.com/api/ingredients"; // Замените на ваш URL

    public static CreateOrder getDefaultOrder() {
        List<String> validIngredients = getValidIngredients();
        if (!validIngredients.isEmpty()) {
            return new CreateOrder(List.of(validIngredients.get(0)));
        } else {
            throw new IllegalStateException("No valid ingredients found");
        }
    }

    public static CreateOrder getOrderWithoutIngredients() {
        return new CreateOrder(List.of());
    }

    public static CreateOrder getOrderWithIncorrectHash() {
        return new CreateOrder(List.of(INCORRECT_INGREDIENT_HASH));
    }

    private static List<String> getValidIngredients() {
        List<String> validIngredients = new ArrayList<>();
        Response response = RestAssured.get(INGREDIENTS_URL);
        String responseBody = response.getBody().asString();
        JSONArray ingredientsArray = new JSONArray(responseBody);
        for (int i = 0; i < ingredientsArray.length(); i++) {
            JSONObject ingredient = ingredientsArray.getJSONObject(i);
            validIngredients.add(ingredient.getString("_id"));
        }
        return validIngredients;
    }
}