package com.nice.quickpizzaclint.Model;

public class ModelReg {
    String phone,password,name;

    public ModelReg(String phone, String password, String name) {
        this.phone = phone;
        this.password = password;
        this.name = name;
    }

    public ModelReg(String password) {
        this.password = password;
    }

    public ModelReg() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
