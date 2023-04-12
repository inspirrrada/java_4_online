package ua.com.alevel.facade.impl;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.AccountDTO;
import ua.com.alevel.dto.UserDTO;
import ua.com.alevel.facade.AccountFacade;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.service.AccountService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountFacadeImpl implements AccountFacade {

    private final AccountService accountService;

    @Override
    public List<AccountDTO> findAll() {
        Collection<Account> accounts = accountService.findAll();
        if (CollectionUtils.isNotEmpty(accounts)) {
            return accounts.stream().map(AccountDTO::new).toList();
        }
        return Collections.emptyList();
    }

    @Override
    public AccountDTO findById(Long id) {
        return new AccountDTO(accountService.findById(id));
    }

    @Override
    public AccountDTO findByAccountNumber(String accountNumber) {
        return new AccountDTO(accountService.findByAccountNumber(accountNumber));
    }

    @Override
    public void create(AccountDTO dto) {
        Account account = new Account();
        account.setAccountNumber(dto.getAccountNumber());
        account.setBalance(dto.getBalance());
        accountService.create(account);
    }

    @Override
    public void update(Long id, AccountDTO dto) {
        Account account = new Account();
        account.setAccountNumber(dto.getAccountNumber());
        account.setBalance(dto.getBalance());
        accountService.update(account);
    }

    @Override
    public void delete(Long id) {
        accountService.delete(id);
    }
}