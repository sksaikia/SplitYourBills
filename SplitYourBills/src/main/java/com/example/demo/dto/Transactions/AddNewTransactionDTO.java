package com.example.demo.dto.Transactions;

import com.example.demo.dto.Users.UserDetails;
import com.example.demo.model.Transactions;

public class AddNewTransactionDTO {
    private long spaceId,amount;
    private String description;
    private String phoneNo;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }


    public long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(long spaceId) {
        this.spaceId = spaceId;
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

    public AddNewTransactionDTO(long spaceId, long amount, String description) {
        this.spaceId = spaceId;
        this.amount = amount;
        this.description = description;
    }

    public AddNewTransactionDTO(Transactions transactions){
        this.setSpaceId(transactions.getSpaceId());
        this.setAmount(transactions.getAmount());
        this.setDescription(transactions.getDescription());
    }
}
