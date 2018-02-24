package com.example.maryem.myapplicationtp2;

/**
 * Created by maryem on 13/02/18.
 */

public class Login {
    private int id;
    private String login;
    private String pass;
    public Login(){}
    public Login(String login, String pass){
        this.login = login;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getLogin() {

        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
