package site.stellarburgers.pojo;

import lombok.Data;

@Data
public class LoginUser {
    private  String email;
    private  String password;

    public LoginUser(String defaultEmail, String defaultPassword) {

    }
}
