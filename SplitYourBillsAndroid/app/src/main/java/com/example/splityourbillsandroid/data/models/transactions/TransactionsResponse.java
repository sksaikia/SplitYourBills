package com.example.splityourbillsandroid.data.models.transactions;

import com.example.splityourbillsandroid.data.models.authentication.response.UserResponse;
import com.google.gson.annotations.SerializedName;

public class TransactionsResponse {

    @SerializedName("transactionId")
    private Long transactionId;

    @SerializedName("spaceId")
    private Long spaceId;

    @SerializedName("personId")
    private Long personID;

    @SerializedName("amount")
    private Long amount;

    @SerializedName("description")
    private String description;

    @SerializedName("userDetails")
    private UserResponse userDetails;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    public Long getPersonID() {
        return personID;
    }

    public void setPersonID(Long personID) {
        this.personID = personID;
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

    public UserResponse getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserResponse userDetails) {
        this.userDetails = userDetails;
    }
}
