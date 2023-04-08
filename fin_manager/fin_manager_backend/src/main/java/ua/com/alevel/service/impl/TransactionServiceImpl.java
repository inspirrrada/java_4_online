package ua.com.alevel.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Transaction;
import ua.com.alevel.persistence.entity.TransactionCategory;
import ua.com.alevel.persistence.entity.TransactionRegister;
import ua.com.alevel.persistence.repository.AccountRepository;
import ua.com.alevel.persistence.repository.TransactionCategoryRepository;
import ua.com.alevel.persistence.repository.TransactionRegisterRepository;
import ua.com.alevel.persistence.repository.TransactionRepository;
import ua.com.alevel.service.AccountService;
import ua.com.alevel.service.TransactionService;

import java.math.BigDecimal;
import java.util.Collection;

@Service
@Transactional
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountService accountService;
    private final TransactionCategoryRepository transactionCategoryRepository;
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final TransactionRegisterRepository transactionRegisterRepository;

    @Override
    public void create(Transaction transaction) {
        transactionRepository.save(transaction);
        BigDecimal transactionSum = transaction.getSum();
        Account accountFrom = transaction.getFromAccount();
        Account accountTo = transaction.getToAccount();
        BigDecimal accountFromBalance = accountFrom.getBalance();
        accountFromBalance = accountFromBalance.subtract(transactionSum);
        BigDecimal accountToBalance = accountTo.getBalance();
        accountToBalance = accountToBalance.add(transactionSum);
        accountFrom.setBalance(accountFromBalance);
        accountTo.setBalance(accountToBalance);
        accountService.update(accountFrom);
        accountService.update(accountTo);
        TransactionRegister transactionIncomeRecord = new TransactionRegister();
        transactionIncomeRecord.setTransaction(transaction);
        TransactionCategory incomeCategory = transactionCategoryRepository.findById(1L).get();
        transactionIncomeRecord.setTransactionCategory(incomeCategory);
        transactionIncomeRecord.setUser(accountRepository.findByAccountId(accountFrom.getId()));
        transactionRegisterRepository.save(transactionIncomeRecord);
        TransactionRegister transactionExpenseRecord = new TransactionRegister();
        TransactionCategory expenseCategory = transactionCategoryRepository.findById(2L).get();
        transactionIncomeRecord.setTransactionCategory(expenseCategory);
        transactionExpenseRecord.setTransaction(transaction);
        transactionExpenseRecord.setUser(accountRepository.findByAccountId(accountTo.getId()));
        transactionRegisterRepository.save(transactionExpenseRecord);
    }

    @Override
    public void update(Transaction transaction) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Transaction findById(Long id) {
        return null;
    }

    @Override
    public Collection<Transaction> findAll() {
        return null;
    }
}
