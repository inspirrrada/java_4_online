package ua.com.alevel.api;

import ua.com.alevel.model.TransactionForm;

import java.util.Optional;

public interface TransactionApiService {

    Optional<TransactionForm> create(TransactionForm transactionForm, Long id, Long accountId);
//String create(TransactionForm transactionForm, Long id, Long accountId);
    String create2(TransactionForm transactionForm, Long accountId);
}
