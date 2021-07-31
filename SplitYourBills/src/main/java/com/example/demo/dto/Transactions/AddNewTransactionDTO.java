package com.example.demo.dto.Transactions;

import com.example.demo.dto.Users.UserDetails;
import com.example.demo.model.Transactions;

public class AddNewTransactionDTO {
    private long spaceId,personId,amount;
    private String description;
    private String phoneNo;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public AddNewTransactionDTO(long spaceId, long personId, long amount, String description, String phoneNo) {
        this.spaceId = spaceId;
        this.personId = personId;
        this.amount = amount;
        this.description = description;
        this.phoneNo = phoneNo;
    }

    public long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(long spaceId) {
        this.spaceId = spaceId;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AddNewTransactionDTO() {
    }

    public AddNewTransactionDTO(long spaceId, long personId, long amount, String description) {
        this.spaceId = spaceId;
        this.personId = personId;
        this.amount = amount;
        this.description = description;
    }

    public AddNewTransactionDTO(Transactions transactions){
        this.setSpaceId(transactions.getSpaceId());
        this.setPersonId(transactions.getPersonId());
        this.setAmount(transactions.getAmount());
        this.setDescription(transactions.getDescription());
    }
}
