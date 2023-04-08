package ua.com.alevel.facade;

import ua.com.alevel.dto.TransactionDTO;
import ua.com.alevel.dto.TransactionFormDTO;
import ua.com.alevel.dto.UserDTO;
import ua.com.alevel.dto.UserFinanceDTO;

import java.util.List;

public interface TransactionFacade {

    List<TransactionDTO> findAll();
    TransactionDTO findById(Long id);
    void create(TransactionFormDTO dto);
//    void update(Long id, UserDTO dto);
//    void delete(Long id);
}
