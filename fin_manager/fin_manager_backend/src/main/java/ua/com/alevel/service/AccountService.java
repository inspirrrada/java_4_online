package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.service.BaseService;

import java.util.Collection;

public interface AccountService extends BaseService<Account> {

    Account findByAccountNumber(String accountNumber);
//    Collection<Account> findAllByUser(Long userId);
    Long findUserIdByAccountId(Long id);
}
