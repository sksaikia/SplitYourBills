package com.example.splityourbillsandroid.data.models.transactions;

import com.example.splityourbillsandroid.data.models.authentication.response.UserResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransactionDetailsResponse {


    @SerializedName("totalAmount")
    private Long totalAmount;

    @SerializedName("perPerson")
    private Long perPerson;

    @SerializedName("personDetailsTXNS")
    private List <PersonDetailsTXN>  personDetailsTXN;

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getPerPerson() {
        return perPerson;
    }

    public void setPerPerson(Long perPerson) {
        this.perPerson = perPerson;
    }

    public List<PersonDetailsTXN> getPersonDetailsTXN() {
        return personDetailsTXN;
    }

    public void setPersonDetailsTXN(List<PersonDetailsTXN> personDetailsTXN) {
        this.personDetailsTXN = personDetailsTXN;
    }
}
