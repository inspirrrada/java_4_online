package ua.com.alevel.api;

import ua.com.alevel.model.TransactionFormModel;

import java.util.Optional;

public interface TransactionApiService {

//    Optional<TransactionFormModel> create(TransactionFormModel transactionForm, Long id, Long accountId);
////String create(TransactionForm transactionForm, Long id, Long accountId);
    //+
    String createTransaction(TransactionFormModel transactionForm, Long accountId);
}
