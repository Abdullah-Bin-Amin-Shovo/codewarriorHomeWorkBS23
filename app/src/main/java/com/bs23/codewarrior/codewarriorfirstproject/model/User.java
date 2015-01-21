package com.bs23.codewarrior.codewarriorfirstproject.model;

/**
 * Created by Lenovo ThinkPad X230 on 1/19/2015.
 */
public class User {
    public String email;
    public String password;
    public String confirmPassword;

    public User(String email,String password, String confirmPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
