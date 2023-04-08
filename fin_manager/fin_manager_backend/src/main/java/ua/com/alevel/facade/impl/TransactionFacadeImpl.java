package ua.com.alevel.facade.impl;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.TransactionDTO;
import ua.com.alevel.dto.TransactionFormDTO;
import ua.com.alevel.dto.UserDTO;
import ua.com.alevel.facade.TransactionFacade;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Transaction;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.service.AccountService;
import ua.com.alevel.service.TransactionService;
import ua.com.alevel.service.UserService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionFacadeImpl implements TransactionFacade {

    private final TransactionService transactionService;
    private final AccountService accountService;

    @Override
    public List<TransactionDTO> findAll() {
        Collection<Transaction> transactions = transactionService.findAll();
        if (CollectionUtils.isNotEmpty(transactions)) {
            return transactions.stream().map(TransactionDTO::new).toList();
        }
        return Collections.emptyList();
    }

    @Override
    public TransactionDTO findById(Long id) {
        return new TransactionDTO(transactionService.findById(id));
    }

    @Override
    public void create(TransactionFormDTO dto) {
        Transaction transaction = new Transaction();
        Account accountFrom = accountService.findByAccountNumber(dto.getFromAccountNumber());
        Account accountTo = accountService.findByAccountNumber(dto.getToAccountNumber());
        transaction.setSum(dto.getSum());
        transaction.setFromAccount(accountFrom);
        transaction.setToAccount(accountTo);
        transactionService.create(transaction);
    }
}
