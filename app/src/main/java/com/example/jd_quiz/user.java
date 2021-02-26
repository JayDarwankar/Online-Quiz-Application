package com.example.jd_quiz;

public class user {
    String Name,Email,Pass,ReferCode;
    long coins=00;

    public user(String name, String email, String pass, String referCode) {
        Name = name;
        Email = email;
        Pass = pass;
        ReferCode = referCode;
    }

    public user(){

    }

    public long getCoins() {
        return coins;
    }

    public void setCoins(long coins) {
        this.coins = coins;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getReferCode() {
        return ReferCode;
    }

    public void setReferCode(String referCode) {
        ReferCode = referCode;
    }
}
