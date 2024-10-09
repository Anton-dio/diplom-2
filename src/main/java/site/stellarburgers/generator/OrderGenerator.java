package site.stellarburgers.generator;

import site.stellarburgers.pojo.CreateOrder;
import site.stellarburgers.client.OrderClient;

import java.util.List;

public class OrderGenerator {

    final static String DEFAULT_INGREDIENT_HASH = "61c0c5a71d1f82001bdaaa70";
    final static String INCORRECT_INGREDIENT_HASH = "abc";

    private OrderClient orderClient;

    public OrderGenerator(OrderClient orderClient) {
        this.orderClient = orderClient;
    }

    public static CreateOrder getDefaultOrder() {
        CreateOrder order = new CreateOrder(List.of(DEFAULT_INGREDIENT_HASH));
        OrderClient.createOrder(order);
        return order;
    }

    public static CreateOrder getOrderWithoutIngredients() {
        CreateOrder order = new CreateOrder(List.of());
        orderClient.createOrder(order);
        return order;
    }

    public static CreateOrder getOrderWithIncorrectHash() {
        CreateOrder order = new CreateOrder(List.of(INCORRECT_INGREDIENT_HASH));
        orderClient.createOrder(order);
        return order;
    }
}