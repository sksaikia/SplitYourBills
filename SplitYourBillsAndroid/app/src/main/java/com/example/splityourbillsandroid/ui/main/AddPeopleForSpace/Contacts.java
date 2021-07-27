package com.example.splityourbillsandroid.ui.main.AddPeopleForSpace;

public class Contacts {
    private String name = "";
    private String phone = "";

    @Override
    public String toString() {
        return "Contacts{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Contacts(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
