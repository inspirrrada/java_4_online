package ua.com.alevel.facade;

import ua.com.alevel.dto.*;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.List;

public interface TransactionFacade {

    List<TransactionDTO> findAll();
    TransactionDTO findById(Long id);
    //+
    void create(TransactionFormDTO dto);
    List<AccountStatementDTO>  getAccountStatement(Timestamp startDate, Timestamp endDate, Long accountId);
//    void update(Long id, UserDTO dto);
//    void delete(Long id);
}
