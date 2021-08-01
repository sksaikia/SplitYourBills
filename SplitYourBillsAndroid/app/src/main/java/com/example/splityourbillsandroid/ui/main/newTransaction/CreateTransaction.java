package com.example.splityourbillsandroid.ui.main.newTransaction;

import com.google.gson.annotations.SerializedName;

public class CreateTransaction {
    private Long amount;

    private String description;
    private String phoneNo;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    public CreateTransaction(Long amount, String description, String phoneNo, Long spaceId) {
        this.amount = amount;
        this.description = description;
        this.phoneNo = phoneNo;
        this.spaceId = spaceId;
    }

    private Long spaceId;


}
