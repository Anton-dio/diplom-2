package site.stellarburgers.generator;

import site.stellarburgers.pojo.CreateOrder;
import site.stellarburgers.pojo.Ingredient;
import site.stellarburgers.client.OrderClient;

import java.util.List;

public class OrderGenerator {

    final static String INCORRECT_INGREDIENT_HASH = "abc";

    private static OrderClient orderClient;

    public OrderGenerator(OrderClient orderClient) {
        this.orderClient = orderClient;
    }

    public static CreateOrder getDefaultOrder() {
        List<Ingredient> ingredients = orderClient.getIngredients();
        if (!ingredients.isEmpty()) {
            Class<? extends Ingredient> defaultIngredientHash = ingredients.get(0).getClass();
            CreateOrder order = new CreateOrder(List.of());
            OrderClient.createOrder(order);
            return order;
        } else {
            throw new RuntimeException("No ingredients available");
        }
    }
}