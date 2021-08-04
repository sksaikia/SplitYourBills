package com.example.demo.controller;

import com.example.demo.dto.TXNDetails.TransactionDetails;
import com.example.demo.dto.Transactions.AddNewTransactionDTO;
import com.example.demo.dto.Transactions.AddTransactionsDTO;
import com.example.demo.model.Transactions;
import com.example.demo.payload.ApiResponse;
import com.example.demo.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionsService transactionsService;
    @Autowired
    private UserController userController;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addTransactions(@RequestBody List<AddNewTransactionDTO> transactions){
        transactionsService.addTransaction(transactions);
        return new ResponseEntity<ApiResponse>(new ApiResponse(
                true,"Transaction added"),HttpStatus.OK);

    }

    @GetMapping("/{spaceId}")
    public ResponseEntity<List<AddTransactionsDTO>> getTransactionsBySpaceId(@PathVariable("spaceId") long spaceId){
        List<AddTransactionsDTO> transactionsDTOS = transactionsService.getTransactionsBySpaceId(spaceId);
        return new ResponseEntity<List<AddTransactionsDTO>>(transactionsDTOS,HttpStatus.OK);
    }
    @GetMapping("/{spaceId}/{personId}")
    public ResponseEntity<List<AddTransactionsDTO>> getTransactionsBySpaceIdAndPersonId(@PathVariable("spaceId") long spaceId,@PathVariable("personId") long personId){
        List<AddTransactionsDTO> transactionsDTOS = transactionsService.getTransactionsBySpaceIdAndPersonId(spaceId,personId);
        return new ResponseEntity<List<AddTransactionsDTO>>(transactionsDTOS,HttpStatus.OK);
    }

    @GetMapping("/get/{transactionId}")
    public ResponseEntity<AddTransactionsDTO> getTransaction(@PathVariable("transactionId") long id){
        AddTransactionsDTO transactionsDTOS = transactionsService.getTransactionById(id);
        return new ResponseEntity<AddTransactionsDTO>(transactionsDTOS,HttpStatus.OK);
    }
    @PutMapping("/{transactionId}")
    public ResponseEntity<ApiResponse> updateTransaction(@PathVariable("transactionId") long id,@RequestBody AddNewTransactionDTO transactions){
        transactionsService.updateTransactionsById(id,transactions);
        return new ResponseEntity<ApiResponse>(new ApiResponse(
                true,"Transaction updated"),HttpStatus.OK);
    }
    @DeleteMapping("/{transactionId}")
    public ResponseEntity<ApiResponse> deleteTransaction(@PathVariable("transactionId") long id){
        transactionsService.deleteTransactionById(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse(
                true,"Transaction deleted"),HttpStatus.OK);
    }


    @GetMapping("/details/{spaceId}")
    public ResponseEntity<TransactionDetails> getTransactionsBySpaceIdPersonLevelDetails(@PathVariable("spaceId") long spaceId){
        TransactionDetails transactionDetails = transactionsService.getTXNDetailsBySpaceId(spaceId);
        return new ResponseEntity<TransactionDetails>(transactionDetails,HttpStatus.OK);
    }

}
