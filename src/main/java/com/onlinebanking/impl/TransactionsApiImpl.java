package com.onlinebanking.impl;

import com.onlinebanking.api.TransactionsApi;
import com.onlinebanking.model.Transaction;
import com.onlinebanking.model.TransferRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TransactionsApiImpl implements TransactionsApi {
    private static final List<Transaction> transactions = new ArrayList<>();

    static {
        // Dummy Transaction Data using Setters
        Transaction txn1 = new Transaction();
        txn1.setId(1);
        txn1.setFromAccount("ACC123456");
        txn1.setToAccount("ACC789012");
        txn1.setAmount(new BigDecimal("250.00"));
        txn1.setStatus(Transaction.StatusEnum.COMPLETED);
        txn1.setCreatedAt(OffsetDateTime.now());
        transactions.add(txn1);

        Transaction txn2 = new Transaction();
        txn2.setId(2);
        txn2.setFromAccount("ACC789012");
        txn2.setToAccount("ACC345678");
        txn2.setAmount(new BigDecimal("150.75"));
        txn2.setStatus(Transaction.StatusEnum.PENDING);
        txn2.setCreatedAt(OffsetDateTime.now());
        transactions.add(txn2);

        Transaction txn3 = new Transaction();
        txn3.setId(3);
        txn3.setFromAccount("ACC345678");
        txn3.setToAccount("ACC123456");
        txn3.setAmount(new BigDecimal("500.00"));
        txn3.setStatus(Transaction.StatusEnum.FAILED);
        txn3.setCreatedAt(OffsetDateTime.now());
        transactions.add(txn3);
    }


    @Override
    public ResponseEntity<Transaction> transactionsTransferPost(TransferRequest request) {
        Transaction transaction = new Transaction();
        transaction.setFromAccount(request.getFromAccount());
        transaction.setToAccount(request.getToAccount());
        transaction.setAmount(request.getAmount());
        transactions.add(transaction);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<Transaction>> transactionsHistoryGet() {
        return ResponseEntity.ok(transactions);
    }
}
