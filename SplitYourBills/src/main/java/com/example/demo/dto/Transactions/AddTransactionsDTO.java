package com.example.demo.dto.Transactions;

import com.example.demo.dto.Users.UserDetails;
import com.example.demo.model.SpaceMembers;
import com.example.demo.model.Transactions;

public class AddTransactionsDTO {
    private long transactionId,spaceId,personId,amount;
    private String description;
    private UserDetails userDetails;

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
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

    public AddTransactionsDTO() {
    }

    public AddTransactionsDTO(Transactions transactions){
        this.setTransactionId(transactions.getTransactionId());
        this.setSpaceId(transactions.getSpaceId());
        this.setPersonId(transactions.getPersonId());
        this.setAmount(transactions.getAmount());
        this.setDescription(transactions.getDescription());
        this.userDetails = new UserDetails(transactions.getUser());
    }


}
