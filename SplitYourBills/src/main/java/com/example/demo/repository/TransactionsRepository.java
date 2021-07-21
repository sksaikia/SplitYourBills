package com.example.demo.repository;

import com.example.demo.model.SpaceMembers;
import com.example.demo.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
    List<Transactions> findAllByTransactionId(Long transactionId);
    List<Transactions> findAllBySpaceId(Long spaceId);
    List<Transactions> findAllBySpaceIdAndPersonId(Long spaceId,Long personId);
}