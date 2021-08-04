package com.example.vendorproject;

public class userinfo {
    private String name;
    //private long ph;
    private String password;
    private long pincode;

    public userinfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public long getPh() {
        return ph;
    }

    public void setPh(long ph) {
        this.ph = ph;
    }*/

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getPincode() {
        return pincode;
    }

    public void setPincode(long pincode) {
        this.pincode = pincode;
    }
}