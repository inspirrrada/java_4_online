package ua.com.alevel.facade;

import ua.com.alevel.dto.AccountDTO;
import ua.com.alevel.dto.UserDTO;

import java.util.List;

public interface AccountFacade {

    List<AccountDTO> findAll();
    AccountDTO findById(Long id);
    AccountDTO findByAccountNumber(String accountNumber);
    void create(AccountDTO dto);
    void update(Long id, AccountDTO dto);
    void delete(Long id);
}
