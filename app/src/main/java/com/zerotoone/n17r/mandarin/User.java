package com.zerotoone.n17r.mandarin;

/**
 * Created by Azat Kun on 8/2/2017.
 */

public class User {
    public String full_name;
    public int number;

    public User(String full_name, int number) {
        this.full_name = full_name;
        this.number = number;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
