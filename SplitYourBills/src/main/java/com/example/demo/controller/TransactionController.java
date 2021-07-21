package com.example.demo.controller;

import com.example.demo.dto.AddTransactionsDTO;
import com.example.demo.dto.SpaceMembersDTO;
import com.example.demo.model.Transactions;
import com.example.demo.model.User.UserSummary;
import com.example.demo.payload.ApiResponse;
import com.example.demo.security.CurrentUser;
import com.example.demo.security.UserPrincipal;
import com.example.demo.service.SpaceMembersService;
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
    public ResponseEntity<ApiResponse> addTransactions(@RequestBody AddTransactionsDTO transactions){
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

}
