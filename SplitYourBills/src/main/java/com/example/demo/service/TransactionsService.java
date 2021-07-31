package com.example.demo.service;

import com.example.demo.dto.Transactions.AddNewTransactionDTO;
import com.example.demo.dto.Transactions.AddTransactionsDTO;
import com.example.demo.model.Invites;
import com.example.demo.model.SpaceMembers;
import com.example.demo.model.Transactions;
import com.example.demo.model.User.User;
import com.example.demo.repository.TransactionsRepository;
import com.example.demo.repository.UserRepository;
import org.hibernate.service.spi.InjectService;
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

   @Autowired
    UserRepository userRepository;

   @Autowired
   InvitesService invitesService;

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

    public int addTransaction(List<AddNewTransactionDTO> transactionsDTOs){


        for (AddNewTransactionDTO transactionsDTO: transactionsDTOs) {

            Long currUserId = Long.valueOf(0);
            String phoneNo = transactionsDTO.getPhoneNo();
            if (userRepository.existsByPhoneno(phoneNo)) {
                Optional<User> optionalUser = userRepository.findByPhoneno(phoneNo);
                if (optionalUser.isPresent()) {
                    currUserId = optionalUser.get().getId();
                    //As the phone no is present in the users table, add it in the SpaceMembers table
//                SpaceMembers spaceMembers = new SpaceMembers(transactionsDTO.getSpaceId(),currUserId,phoneNo);
//                transactionsRepository.save(spaceMembers);
                    Transactions transactions = new Transactions(transactionsDTO, currUserId);
                    transactionsRepository.save(transactions);

                } else {
                    //TODO need to throw some error
                }
            } else {
                //Create a new entry in the invitations table with phone no and space_id
                //replace the currUserId value
                Long spaceId = transactionsDTO.getSpaceId();

                long inviteId = invitesService.getInviteByPhoneNoAndSpaceId(spaceId, phoneNo);

                Transactions transactions = new Transactions(transactionsDTO, -1, inviteId);
                transactionsRepository.save(transactions);


            }

        }
        return 1;
    }

//    private Transactions getNewTransactionFromDTO(AddNewTransactionDTO transactionsDTO) {
//        Transactions transactions = new Transactions(transactionsDTO);
//        return transactions;
//    }

    //Throw error here
    public AddTransactionsDTO getTransactionById(long id){
        Optional<Transactions>  optionalTransactions =  transactionsRepository.findById(id);
        Transactions transactions;
        AddTransactionsDTO dto = new AddTransactionsDTO();

        if (optionalTransactions.isPresent()){
            transactions = optionalTransactions.get();
            dto =  getDTOFromTransaction(transactions);
            return dto;
        }else{

        }

        return dto;
    }


    public void updateTransactionsById(Long id,AddNewTransactionDTO transactionDTO){
        Optional<Transactions> optionalTransactions = transactionsRepository.findById(id);
        if (optionalTransactions.isPresent()){
            Transactions transactions = optionalTransactions.get();
            transactions.setDescription(transactionDTO.getDescription());
            transactions.setAmount(transactionDTO.getAmount());
            transactionsRepository.save(transactions);
        }
    }
    public void deleteTransactionById(Long id){
        Optional<Transactions> optionalTransactions = transactionsRepository.findById(id);
        if (optionalTransactions.isPresent()){
            transactionsRepository.deleteById(id);
        }
    }


}


