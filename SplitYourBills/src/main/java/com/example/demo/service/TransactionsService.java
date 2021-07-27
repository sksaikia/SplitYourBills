package com.example.demo.service;

import com.example.demo.dto.Transactions.AddNewTransactionDTO;
import com.example.demo.dto.Transactions.AddTransactionsDTO;
import com.example.demo.model.Transactions;
import com.example.demo.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TransactionsService {

   @Autowired
    TransactionsRepository transactionsRepository;

    public TransactionsService() {
    }

    public void addTransaction(AddTransactionsDTO transactionsDTO){
        Transactions transactions = getTransactionFromDTO(transactionsDTO);
        transactionsRepository.save(transactions);
    }

    private Transactions getTransactionFromDTO(AddTransactionsDTO transactionsDTO) {
        Transactions transactions = new Transactions(transactionsDTO);
        return transactions;
    }

    public List<AddTransactionsDTO> getTransactionsBySpaceId(long spaceId){
        List<Transactions> list = transactionsRepository.findAllBySpaceId(spaceId);
        List<AddTransactionsDTO> transactionsDTOS = new ArrayList<>();

        for (Transactions t : list)
            transactionsDTOS.add(getDTOFromTransaction(t));

        return transactionsDTOS;
    }

    private AddTransactionsDTO getDTOFromTransaction(Transactions t) {
        AddTransactionsDTO addTransactionsDTO = new AddTransactionsDTO(t);
        return addTransactionsDTO;
    }
    public List<AddTransactionsDTO> getTransactionsBySpaceIdAndPersonId(long spaceId,long personId){
        List<Transactions> list = transactionsRepository.findAllBySpaceIdAndPersonId(spaceId,personId);
        List<AddTransactionsDTO> transactionsDTOS = new ArrayList<>();

        for (Transactions t : list)
            transactionsDTOS.add(getDTOFromTransaction(t));

        return transactionsDTOS;
    }

    public void addTransaction(AddNewTransactionDTO transactionsDTO){
        Transactions transactions = getNewTransactionFromDTO(transactionsDTO);
        transactionsRepository.save(transactions);
    }

    private Transactions getNewTransactionFromDTO(AddNewTransactionDTO transactionsDTO) {
        Transactions transactions = new Transactions(transactionsDTO);
        return transactions;
    }



}


