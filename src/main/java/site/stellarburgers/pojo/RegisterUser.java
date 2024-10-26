package site.stellarburgers.pojo;

import lombok.Data;

@Data
public class RegisterUser {
    private  String email;
    private  String password;
    private  String name;

    public RegisterUser(String defaultEmail, String defaultPassword, String s) {
    }
}
