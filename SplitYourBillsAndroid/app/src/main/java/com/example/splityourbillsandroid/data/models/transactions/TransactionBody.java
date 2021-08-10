package com.example.splityourbillsandroid.data.models.transactions;

import com.google.gson.annotations.SerializedName;

public class TransactionBody {
    @SerializedName("amount")
    private Long amount;

    @SerializedName("description")
    private String description;

    @SerializedName("phoneNo")
    private String phoneNo;
    @SerializedName("spaceId")
    private Long spaceId;

    public TransactionBody() {
    }

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

    public TransactionBody(Long amount, String description, String phoneNo, Long spaceId) {
        this.amount = amount;
        this.description = description;
        this.phoneNo = phoneNo;
        this.spaceId = spaceId;
    }



    public TransactionBody(Long amount, String description) {
        this.amount = amount;
        this.description = description;
    }
}
