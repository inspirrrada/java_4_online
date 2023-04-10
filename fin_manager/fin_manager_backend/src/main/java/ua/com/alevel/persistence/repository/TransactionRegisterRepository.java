package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.TransactionRegister;

import java.util.Collection;

@Repository
public interface TransactionRegisterRepository extends BaseRepository<TransactionRegister> {

    Collection<TransactionRegister> findAllByTransactionId(Long transactionId);
    TransactionRegister findByTransactionIdAndUserId(Long transactionId, Long userId);
}
