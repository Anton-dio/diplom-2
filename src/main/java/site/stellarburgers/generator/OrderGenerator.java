package site.stellarburgers.generator;

import site.stellarburgers.pojo.CreateOrder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderGenerator {

    final static String INCORRECT_INGREDIENT_HASH = "abc";
    final static String INGREDIENTS_URL = "https://example.com/api/ingredients"; // Замените на ваш URL

    public static CreateOrder getDefaultOrder() throws IOException {
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

    private static List<String> getValidIngredients() throws IOException {
        List<String> validIngredients = new ArrayList<>();
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(INGREDIENTS_URL);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String responseBody = EntityUtils.toString(response.getEntity());
                JSONArray ingredientsArray = new JSONArray(responseBody);
                for (int i = 0; i < ingredientsArray.length(); i++) {
                    JSONObject ingredient = ingredientsArray.getJSONObject(i);
                    validIngredients.add(ingredient.getString("_id"));
                }
            }
        }
        return validIngredients;
    }
}