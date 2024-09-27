package site.stellarburgers.generator;

import site.stellarburgers.pojo.LoginUser;
import site.stellarburgers.pojo.RegisterUser;

import java.util.Random;

public class UserGenerator {

    final static String DEFAULT_EMAIL = "stdr8@test.ru";
    final static String DEFAULT_PASSWORD = "123";
    final static String DEFAULT_NAME = "Seva";
    final static String FAKE_EMAIL = "dfjzgzn4564dnbxfgb@ffg435gb.r7u6tr";

    public enum UserField {
        EMAIL,
        PASSWORD,
        NAME
    }

    private static final Random RANDOM = new Random();

    public static RegisterUser getDefaultRegistrationData() {
        return new RegisterUser(DEFAULT_EMAIL, DEFAULT_PASSWORD, DEFAULT_NAME);
    }

    public static RegisterUser getRegistrationDataWithOneEmptyField(UserField emptyField) {
        RegisterUser data = null;
        switch (emptyField) {
            case EMAIL:
                data = new RegisterUser("", DEFAULT_PASSWORD, DEFAULT_NAME);
                break;
            case PASSWORD:
                data = new RegisterUser(DEFAULT_EMAIL, "", DEFAULT_NAME);
                break;
            case NAME:
                data = new RegisterUser(DEFAULT_EMAIL, DEFAULT_PASSWORD, "");
                break;
        }
        return data;
    }

    public static LoginUser getDefaultLoginData() {
        return new LoginUser(DEFAULT_EMAIL, DEFAULT_PASSWORD);
    }

    public static LoginUser getNewLoginData() {
        return new LoginUser(generateRandomEmail(), DEFAULT_PASSWORD);
    }

    public static LoginUser getFakeLoginData() {
        return new LoginUser(FAKE_EMAIL, DEFAULT_PASSWORD);
    }

    public static RegisterUser getDefaultUpdateData() {
        return new RegisterUser(generateRandomEmail(), generateRandomPassword(), generateRandomName());
    }

    private static String generateRandomEmail() {
        return "user" + RANDOM.nextInt(100000) + "@test.ru";
    }

    private static String generateRandomPassword() {
        return "password" + RANDOM.nextInt(100000);
    }

    private static String generateRandomName() {
        return "User" + RANDOM.nextInt(100000);
    }
}