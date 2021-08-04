package com.example.demo.dto.TXNDetails;

import java.util.ArrayList;
import java.util.List;

public class TransactionDetails {
    private long totalAmount,perPerson;

    private List<PersonDetailsTXN> personDetailsTXNS;

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public long getPerPerson() {
        return perPerson;
    }

    public void setPerPerson(long perPerson) {
        this.perPerson = perPerson;
    }

    public List<PersonDetailsTXN> getPersonDetailsTXNS() {
        return personDetailsTXNS;
    }

    public void setPersonDetailsTXNS(List<PersonDetailsTXN> personDetailsTXNS) {
        this.personDetailsTXNS = personDetailsTXNS;
    }

    public TransactionDetails() {
    }

    public TransactionDetails(long totalAmount, long perPerson, ArrayList<PersonDetailsTXN> personDetailsTXNS) {
        this.totalAmount = totalAmount;
        this.perPerson = perPerson;
        this.personDetailsTXNS = personDetailsTXNS;
    }
}
