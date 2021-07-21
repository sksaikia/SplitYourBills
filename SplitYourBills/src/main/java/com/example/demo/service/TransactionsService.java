package com.example.demo.service;

import com.example.demo.dto.AddTransactionsDTO;
import com.example.demo.dto.SpaceMembersDTO;
import com.example.demo.model.Invites;
import com.example.demo.model.SpaceMembers;
import com.example.demo.model.Transactions;
import com.example.demo.model.User.User;
import com.example.demo.repository.SpaceMembersRepository;
import com.example.demo.repository.TransactionsRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


}


