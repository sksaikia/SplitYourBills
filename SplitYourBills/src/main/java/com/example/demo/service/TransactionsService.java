package com.example.demo.service;

import com.example.demo.dto.TXNDetails.PersonDetailsTXN;
import com.example.demo.dto.TXNDetails.TransactionDetails;
import com.example.demo.dto.Transactions.AddNewTransactionDTO;
import com.example.demo.dto.Transactions.AddTransactionsDTO;
import com.example.demo.dto.Users.InviteUserDetails;
import com.example.demo.dto.Users.UserDetails;
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
import java.util.*;

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


    public TransactionDetails getTXNDetailsBySpaceId(long spaceId){
        List<Transactions> list = transactionsRepository.findAllBySpaceId(spaceId);
        List<AddTransactionsDTO> transactionsDTOS = new ArrayList<>();

        for (Transactions t : list)
            transactionsDTOS.add(getDTOFromTransaction(t));

        HashMap<Long,Long> mapPerson = new HashMap<>();
        HashMap<Long,Long> mapInvites = new HashMap<>();
        HashMap<Long, InviteUserDetails> detailsInvites = new HashMap<>();
        HashMap<Long, UserDetails> detailsUsers = new HashMap<>();

        long totalAmount =0 ;
        long perPersonAmount = 0;

        for (AddTransactionsDTO txn : transactionsDTOS){

            totalAmount += txn.getAmount();
            long personId = txn.getPersonId();
            long inviteId = txn.getInvites().getInviteId();
            long amount = txn.getAmount();
            System.out.println("Persondi : " + personId + " inviteid : " + inviteId);
            if (inviteId==-1){
                if (mapPerson.containsKey(personId)){
                    mapPerson.put(personId,mapPerson.get(personId)+amount);
                }else{
                    mapPerson.put(personId,amount);
                }
                detailsUsers.put(personId,txn.getUserDetails());

            }else {
                if (mapInvites.containsKey(inviteId)){
                    mapInvites.put(inviteId,mapInvites.get(inviteId)+amount);
                }else{
                    mapInvites.put(inviteId,amount);
                }
                detailsInvites.put(inviteId,txn.getInvites());

            }


        }

        int totalMembers = mapInvites.size()+mapPerson.size();
        perPersonAmount = totalAmount/totalMembers;

        TransactionDetails transactionDetails = new TransactionDetails();
        transactionDetails.setTotalAmount(totalAmount);
        transactionDetails.setPerPerson(perPersonAmount);

        List<PersonDetailsTXN> personDetailsTXNS = new ArrayList<>();
        System.out.println("Size : person : " + mapPerson.size() + " ivnite : " + mapInvites.size());
        Iterator it = mapPerson.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry mapElement = (Map.Entry) it.next();

            long key = (long) mapElement.getKey();
            long amount = (long) mapElement.getValue();

            PersonDetailsTXN o = new PersonDetailsTXN(key, (long) -1,amount);
            o.setUserDetails(detailsUsers.get(key));
            personDetailsTXNS.add(o);
        }
        Iterator it2 = mapInvites.entrySet().iterator();
        while (it2.hasNext()){
            Map.Entry mapElement = (Map.Entry) it2.next();

            long key = (long) mapElement.getKey();
            long amount = (long) mapElement.getValue();

            PersonDetailsTXN o = new PersonDetailsTXN((long) -1, key,amount);
            o.setInvites(detailsInvites.get(key));
            personDetailsTXNS.add(o);
        }

        transactionDetails.setPersonDetailsTXNS(personDetailsTXNS);

        return transactionDetails;

    }


}


