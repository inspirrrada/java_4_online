package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Transaction;
import ua.com.alevel.persistence.entity.TransactionRegister;

import java.time.OffsetDateTime;
import java.util.Collection;

public interface TransactionService extends BaseService<Transaction> {

    Collection<Transaction> findAllByAccountId(Long accountId);
    TransactionRegister findRecordByTransactionIdAndUserId(Long transactionId, Long userId);
    Collection<TransactionRegister> findAllByTransactionId(Long transactionId);
}
