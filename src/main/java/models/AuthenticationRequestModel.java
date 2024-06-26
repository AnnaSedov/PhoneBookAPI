package models;

public class AuthenticationRequestModel {
    private String username;
    private  String password;

    private AuthenticationRequestModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public  AuthenticationRequestModel password(String password) {
        this.password=password;
        return this;
    }
    public static AuthenticationRequestModel username(String username) {
        return new AuthenticationRequestModel(username,null);
    }
public AuthenticationRequestModel(String password){
        this.password=password;
}
    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
