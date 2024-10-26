package site.stellarburgers.client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import site.stellarburgers.pojo.CreateOrder;
import site.stellarburgers.pojo.Ingredient;

import java.util.List;

import static io.restassured.RestAssured.given;

public class OrderClient extends Client {

    private final static String ORDERS_PATH = "api/orders";
    private final static String INGREDIENTS_PATH = "api/ingredients";

    @Step("Создание заказа")
    public static ValidatableResponse createOrder(CreateOrder data, String bearerPlusToken) {
        return given()
                .spec(getSpec(bearerPlusToken))
                .body(data)
                .when()
                .post(ORDERS_PATH)
                .then();
    }

    @Step("Получение заказов конкретного пользователя")
    public static ValidatableResponse getUserOrders(String bearerPlusToken) {
        return given()
                .spec(getSpec(bearerPlusToken))
                .when()
                .get(ORDERS_PATH)
                .then();
    }

    @Step("Получение списка ингредиентов")
    public static List<Ingredient> getIngredients() {
        return given()
                .spec(getSpec())
                .when()
                .get(INGREDIENTS_PATH)
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList("data", Ingredient.class);
    }

    public static void createOrder(CreateOrder order) {
    }
}

