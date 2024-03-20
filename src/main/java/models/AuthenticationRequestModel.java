package models;

public class AuthenticationRequestModel {
    private String userName;
    private  String password;

    public AuthenticationRequestModel(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public static AuthenticationRequestModel password(String password) {

        return new AuthenticationRequestModel(password);
    }
    public static AuthenticationRequestModel userName(String userName) {

        return new AuthenticationRequestModel(userName);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}