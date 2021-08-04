package com.example.demo.model;

import com.example.demo.dto.Transactions.AddNewTransactionDTO;
import com.example.demo.dto.Transactions.AddTransactionsDTO;
import com.example.demo.model.User.User;
import com.example.demo.model.audit.DateAudit;

import javax.persistence.*;

@Entity
@Table(name="transactions")
public class Transactions extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(name="space_id")
    private Long spaceId;

    @Column(name="person_id")
    private Long personId;

    @Column(name="invite_id")
    private Long inviteId;

    @Column(name="amount")
    private Long amount;

    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "user_id",insertable = false,updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "space_id", referencedColumnName = "space_id",insertable = false,updatable = false)
    private Space space;

    @ManyToOne
    @JoinColumn(name = "invite_id", referencedColumnName = "invite_id",insertable = false,updatable = false)
    private Invites invites;

    public Invites getInvites() {
        return invites;
    }

    public void setInvites(Invites invites) {
        this.invites = invites;
    }

    public Transactions() {
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
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

    public Transactions(Long transaction_id, Long spaceId, Long personId, Long amount, String description) {
        this.transactionId = transaction_id;
        this.spaceId = spaceId;
        this.personId = personId;
        this.amount = amount;
        this.description = description;
    }
    public Transactions(AddTransactionsDTO transactionsDTO){
        this.setSpaceId(transactionsDTO.getSpaceId());
        this.setPersonId(transactionsDTO.getPersonId());
        this.setAmount(transactionsDTO.getAmount());
        this.setDescription(transactionsDTO.getDescription());

    }
    public Transactions(AddNewTransactionDTO transactionsDTO,long personId){
        this.setSpaceId(transactionsDTO.getSpaceId());
        this.setPersonId(personId);
        this.setAmount(transactionsDTO.getAmount());
        this.setDescription(transactionsDTO.getDescription());
        this.setInviteId(Long.valueOf(-1));
    }

    public Long getInviteId() {
        return inviteId;
    }

    public void setInviteId(Long inviteId) {
        this.inviteId = inviteId;
    }

    public Transactions(AddNewTransactionDTO transactionsDTO, long personId, long inviteId){
        this.setSpaceId(transactionsDTO.getSpaceId());
        this.setPersonId(personId);
        this.setAmount(transactionsDTO.getAmount());
        this.setDescription(transactionsDTO.getDescription());
        this.setInviteId(inviteId);
    }


}
